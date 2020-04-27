package com.ncedu.eventx.converters;

import com.ncedu.eventx.models.DTO.CityDTO;
import com.ncedu.eventx.models.entities.CityEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CitiesMapper {

    CityDTO toDTO(CityEntity cityEntity);

    CityEntity toCityEntity(CityDTO cityDTO);

    List<CityDTO> toCitiesDTO(List<CityEntity> cityEntityList);

}
