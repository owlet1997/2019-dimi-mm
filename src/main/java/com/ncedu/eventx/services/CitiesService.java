package com.ncedu.eventx.services;

import com.ncedu.eventx.models.DTO.CityDTO;

import java.util.List;

public interface CitiesService {

    boolean createCity(CityDTO cityDTO);

    List<CityDTO> getCitiesList();

    List<CityDTO> getCitiesList(String abbrev);

    CityDTO getCityByName(String name);

}
