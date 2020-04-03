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
    public boolean createEvent(EventForCreateDTO createDTO, String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        EventEntity eventEntity = eventsService.createEvent(createDTO);

        System.out.println(createDTO);
        System.out.println(username);

        RoleEntity roleEntity = rolesRepository.findByName(CREATOR.getDescription());

        UserEventKey key = new UserEventKey(eventEntity.getId(),userEntity.getId(),roleEntity.getId());
        UserEventEntity userEventEntity = new UserEventEntity(key,userEntity,eventEntity,roleEntity,1);
        userEventRepository.save(userEventEntity);
        return true;
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

        UserEventKey key = new UserEventKey(eventId, userEntity.getId(),roleEntity.getId());

        userEventRepository.deleteById(key);
        return true;
    }

}
