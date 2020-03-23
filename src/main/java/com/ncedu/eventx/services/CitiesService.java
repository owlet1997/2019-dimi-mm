package com.ncedu.eventx.services;

import com.ncedu.eventx.models.DTO.CityDTO;
import com.ncedu.eventx.models.DTO.CityWithEventsDTO;

import java.util.List;

public interface CitiesService {

    boolean createCity(CityDTO cityDTO);

    List<CityDTO> getCitiesList();

    CityWithEventsDTO getCityWithEventsById(int cityId);

    CityDTO getCityByName(String name);

}
