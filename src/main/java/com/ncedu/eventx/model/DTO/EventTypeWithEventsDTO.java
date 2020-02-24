package com.ncedu.eventx.model.DTO;

import java.util.Set;

public class EventTypeWithEventsDTO {
    private int id;
    private String type;
    private Set<EventDTO> eventDTOSet;

    public EventTypeWithEventsDTO() {
    }

    public EventTypeWithEventsDTO(int id, String type, Set<EventDTO> eventDTOSet) {
        this.id = id;
        this.type = type;
        this.eventDTOSet = eventDTOSet;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public Set<EventDTO> getEventDTOSet() {
        return eventDTOSet;
    }

    public void setEventDTOSet(Set<EventDTO> eventDTOSet) {
        this.eventDTOSet = eventDTOSet;
    }
}
