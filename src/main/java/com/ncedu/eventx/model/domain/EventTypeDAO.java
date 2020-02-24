package com.ncedu.eventx.model.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="EVENT_TYPE")
public class EventTypeDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "TYPE", nullable = false)
    private String type;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventDAO> events;

    public int getId() {
        return id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<EventDAO> getEvents() {
        return events;
    }

    public void setEvents(List<EventDAO> events) {
        this.events = events;
    }
}
