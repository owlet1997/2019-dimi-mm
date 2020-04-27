package com.ncedu.eventx.services.impl;

import com.ncedu.eventx.converters.CitiesMapper;
import com.ncedu.eventx.models.DTO.CityDTO;
import com.ncedu.eventx.models.entities.CityEntity;
import com.ncedu.eventx.repositories.CitiesRepository;
import com.ncedu.eventx.services.CitiesService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitiesServiceImpl implements CitiesService {

    private final CitiesRepository citiesRepository;
    private final CitiesMapper citiesMapper = Mappers.getMapper(CitiesMapper.class);

    public CitiesServiceImpl(CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }

    @Override
    public List<CityDTO> getCitiesList() {
        List<CityEntity> cityEntityList = citiesRepository.findAll();
        return citiesMapper.toCitiesDTO(cityEntityList);
    }

    @Override
    public List<CityDTO> getCitiesList(String abbrev) {
        List<CityEntity> cityEntityList = citiesRepository.findAll();
        CityEntity entity = citiesRepository.findByAbbrev(abbrev);
        cityEntityList.remove(entity);
        return citiesMapper.toCitiesDTO(cityEntityList);
    }


    @Override
    public boolean createCity(CityDTO cityDTO) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setAbbrev(cityDTO.getAbbrev());
        cityEntity.setName(cityDTO.getName());
        citiesRepository.save(cityEntity);
        return true;
    }

    @Override
    public CityDTO getCityByName(String name) {
        CityEntity cityEntity = citiesRepository.findByName(name);
        return citiesMapper.toDTO(cityEntity);
    }


}
