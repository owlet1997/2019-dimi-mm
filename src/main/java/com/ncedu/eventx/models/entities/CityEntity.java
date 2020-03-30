package com.ncedu.eventx.models.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="city", schema = "eventx")
public class CityEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String abbrev;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "city", cascade = {CascadeType.REFRESH, CascadeType.MERGE}, orphanRemoval = true)
    private List<EventEntity> events = new ArrayList<>();

}
