package com.ncedu.eventx.model.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="EVENT")
public class EventDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("id")
    @Column(name = "TYPE", nullable = false)
    private EventTypeDAO type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("id")
    @Column(name = "CITY", nullable = false)
    private CityDAO city;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("id")
    @Column(name = "CITY", nullable = false)
    private CoordinatesDAO coordinates;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "TIME_START", nullable = false)
    private LocalDate timeStart;

    @Column(name = "TIME_END", nullable = false)
    private LocalDate timeEnd;

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

    public LocalDate getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDate timeStart) {
        this.timeStart = timeStart;
    }

    public LocalDate getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDate timeEnd) {
        this.timeEnd = timeEnd;
    }

    public CoordinatesDAO getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinatesDAO coordinates) {
        this.coordinates = coordinates;
    }
}
