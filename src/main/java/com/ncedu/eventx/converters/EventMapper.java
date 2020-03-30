package com.ncedu.eventx.converters;

import com.ncedu.eventx.models.DTO.EventDTO;
import com.ncedu.eventx.models.DTO.EventWithItemsDTO;
import com.ncedu.eventx.models.entities.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface EventMapper {

    EventDTO toDTO(EventEntity eventEntity);

    List<EventDTO> toListDTO(List<EventEntity> list);

    EventEntity toEventEntity(EventDTO eventDTO);

    EventWithItemsDTO toEventWithItemsDTO(EventEntity eventEntity);


}
