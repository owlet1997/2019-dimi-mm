package com.ncedu.eventx.model.DTO;

import java.util.List;

public class UserEventDTO {
    private EventDTO event;
    private List<UserDTO> users;
    private int showOrder;

    public UserEventDTO() {
    }

    public UserEventDTO(EventDTO event, List<UserDTO> users, int showOrder) {
        this.event = event;
        this.users = users;
        this.showOrder = showOrder;
    }

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    public int getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(int showOrder) {
        this.showOrder = showOrder;
    }
}
