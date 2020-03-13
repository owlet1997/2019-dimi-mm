package com.ncedu.eventx.services;

import com.ncedu.eventx.models.DTO.EventDTO;
import com.ncedu.eventx.models.DTO.UserDTO;
import com.ncedu.eventx.models.DTO.UserRoleDTO;
import com.ncedu.eventx.models.entities.UserEventEntity;

import java.util.List;

public interface UserEventService {
    List<UserEventEntity> getAllList();

    List<UserEventEntity> getAllByEvent(EventDTO eventDTO);

    List<UserEventEntity> getAllByRole(UserRoleDTO roleDTO);

    List<UserEventEntity> getAllByUser(UserDTO userDTO);

    boolean createEvent(UserDTO user, EventDTO eventDTO);

    boolean visitEvent(UserDTO user, EventDTO eventDTO);

    boolean deleteVisit(UserDTO userDTO, EventDTO eventDTO);


}
