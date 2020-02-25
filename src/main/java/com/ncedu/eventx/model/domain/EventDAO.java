package com.ncedu.eventx.model.domain;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="EVENT")
public class EventDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id", nullable = false)
    private EventTypeDAO type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id", nullable = false)
    private CityDAO city;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "coord_id", nullable = false)
    private CoordinatesDAO coordinates;

    @OneToMany(mappedBy = "eventId")
    Set<UserEventDAO> userEvents;

    @OneToMany(mappedBy = "parentId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    HashSet<EventItemDAO> items;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "TIME_START", nullable = false)
    private Date timeStart;

    @Temporal(TemporalType.DATE)
    @Column(name = "TIME_END", nullable = false)
    private Date timeEnd;

    public int getId() {
        return id;
    }

    public EventTypeDAO getType() {
        return type;
    }

    public void setType(EventTypeDAO type) {
        this.type = type;
    }

    public CityDAO getCity() {
        return city;
    }

    public void setCity(CityDAO city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public CoordinatesDAO getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinatesDAO coordinates) {
        this.coordinates = coordinates;
    }

    public Set<UserEventDAO> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(Set<UserEventDAO> userEvents) {
        this.userEvents = userEvents;
    }

    public HashSet<EventItemDAO> getItems() {
        return items;
    }

    public void setItems(HashSet<EventItemDAO> items) {
        this.items = items;
    }
}
