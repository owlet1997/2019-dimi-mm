package com.ncedu.eventx.model.DTO;

import java.util.Set;

public class EventWithUsersDTO {
    private EventDTO event;
    private Set<UserDTO> users;
    private int showOrder;

    public EventWithUsersDTO() {
    }

    public EventWithUsersDTO(EventDTO event, Set<UserDTO> users, int showOrder) {
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

    public Set<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDTO> users) {
        this.users = users;
    }

    public int getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(int showOrder) {
        this.showOrder = showOrder;
    }
}
