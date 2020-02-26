package com.ncedu.eventx.models.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Getter
@Setter
@Entity
@Table(name="event", schema = "eventx")
public class EventEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private EventTypeEntity type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private CityEntity city;

    @Column(nullable = false)
    private float latitude;

    @Column(nullable = false)
    private float longtitude;

    @OneToMany(mappedBy = "eventId")
    Set<UserEventEntity> userEvents;

    @OneToMany(mappedBy = "parentId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    Set<EventItemEntity> items;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "time_start", nullable = false)
    private Date timeStart;

    @Temporal(TemporalType.DATE)
    @Column(name = "time_end", nullable = false)
    private Date timeEnd;

}
