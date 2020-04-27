package com.ncedu.eventx.services;
import com.ncedu.eventx.models.DTO.*;
import com.ncedu.eventx.models.entities.EventEntity;

import java.util.List;

public interface EventsService {

    EventEntity createEvent(EventForCreateDTO createDTO);

    boolean deleteEventById(int id);

    List<EventDTO> getEventsByUserId(int userId, String role);

    EventWithItemsDTO getEventWithItemsById(int id, String username);

    List<EventWithItemsDTO> getEventsBySearchParams(String city, String type, String dateStart);

    List<EventWithItemsDTO> getEventsWithItemsList(List<EventEntity> eventEntityList);

    List<EventDTO> getLastEventsByCreator(int userId);

    boolean cancelEventById(int eventId);

    EventForUpdateDTO updateEventById(EventForUpdateDTO event);
}
