package com.ncedu.eventx.services;

import com.ncedu.eventx.models.DTO.EventTypeDTO;

import java.util.List;

public interface EventTypeService {

    boolean createEventType(EventTypeDTO eventTypeDTO);

    List<EventTypeDTO> getAllEventTypes();

    EventTypeDTO getEventTypeByName(String name);

}
