package com.ncedu.eventx.model.DTO;

import java.util.Date;

public class EventItemDTO {

    private int id;

    private EventDTO parentId;

    private String name;

    private Date time;

    public EventItemDTO() {
    }

    public EventItemDTO(int id, EventDTO parentId, String name, Date time) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventDTO getParentId() {
        return parentId;
    }

    public void setParentId(EventDTO parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
