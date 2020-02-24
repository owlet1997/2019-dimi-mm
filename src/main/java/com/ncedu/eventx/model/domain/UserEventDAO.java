package com.ncedu.eventx.model.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="USER_EVENT")
public class UserEventDAO implements Serializable {

    @EmbeddedId
    UserEventKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id", nullable = false)
    private UserDAO userId;

    @ManyToOne
    @MapsId("event_id")
    @JoinColumn(name = "event_id", nullable = false)
    private EventDAO eventId;

    @Column(name = "SHOW_ORDER", nullable = false)
    private int showOrder;

    public UserEventDAO() {
    }

    public UserEventDAO(UserDAO userId, EventDAO eventId, int showOrder) {
        this.userId = userId;
        this.eventId = eventId;
        this.showOrder = showOrder;
    }

    public UserDAO getUserId() {
        return userId;
    }

    public void setUserId(UserDAO userId) {
        this.userId = userId;
    }

    public EventDAO getEventId() {
        return eventId;
    }

    public void setEventId(EventDAO eventId) {
        this.eventId = eventId;
    }
}
