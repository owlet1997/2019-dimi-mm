package com.ncedu.eventx.models.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ncedu.eventx.serializers.JsonToPointDeserializer;
import com.ncedu.eventx.serializers.PointToJsonSerializer;
import lombok.*;
import org.locationtech.jts.geom.Point;

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

    @Column(columnDefinition="geometry(Point,4326)",nullable = false)
    @JsonSerialize(using = PointToJsonSerializer.class)
    @JsonDeserialize(using = JsonToPointDeserializer.class)
    private Point point;

}
