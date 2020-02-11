package com.ncedu.eventx.model.domain;

import javax.persistence.*;

@Embeddable
@Table(name="USER_EVENT")
public class UserEventModel {

    @ManyToOne
    @MapsId("id")
    @Column(name = "USER_ID", nullable = false)
    private UserModel userId;

    @ManyToOne
    @MapsId("id")
    @Column(name = "EVENT_ID", nullable = false)
    private EventModel eventId;

    @Column(name = "SHOW_ORDER", nullable = false)
    private int showOrder;

    public UserEventModel() {
    }

    public UserEventModel(UserModel userId, EventModel eventId, int showOrder) {
        this.userId = userId;
        this.eventId = eventId;
        this.showOrder = showOrder;
    }

    public UserModel getUserId() {
        return userId;
    }

    public void setUserId(UserModel userId) {
        this.userId = userId;
    }

    public EventModel getEventId() {
        return eventId;
    }

    public void setEventId(EventModel eventId) {
        this.eventId = eventId;
    }
}
