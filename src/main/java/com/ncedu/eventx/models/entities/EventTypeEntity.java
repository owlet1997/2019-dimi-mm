package com.ncedu.eventx.models.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="event_type", schema = "eventx")
public class EventTypeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String type;

    @OneToMany(mappedBy = "type",
            cascade = {CascadeType.REFRESH, CascadeType.MERGE},
            orphanRemoval = true)
    private List<EventEntity> events = new ArrayList<>();

}
