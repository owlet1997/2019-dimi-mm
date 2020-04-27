package com.ncedu.eventx.services.impl;

import com.ncedu.eventx.converters.EventMapper;
import com.ncedu.eventx.converters.UsersMapper;
import com.ncedu.eventx.models.DTO.*;


import com.ncedu.eventx.models.entities.*;
import com.ncedu.eventx.repositories.*;
import com.ncedu.eventx.services.EventsService;
import com.ncedu.eventx.services.UserEventService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.ncedu.eventx.enums.UserRoleItems.CREATOR;
import static com.ncedu.eventx.enums.UserRoleItems.VISITOR;

@Service
public class UserEventServiceImpl implements UserEventService {

    final UserEventRepository userEventRepository;
    final UserEventItemRepository userEventItemRepository;

    final EventRepository eventRepository;
    final EventItemRepository eventItemRepository;
    final RolesRepository rolesRepository;
    final UserRepository userRepository;
    final EventsService eventsService;

    public UserEventServiceImpl(UserEventRepository userEventRepository,
                                UserEventItemRepository userEventItemRepository,
                                EventRepository eventRepository,
                                EventItemRepository eventItemRepository, RolesRepository rolesRepository,
                                UserRepository userRepository,
                                EventsService eventsService) {
        this.userEventRepository = userEventRepository;
        this.userEventItemRepository = userEventItemRepository;
        this.eventRepository = eventRepository;
        this.eventItemRepository = eventItemRepository;
        this.rolesRepository = rolesRepository;
        this.userRepository = userRepository;
        this.eventsService = eventsService;
    }

    EventMapper eventMapper = Mappers.getMapper(EventMapper.class);
    UsersMapper usersMapper = Mappers.getMapper(UsersMapper.class);

    @Override
    public EventWithUsersDTO createEvent(EventForCreateDTO createDTO, String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        EventEntity eventEntity = eventsService.createEvent(createDTO);

        System.out.println(createDTO);

        RoleEntity roleEntity = rolesRepository.findByName(CREATOR.getDescription());

        UserEventKey key = new UserEventKey(eventEntity.getId(),userEntity.getId(),roleEntity.getId());
        UserEventEntity userEventEntity = new UserEventEntity(key,userEntity,eventEntity,roleEntity,1);
        userEventRepository.save(userEventEntity);
        EventWithUsersDTO eventWithUsersDTO = eventMapper.toEventWithUsersDTO(eventEntity);
        eventWithUsersDTO.setCreator(usersMapper.toDTO(userEntity));
        return eventWithUsersDTO;
    }


    @Override
    public boolean visitEvent(String username, int eventId) {
        if (isVisited(username, eventId)){
            deleteVisit(username, eventId);

            return false;
        }
        else {
            UserEntity userEntity = userRepository.findByUsername(username);
            EventEntity eventEntity = eventRepository.findById(eventId);
            RoleEntity roleEntity = rolesRepository.findByName(VISITOR.getDescription());

            UserEventKey key = new UserEventKey(eventEntity.getId(),userEntity.getId(),roleEntity.getId());
            UserEventEntity userEventEntity = new UserEventEntity(key,userEntity,eventEntity,roleEntity,1);
            userEventRepository.save(userEventEntity);
        }
        return true;
    }

    @Override
    public boolean isVisited(String username, int eventId) {
        UserEntity userEntity = userRepository.findByUsername(username);
        EventEntity eventEntity = eventRepository.findById(eventId);
        RoleEntity roleEntity = rolesRepository.findByName(VISITOR.getDescription());

        List<UserEventEntity> list = userEventRepository.findAllByEvent(eventEntity);

        boolean visitor = list.stream().filter(role -> role.getRole().equals(roleEntity))
                .filter(user -> user.getUser().equals(userEntity))
                .map(UserEventEntity::getUser).findAny().isPresent();

        return visitor;
    }

    @Override
    public boolean deleteVisit(String username, int eventId) {
        RoleEntity roleEntity = rolesRepository.findByName(VISITOR.getDescription());
        UserEntity userEntity = userRepository.findByUsername(username);

        List<EventItemEntity> list = eventItemRepository.findAllByParent(eventRepository.findById(eventId));

        UserEventKey key = new UserEventKey(eventId, userEntity.getId(),roleEntity.getId());
        userEventRepository.deleteById(key);
        try {
            List<UserEventItemEntity> itemEntityList = userEventItemRepository.getAllByUserAndRole(userEntity, roleEntity)
                    .stream().filter(eventItemEntity -> list.contains(eventItemEntity.getItem())).collect(Collectors.toList());
            itemEntityList.forEach(e -> userEventItemRepository.delete(e));
        } catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

}
