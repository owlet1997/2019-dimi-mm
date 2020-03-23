package com.ncedu.eventx.converters;

import com.ncedu.eventx.models.DTO.EventItemDTO;
import com.ncedu.eventx.models.entities.EventItemEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface EventItemMapper {

    EventItemDTO toEventItemDTO(EventItemEntity eventItemEntity);

    List<EventItemDTO> toListDTO(List<EventItemEntity> entityList);
}
