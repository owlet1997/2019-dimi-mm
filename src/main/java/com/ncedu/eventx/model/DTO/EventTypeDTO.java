package com.ncedu.eventx.model.DTO;

public class EventTypeDTO {

    private int id;
    private String type;

    public EventTypeDTO() {
    }

    public EventTypeDTO(int id, String type) {
        this.id = id;
        this.type = type;
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
}
