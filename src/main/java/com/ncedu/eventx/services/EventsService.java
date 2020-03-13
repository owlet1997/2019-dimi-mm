package com.ncedu.eventx.services;
import com.ncedu.eventx.models.DTO.EventDTO;
import com.ncedu.eventx.models.DTO.EventWithItemsDTO;
import com.ncedu.eventx.models.DTO.EventWithUsersDTO;

import java.util.List;

public interface EventsService {

    boolean createEvent(EventDTO event);

    public EventDTO getEventById(int id);

    List<EventDTO> getEventsList();

    EventWithItemsDTO getEventWithItemsById(int id);

    List<EventDTO> getEventsBySearchParams(String city, String type, String dateStart);

    List<EventWithItemsDTO> getEventsWithItemsList();

    EventWithUsersDTO getEventWithUsers(int id);
}
