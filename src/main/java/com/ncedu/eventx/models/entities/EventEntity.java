package com.ncedu.eventx.models.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@Table(name="event", schema = "eventx")
public class EventEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(nullable = false)
    private EventTypeEntity type;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.REFRESH)
    @JoinColumn(nullable = false)
    private CityEntity city;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.REFRESH)
    @JoinColumn(nullable = false)
    private CoordinatesEntity coord;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "event",
            cascade = {CascadeType.REFRESH, CascadeType.MERGE},
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    List<UserEventEntity> userEvents = new ArrayList<>();

    @OneToMany(mappedBy = "parent",
            cascade = {CascadeType.REFRESH, CascadeType.MERGE},
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    List<EventItemEntity> items = new ArrayList<>();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_start", nullable = false)
    private Date timeStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_end", nullable = false)
    private Date timeEnd;

    @Column(nullable = false)
    private boolean cancelled;
}
