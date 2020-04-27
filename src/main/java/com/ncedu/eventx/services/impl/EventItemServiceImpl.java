package com.ncedu.eventx.services.impl;

import com.ncedu.eventx.converters.EventItemMapper;
import com.ncedu.eventx.converters.EventMapper;
import com.ncedu.eventx.converters.UsersMapper;
import com.ncedu.eventx.enums.UserRoleItems;
import com.ncedu.eventx.models.DTO.EventItemDTO;
import com.ncedu.eventx.models.DTO.EventItemForCreateDTO;
import com.ncedu.eventx.models.DTO.EventItemWithUsersDTO;
import com.ncedu.eventx.models.entities.*;
import com.ncedu.eventx.repositories.*;
import com.ncedu.eventx.services.EventItemService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.ncedu.eventx.enums.UserRoleItems.SPEAKER;
import static com.ncedu.eventx.enums.UserRoleItems.VISITOR;

@Service
public class EventItemServiceImpl implements EventItemService {

    final EventItemRepository eventItemRepository;
    final EventRepository eventRepository;
    final RolesRepository rolesRepository;
    final UserEventItemRepository userEventItemRepository;
    final UserRepository userRepository;
    SimpleDateFormat formatCreate = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);


    public EventItemServiceImpl(EventItemRepository eventItemRepository,
                                EventRepository eventRepository,
                                RolesRepository rolesRepository,
                                UserEventItemRepository userEventItemRepository,
                                UserRepository userRepository) {
        this.eventItemRepository = eventItemRepository;
        this.eventRepository = eventRepository;
        this.rolesRepository = rolesRepository;
        this.userEventItemRepository = userEventItemRepository;
        this.userRepository = userRepository;
    }

    EventMapper eventMapper = Mappers.getMapper(EventMapper.class);
    EventItemMapper eventItemMapper = Mappers.getMapper(EventItemMapper.class);
    UsersMapper usersMapper = Mappers.getMapper(UsersMapper.class);

    @Override
    public EventItemEntity createEventItem(EventItemForCreateDTO eventItemDTO) {
        EventItemEntity eventItemEntity = new EventItemEntity();

        EventEntity parentEntity = eventRepository.findById(eventItemDTO.getParent());

        eventItemEntity.setParent(parentEntity);
        eventItemEntity.setName(eventItemDTO.getName());
        eventItemEntity.setAuditory(eventItemDTO.getAuditory());
        String dateStart = eventItemDTO.getDateStart() + " " + eventItemDTO.getTimeStart();
        try {
            eventItemEntity.setTimeStart(formatCreate.parse(dateStart));
        }catch (Exception e){
            e.printStackTrace();
        }

        eventItemRepository.save(eventItemEntity);
        return eventItemEntity;
    }

    @Override
    public List<EventItemWithUsersDTO> getEventItemWithUsersListByParent(int id, UserEntity userEntity) {
        EventEntity eventEntity = eventRepository.findById(id);
        List<EventItemEntity> list = eventItemRepository.findAllByParent(eventEntity);

        List<EventItemWithUsersDTO> withUsersDTOList = new ArrayList<>();

        for (EventItemEntity e: list) {
            EventItemWithUsersDTO item = getEventItemWithUsers(e,userEntity);
            withUsersDTOList.add(item);
        }
        return withUsersDTOList;
    }

    public List<EventItemDTO> getItemsByUser(int userId) {
        UserEntity userEntity = userRepository.findById(userId);
        RoleEntity roleEntity = rolesRepository.findByName(VISITOR.getDescription());

        List<UserEventItemEntity> userEventItemEntityList = userEventItemRepository.getAllByUser(userEntity);

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY,0);
        Date now = today.getTime();

        List<EventItemEntity> list = userEventItemEntityList.stream()
                .filter(eventItemEntity -> eventItemEntity.getRole().equals(roleEntity))
                .map(UserEventItemEntity::getItem)
                .filter(eventWithItemsDTO -> eventWithItemsDTO.getTimeStart().after(now))
                .sorted(Comparator.comparing(EventItemEntity::getTimeStart)).collect(Collectors.toList());

        return eventItemMapper.toListDTO(list);
    }

    @Override
    public EventItemWithUsersDTO getEventItemWithUsers(EventItemEntity e, UserEntity userEntity){
        RoleEntity userRoleVisit = rolesRepository.findByName(VISITOR.getDescription());
        RoleEntity userRoleSpeaker = rolesRepository.findByName(SPEAKER.getDescription());

        List<UserEventItemEntity> userEventMap = userEventItemRepository.getAllByItem(e);

        List<UserEntity> usersList = userEventMap.stream()
                .filter(role -> role.getRole().equals(userRoleVisit))
                .map(UserEventItemEntity::getUser).collect(Collectors.toList());;

        UserEntity speaker = userEventMap.stream().filter(role -> role.getRole().equals(userRoleSpeaker))
                .map(UserEventItemEntity::getUser).findAny().orElseGet(null);

        boolean featured = userEventMap.stream()
                .filter(role -> role.getRole().equals(userRoleVisit))
                .filter(user -> user.getUser().equals(userEntity))
                .map(UserEventItemEntity::getUser).findFirst().isPresent();

        return new EventItemWithUsersDTO(eventItemMapper.toEventItemDTO(e),
                usersMapper.toDTO(speaker),
                featured,
                usersMapper.toUserDTOList(usersList));
    }

    @Override
    public boolean deleteItemById(int id) {
        eventItemRepository.deleteById(id);
        return true;
    }
}
