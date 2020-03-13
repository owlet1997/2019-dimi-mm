package com.ncedu.eventx.models.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ncedu.eventx.serializers.JsonToPointDeserializer;
import com.ncedu.eventx.serializers.PointToJsonSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="coordinates", schema = "eventx")
public class CoordinatesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @UniqueElements
    private String name;

    @Column(columnDefinition="geometry(Point,4326)",nullable = false)
    @JsonSerialize(using = PointToJsonSerializer.class)
    @JsonDeserialize(using = JsonToPointDeserializer.class)
    private Point point;

    @OneToMany(mappedBy = "coord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventEntity> events = new ArrayList<>();

}


