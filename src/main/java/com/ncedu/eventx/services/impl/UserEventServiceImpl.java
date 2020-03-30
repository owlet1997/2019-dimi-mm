package com.ncedu.eventx.services.impl;

import com.ncedu.eventx.models.DTO.*;


import com.ncedu.eventx.models.entities.*;
import com.ncedu.eventx.repositories.EventRepository;
import com.ncedu.eventx.repositories.RolesRepository;
import com.ncedu.eventx.repositories.UserEventRepository;
import com.ncedu.eventx.repositories.UserRepository;
import com.ncedu.eventx.services.EventsService;
import com.ncedu.eventx.services.UserEventService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ncedu.eventx.enums.UserRoleItems.CREATOR;
import static com.ncedu.eventx.enums.UserRoleItems.VISITOR;

@Service
public class UserEventServiceImpl implements UserEventService {

    final UserEventRepository userEventRepository;
    final EventRepository eventRepository;
    final RolesRepository rolesRepository;
    final UserRepository userRepository;
    final EventsService eventsService;

    public UserEventServiceImpl(UserEventRepository userEventRepository,
                                EventRepository eventRepository,
                                RolesRepository rolesRepository,
                                UserRepository userRepository,
                                EventsService eventsService) {
        this.userEventRepository = userEventRepository;
        this.eventRepository = eventRepository;
        this.rolesRepository = rolesRepository;
        this.userRepository = userRepository;
        this.eventsService = eventsService;
    }

    @Override
    public List<UserEventEntity> getAllList() {
        return userEventRepository.findAll();
    }

    @Override
    public List<UserEventEntity> getAllByEvent(EventDTO eventDTO) {
        EventEntity entity = eventRepository.findById(eventDTO.getId());
        return userEventRepository.findAllByEvent(entity);
    }

    @Override

    public List<UserEventEntity> getAllByRole(RoleDTO roleDTO) {
        RoleEntity entity = rolesRepository.findByName(roleDTO.getName());

        return userEventRepository.findAllByRole(entity);
    }

    @Override
    public List<UserEventEntity> getAllByUser(UserDTO userDTO) {
        UserEntity entity = userRepository.findById(userDTO.getId());
        return userEventRepository.findAllByUser(entity);
    }

    @Override
    public boolean createEvent(EventForCreateDTO createDTO) {
        UserEntity userEntity = userRepository.findById(Integer.parseInt(createDTO.getUserId()));
        EventEntity eventEntity = eventsService.createEvent(createDTO);

        RoleEntity roleEntity = rolesRepository.findByName(CREATOR.getDescription());


        UserEventKey key = new UserEventKey(eventEntity.getId(),userEntity.getId(),roleEntity.getId());
        UserEventEntity userEventEntity = new UserEventEntity(key,userEntity,eventEntity,roleEntity,1);
        userEventRepository.save(userEventEntity);
        return true;
    }


    @Override
    public boolean visitEvent(int userId, int eventId) {
        if (isVisited(userId, eventId)){
            deleteVisit(userId, eventId);
            return false;
        }
        else {
            UserEntity userEntity = userRepository.findById(userId);
            EventEntity eventEntity = eventRepository.findById(eventId);
            RoleEntity roleEntity = rolesRepository.findByName(VISITOR.getDescription());

            UserEventKey key = new UserEventKey(eventEntity.getId(),userEntity.getId(),roleEntity.getId());
            UserEventEntity userEventEntity = new UserEventEntity(key,userEntity,eventEntity,roleEntity,1);
            userEventRepository.save(userEventEntity);
        }
        return true;
    }

    @Override
    public boolean isVisited(int userId, int eventId) {
        UserEntity userEntity = userRepository.findById(userId);
        EventEntity eventEntity = eventRepository.findById(eventId);
        RoleEntity roleEntity = rolesRepository.findByName(VISITOR.getDescription());

        List<UserEventEntity> list = userEventRepository.findAllByEvent(eventEntity);

        boolean visitor = list.stream().filter(role -> role.getRole().equals(roleEntity))
                .filter(user -> user.getUser().equals(userEntity))
                .map(UserEventEntity::getUser).findAny().isPresent();

        return visitor;
    }

    @Override
    public boolean deleteVisit(int userId, int eventId) {
        RoleEntity roleEntity = rolesRepository.findByName(VISITOR.getDescription());

        UserEventKey key = new UserEventKey(eventId,userId,roleEntity.getId());

        userEventRepository.deleteById(key);
        return true;
    }

}
