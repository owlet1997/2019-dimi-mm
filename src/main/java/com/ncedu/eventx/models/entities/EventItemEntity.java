package com.ncedu.eventx.models.entities;

import com.ncedu.eventx.models.entities.EventEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@Table(name="event_item", schema = "eventx")
public class EventItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(nullable = false)
    private EventEntity parent;

    @Column(nullable = false)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date timeStart;

    @Column(nullable = false)
    private String auditory;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "item")
    List<UserEventItemEntity> userEventItems = new ArrayList<>();

}
