package com.ncedu.eventx.converters;

import com.ncedu.eventx.models.DTO.EventTypeDTO;
import com.ncedu.eventx.models.entities.EventTypeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface EventTypesMapper {

   List<EventTypeDTO> getEventTypeDTOs(List<EventTypeEntity> eventTypeEntityList);

   EventTypeEntity toEventTypeEntity(EventTypeDTO eventTypeDTO);

   EventTypeDTO toEventTypeDTO(EventTypeEntity eventTypeEntity);
}
