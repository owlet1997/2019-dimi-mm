package com.ncedu.eventx.services;

import com.ncedu.eventx.models.DTO.*;

import com.ncedu.eventx.models.entities.UserEventEntity;

import java.util.List;

public interface UserEventService {
    List<UserEventEntity> getAllList();

    List<UserEventEntity> getAllByEvent(EventDTO eventDTO);


    List<UserEventEntity> getAllByRole(RoleDTO roleDTO);


    List<UserEventEntity> getAllByUser(UserDTO userDTO);

    boolean createEvent(EventForCreateDTO createDTO, String username);

    boolean visitEvent(String username, int evenId);

    boolean deleteVisit(String username, int eventId);

    boolean isVisited(String username, int evenId);

}
