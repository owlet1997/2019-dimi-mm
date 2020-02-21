package com.ncedu.eventx.model.DTO;

import java.time.LocalDate;

public class EventDTO {

    private int id;
    private CityDTO city;
    private String name;
    private EventTypeDTO eventType;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String description;

    public EventDTO(int id, CityDTO city, String name,
                    EventTypeDTO eventType, LocalDate dateStart,
                    LocalDate dateEnd, String description) {
        this.id = id;
        this.city = city;
        this.name = name;
        this.eventType = eventType;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventTypeDTO getEventType() {
        return eventType;
    }

    public void setEventType(EventTypeDTO eventType) {
        this.eventType = eventType;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
