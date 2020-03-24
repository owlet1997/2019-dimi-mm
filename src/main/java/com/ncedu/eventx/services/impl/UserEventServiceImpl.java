package com.ncedu.eventx.services.impl;

import com.ncedu.eventx.models.DTO.EventDTO;
import com.ncedu.eventx.models.DTO.EventForCreateDTO;
import com.ncedu.eventx.models.DTO.UserDTO;
import com.ncedu.eventx.models.DTO.UserRoleDTO;
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
    public List<UserEventEntity> getAllByRole(UserRoleDTO roleDTO) {
        UserRoleEntity entity = rolesRepository.findByName(roleDTO.getName());
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
        UserRoleEntity roleEntity = rolesRepository.findByName(CREATOR.getDescription());

        UserEventKey key = new UserEventKey(eventEntity.getId(),userEntity.getId(),roleEntity.getId());
        UserEventEntity userEventEntity = new UserEventEntity(key,userEntity,eventEntity,roleEntity,1);
        userEventRepository.save(userEventEntity);
        return true;
    }


    @Override
    public boolean visitEvent(int userId, int eventId) {
        UserEntity userEntity = userRepository.findById(userId);
        EventEntity eventEntity = eventRepository.findById(eventId);
        UserRoleEntity roleEntity = rolesRepository.findByName(VISITOR.getDescription());

        UserEventKey key = new UserEventKey(eventEntity.getId(),userEntity.getId(),roleEntity.getId());
        UserEventEntity userEventEntity = new UserEventEntity(key,userEntity,eventEntity,roleEntity,1);
        userEventRepository.save(userEventEntity);
        return true;
    }

    @Override
    public boolean isVisited(int userId, int eventId) {
        UserEntity userEntity = userRepository.findById(userId);
        EventEntity eventEntity = eventRepository.findById(eventId);
        UserRoleEntity roleEntity = rolesRepository.findByName(VISITOR.getDescription());

        List<UserEventEntity> list = userEventRepository.findAllByEvent(eventEntity);

        UserEntity visitor = list.stream().filter(role -> role.getRole().equals(roleEntity))
                .filter(user -> user.getUser().equals(userEntity))
                .map(UserEventEntity::getUser).findAny().get();

        return visitor != null;
    }

    @Override
    public boolean deleteVisit(int userId, int eventId) {
        UserRoleEntity roleEntity = rolesRepository.findByName(VISITOR.getDescription());

        UserEventKey key = new UserEventKey(roleEntity.getId(),userId,eventId);
        UserEventEntity userEventEntity = userEventRepository.findById(key).get();

        userEventRepository.delete(userEventEntity);
        return true;
    }

}
