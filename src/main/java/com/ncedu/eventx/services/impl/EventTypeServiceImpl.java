package com.ncedu.eventx.services.impl;

import com.ncedu.eventx.converters.EventTypesMapper;
import com.ncedu.eventx.models.DTO.EventTypeDTO;
import com.ncedu.eventx.models.entities.EventTypeEntity;
import com.ncedu.eventx.repositories.EventTypeRepository;
import com.ncedu.eventx.services.EventTypeService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventTypeServiceImpl implements EventTypeService {

    final EventTypeRepository eventTypeRepository;

    EventTypesMapper eventTypesMapper = Mappers.getMapper(EventTypesMapper.class);

    public EventTypeServiceImpl(EventTypeRepository eventTypeRepository) {
        this.eventTypeRepository = eventTypeRepository;
    }

    @Override
    public boolean createEventType(EventTypeDTO eventTypeDTO) {
        EventTypeEntity eventTypeEntity = new EventTypeEntity();
        eventTypeEntity.setType(eventTypeDTO.getType());
        eventTypeRepository.save(eventTypeEntity);
        return true;
    }

    @Override
    public List<EventTypeDTO> getAllEventTypes() {
        List<EventTypeEntity> entityList = eventTypeRepository.findAll();
        return eventTypesMapper.getEventTypeDTOs(entityList);
    }

    @Override
    public List<EventTypeDTO> getAllEventTypes(int id) {
        List<EventTypeEntity> entityList = eventTypeRepository.findAll();
        EventTypeEntity entity = eventTypeRepository.findById(id);
        entityList.remove(entity);
        return eventTypesMapper.getEventTypeDTOs(entityList);

    }
}
