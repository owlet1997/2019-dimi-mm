package com.ncedu.eventx.model.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="CITY")
public class CityDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "CITYID", nullable = false)
    private String cityId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventDAO> events;

    public int getId() {
        return id;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EventDAO> getEvents() {
        return events;
    }

    public void setEvents(List<EventDAO> events) {
        this.events = events;
    }
}
