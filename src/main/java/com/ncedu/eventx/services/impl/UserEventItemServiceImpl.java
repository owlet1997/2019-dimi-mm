package com.ncedu.eventx.services.impl;

import com.ncedu.eventx.models.DTO.EventItemDTO;
import com.ncedu.eventx.models.DTO.EventWithItemsDTO;
import com.ncedu.eventx.models.DTO.UserDTO;
import com.ncedu.eventx.models.entities.*;
import com.ncedu.eventx.repositories.EventItemRepository;
import com.ncedu.eventx.repositories.RolesRepository;
import com.ncedu.eventx.repositories.UserEventItemRepository;
import com.ncedu.eventx.repositories.UserRepository;
import com.ncedu.eventx.services.EventItemService;
import com.ncedu.eventx.services.EventsService;
import com.ncedu.eventx.services.UserEventItemService;
import com.ncedu.eventx.services.UserEventService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ncedu.eventx.enums.UserRoleItems.SPEAKER;
import static com.ncedu.eventx.enums.UserRoleItems.VISITOR;

@Service
public class UserEventItemServiceImpl implements UserEventItemService {

    final UserRepository userRepository;
    final RolesRepository rolesRepository;
    final UserEventItemRepository userEventItemRepository;
    final EventItemRepository eventItemRepository;
    final EventItemService eventItemService;
    final UserEventService userEventService;
    final EventsService eventsService;

    public UserEventItemServiceImpl(UserRepository userRepository,
                                    RolesRepository rolesRepository,
                                    UserEventItemRepository eventItemRepository,
                                    EventItemRepository eventItemRepository1,
                                    EventItemService eventItemService,
                                    UserEventService userEventService, EventsService eventsService) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.userEventItemRepository = eventItemRepository;
        this.eventItemRepository = eventItemRepository1;
        this.eventItemService = eventItemService;
        this.userEventService = userEventService;
        this.eventsService = eventsService;
    }

    @Override
    public List<UserEventItemEntity> getAll() {
        return null;
    }

    @Override
    public boolean createEventItem(EventItemDTO eventItemDTO, UserDTO user) {
        UserEntity userEntity = userRepository.findById(user.getId());
        EventItemEntity eventItemEntity = eventItemService.createEventItem(eventItemDTO);

        RoleEntity roleEntity = rolesRepository.findByName(SPEAKER.getDescription());

        UserEventItemKey key = new UserEventItemKey(eventItemEntity.getId(),userEntity.getId(), roleEntity.getId());
        UserEventItemEntity entity = new UserEventItemEntity(key,userEntity,eventItemEntity, roleEntity,1);


        userEventItemRepository.save(entity);

        return true;
    }

    @Override
    public EventWithItemsDTO addToFeatured(int itemId, int userId) {
        EventItemEntity itemEntity = eventItemRepository.findById(itemId);
        UserEntity userEntity = userRepository.findById(userId);

        if(isFeatured(itemId, userId)){
            removeFromFeatured(itemId, userId);
        }
        else {
            RoleEntity roleEntity = rolesRepository.findByName(VISITOR.getDescription());

            UserEventItemKey key = new UserEventItemKey(itemEntity.getId(),userEntity.getId(), roleEntity.getId());
            UserEventItemEntity entity = new UserEventItemEntity(key,userEntity,itemEntity, roleEntity,1);
            userEventItemRepository.save(entity);
        }
        return eventsService.getEventWithItemsById(itemEntity.getParent().getId(),userId);
    }

    @Override
    public boolean isFeatured(int itemId, int userId) {
        EventItemEntity itemEntity = eventItemRepository.findById(itemId);
        UserEntity userEntity = userRepository.findById(userId);

        if (!userEventService.isVisited(userId,itemEntity.getParent().getId()))
        userEventService.visitEvent(userId,itemEntity.getParent().getId());

        RoleEntity roleEntity = rolesRepository.findByName(VISITOR.getDescription());

        List<UserEventItemEntity> list = userEventItemRepository.getAllByItem(itemEntity);

        boolean owner = list.stream().filter(role -> role.getRole().equals(roleEntity))
                .filter(user -> user.getUser().equals(userEntity))
                .map(UserEventItemEntity::getUser).findAny().isPresent();

        return owner;
    }

    @Override
    public boolean removeFromFeatured(int itemId, int userId) {
        RoleEntity roleEntity = rolesRepository.findByName(VISITOR.getDescription());

        UserEventItemKey key = new UserEventItemKey(itemId,userId,roleEntity.getId());
        UserEventItemEntity userEventItemEntity = userEventItemRepository.findById(key).get();

        userEventItemRepository.delete(userEventItemEntity);
        return true;
    }
}
