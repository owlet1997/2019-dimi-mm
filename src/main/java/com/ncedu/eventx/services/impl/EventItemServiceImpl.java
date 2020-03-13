package com.ncedu.eventx.services.impl;

import com.ncedu.eventx.converters.EventItemMapper;
import com.ncedu.eventx.converters.EventMapper;
import com.ncedu.eventx.converters.UsersMapper;
import com.ncedu.eventx.models.DTO.EventItemDTO;
import com.ncedu.eventx.models.DTO.EventItemWithUsersDTO;
import com.ncedu.eventx.models.entities.*;
import com.ncedu.eventx.repositories.EventItemRepository;
import com.ncedu.eventx.repositories.EventRepository;
import com.ncedu.eventx.repositories.RolesRepository;
import com.ncedu.eventx.services.EventItemService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.ncedu.eventx.enums.UserRoleItems.SPEAKER;
import static com.ncedu.eventx.enums.UserRoleItems.VISITOR;

@Service
public class EventItemServiceImpl implements EventItemService {

    final EventItemRepository eventItemRepository;
    final EventRepository eventRepository;
    final RolesRepository rolesRepository;

    public EventItemServiceImpl(EventItemRepository eventItemRepository, EventRepository eventRepository, RolesRepository rolesRepository) {
        this.eventItemRepository = eventItemRepository;
        this.eventRepository = eventRepository;
        this.rolesRepository = rolesRepository;
    }

    EventMapper eventMapper = Mappers.getMapper(EventMapper.class);
    EventItemMapper eventItemMapper = Mappers.getMapper(EventItemMapper.class);
    UsersMapper usersMapper = Mappers.getMapper(UsersMapper.class);

    @Override
    public EventItemEntity createEventItem(EventItemDTO eventItemDTO) {
        EventItemEntity eventItemEntity = new EventItemEntity();

        EventEntity parentEntity = eventMapper.toEventEntity(eventItemDTO.getParentId());

        eventItemEntity.setParent(parentEntity);
        eventItemEntity.setName(eventItemDTO.getName());
        eventItemEntity.setTimeStart(eventItemDTO.getTimeStart());

        eventItemRepository.save(eventItemEntity);
        return eventItemEntity;
    }

    @Override
    public List<EventItemDTO> getEventItemsListByParent(int id) {
        EventEntity eventEntity = eventRepository.findById(id);
        List<EventItemEntity> list = eventItemRepository.findAllByParent(eventEntity);
        return eventItemMapper.toListDTO(list);

    }

    @Override
    public EventItemWithUsersDTO getEventItemWithUsers(EventItemDTO eventItemDTO) {
        EventItemEntity itemEntity = eventItemRepository.findById(eventItemDTO.getId());

        UserRoleEntity userRoleVisit = rolesRepository.findByName(VISITOR.getDescription());
        UserRoleEntity userRoleSpeaker = rolesRepository.findByName(SPEAKER.getDescription());

        List<UserEventItemEntity> list = itemEntity.getUserEventItems();

        List<UserEntity> usersList = list.stream()
                .filter(e -> e.getRole().equals(userRoleVisit))
                .map(UserEventItemEntity::getUser).collect(Collectors.toList());;

        UserEntity speaker = list.stream().filter(e -> e.getRole().equals(userRoleSpeaker))
                            .map(UserEventItemEntity::getUser).findAny().get();

        return new EventItemWithUsersDTO(eventItemDTO,usersMapper.toDTO(speaker),usersMapper.toUserDTOList(usersList));
    }
}
