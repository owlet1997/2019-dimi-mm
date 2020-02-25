package com.ncedu.eventx.model.DTO;

import java.util.Set;

public class UserWithEventsDTO {

    private UserDTO user;
    private Set<EventDTO> events;
    private int showOrder;

    public UserWithEventsDTO() {
    }

    public UserWithEventsDTO(UserDTO user, Set<EventDTO> events, int showOrder) {
        this.user = user;
        this.events = events;
        this.showOrder = showOrder;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Set<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(Set<EventDTO> events) {
        this.events = events;
    }

    public int getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(int showOrder) {
        this.showOrder = showOrder;
    }
}
