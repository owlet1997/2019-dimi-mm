package com.ncedu.eventx.model.domain;

import javax.persistence.*;
import java.io.Serializable;
import oracle.spatial.geometry.JGeometry;

@Entity
@Table(name="COORDINATES")
public class CoordinatesDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name="coordinates")
    private JGeometry coordinates;


    public CoordinatesDAO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JGeometry getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(JGeometry coordinates) {
        this.coordinates = coordinates;
    }
}
