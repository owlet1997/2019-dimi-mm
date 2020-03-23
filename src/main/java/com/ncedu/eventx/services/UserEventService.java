package com.ncedu.eventx.services;

import com.ncedu.eventx.models.DTO.EventDTO;
import com.ncedu.eventx.models.DTO.EventForCreateDTO;
import com.ncedu.eventx.models.DTO.UserDTO;
import com.ncedu.eventx.models.DTO.RoleDTO;
import com.ncedu.eventx.models.entities.UserEventEntity;

import java.util.List;

public interface UserEventService {
    List<UserEventEntity> getAllList();

    List<UserEventEntity> getAllByEvent(EventDTO eventDTO);

    List<UserEventEntity> getAllByRole(RoleDTO roleDTO);

    List<UserEventEntity> getAllByUser(UserDTO userDTO);

    boolean createEvent(EventForCreateDTO createDTO);

    boolean visitEvent(UserDTO user, EventDTO eventDTO);

    boolean deleteVisit(UserDTO userDTO, EventDTO eventDTO);


}
