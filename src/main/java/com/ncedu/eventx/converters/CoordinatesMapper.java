package com.ncedu.eventx.converters;

import com.ncedu.eventx.models.DTO.CoordinatesDTO;
import com.ncedu.eventx.models.entities.CoordinatesEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CoordinatesMapper {

    CoordinatesDTO toCoordinatesDTO(CoordinatesEntity entity);

    CoordinatesEntity toCoordinatesEntity(CoordinatesDTO coordinatesDTO);

    List<CoordinatesDTO> toCoordinatesDTOList(List<CoordinatesEntity> list);

}
