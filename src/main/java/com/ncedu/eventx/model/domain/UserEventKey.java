package com.ncedu.eventx.model.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserEventKey implements Serializable {

    @Column(name = "event_id")
    int eventId;

    @Column(name = "user_id")
    int userId;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEventKey that = (UserEventKey) o;
        return eventId == that.eventId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, userId);
    }
}
