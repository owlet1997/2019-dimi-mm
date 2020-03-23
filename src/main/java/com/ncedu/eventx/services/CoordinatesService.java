package com.ncedu.eventx.services;

import com.ncedu.eventx.models.DTO.CoordinatesDTO;
import com.ncedu.eventx.models.entities.CoordinatesEntity;
import org.locationtech.jts.geom.Point;

import java.util.List;

public interface CoordinatesService {

    CoordinatesEntity createPlace(String name, Point coord);

    CoordinatesDTO getPlaceById(int id);

    CoordinatesEntity getPlaceByName(String name);

    List<CoordinatesDTO> getAllPlaces();

}
