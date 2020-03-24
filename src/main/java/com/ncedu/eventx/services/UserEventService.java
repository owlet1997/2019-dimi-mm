package com.ncedu.eventx.services;

import com.ncedu.eventx.models.DTO.EventDTO;
import com.ncedu.eventx.models.DTO.EventForCreateDTO;
import com.ncedu.eventx.models.DTO.UserDTO;
import com.ncedu.eventx.models.DTO.UserRoleDTO;
import com.ncedu.eventx.models.entities.UserEventEntity;

import java.util.List;

public interface UserEventService {
    List<UserEventEntity> getAllList();

    List<UserEventEntity> getAllByEvent(EventDTO eventDTO);

    List<UserEventEntity> getAllByRole(UserRoleDTO roleDTO);

    List<UserEventEntity> getAllByUser(UserDTO userDTO);

    boolean createEvent(EventForCreateDTO createDTO);

    boolean visitEvent(int userId, int evenId);

    boolean deleteVisit(int userId, int eventId);

    boolean isVisited(int userId, int evenId);

}
