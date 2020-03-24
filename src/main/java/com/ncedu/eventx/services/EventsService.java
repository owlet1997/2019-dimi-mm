package com.ncedu.eventx.services;
import com.ncedu.eventx.models.DTO.EventDTO;
import com.ncedu.eventx.models.DTO.EventForCreateDTO;
import com.ncedu.eventx.models.DTO.EventWithItemsDTO;
import com.ncedu.eventx.models.DTO.EventWithUsersDTO;
import com.ncedu.eventx.models.entities.EventEntity;

import java.util.List;

public interface EventsService {

    EventEntity createEvent(EventForCreateDTO createDTO);

    public EventDTO getEventById(int id);

    List<EventDTO> getEventsList();

    EventWithItemsDTO getEventWithItemsById(int id);

    List<EventWithItemsDTO> getEventsBySearchParams(String city, String type, String dateStart);

    List<EventWithItemsDTO> getEventsWithItemsList(List<EventEntity> eventEntityList);

    EventWithUsersDTO getEventWithUsers(int id);

    List<EventDTO> getLastEventsByCreator(int userId);
}
