package com.ncedu.eventx.model.DTO;

import oracle.spatial.geometry.JGeometry;

import javax.persistence.Column;

public class CoordinatesDTO {
    private int id;
    private JGeometry coordinates;


    public CoordinatesDTO() {
    }

    public CoordinatesDTO(int id, JGeometry coordinates) {
        this.id = id;
        this.coordinates = coordinates;
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
