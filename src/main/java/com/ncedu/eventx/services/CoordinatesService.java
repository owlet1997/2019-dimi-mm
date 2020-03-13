package com.ncedu.eventx.services;

import com.ncedu.eventx.models.DTO.CoordinatesDTO;

import java.util.List;

public interface CoordinatesService {

    boolean createPlace(CoordinatesDTO coordinatesDTO);

    CoordinatesDTO getPlaceById(int id);

    CoordinatesDTO getPlaceByName(String name);

    List<CoordinatesDTO> getAllPlaces();

}
