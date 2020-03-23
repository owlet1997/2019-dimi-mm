package com.ncedu.eventx.converters;

import com.ncedu.eventx.models.DTO.CityDTO;
import com.ncedu.eventx.models.DTO.CityWithEventsDTO;
import com.ncedu.eventx.models.entities.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CitiesMapper {

    CityDTO toDTO(CityEntity cityEntity);

    CityEntity toCityEntity(CityDTO cityDTO);

    List<CityDTO> toCitiesDTO(List<CityEntity> cityEntityList);

    @Mapping(target = "events", source = "cityEntity.events", ignore = true)
    CityWithEventsDTO toCityWithEventsDTOs(CityEntity cityEntity);
}
