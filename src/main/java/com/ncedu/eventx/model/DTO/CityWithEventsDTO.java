package com.ncedu.eventx.model.DTO;

import java.util.Set;

public class CityWithEventsDTO {
    private int id;
    private String cityId;
    private String name;
    private Set<EventDTO> events;

    public CityWithEventsDTO() {
    }

    public CityWithEventsDTO(int id, String cityId,
                             String name, Set<EventDTO> events) {
        this.id = id;
        this.cityId = cityId;
        this.name = name;
        this.events = events;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(Set<EventDTO> events) {
        this.events = events;
    }
}
