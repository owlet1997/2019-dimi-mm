package com.ncedu.eventx.services;
import com.ncedu.eventx.models.DTO.EventDTO;
import com.ncedu.eventx.models.DTO.EventForCreateDTO;
import com.ncedu.eventx.models.DTO.EventWithItemsDTO;
import com.ncedu.eventx.models.DTO.EventWithUsersDTO;
import com.ncedu.eventx.models.entities.EventEntity;

import java.util.List;

public interface EventsService {

    EventEntity createEvent(EventForCreateDTO createDTO);

    EventDTO getEventById(int id);

    List<EventDTO> getEventsList();

    boolean deleteEventById(int id);

    List<EventDTO> getEventsByUserId(int userId, String role);

    EventWithItemsDTO getEventWithItemsById(int id, String username);

    List<EventWithItemsDTO> getEventsBySearchParams(String city, String type, String dateStart);

    List<EventWithItemsDTO> getEventsWithItemsList(List<EventEntity> eventEntityList);

    EventWithUsersDTO getEventWithUsers(int id);

    List<EventDTO> getLastEventsByCreator(int userId);
}
