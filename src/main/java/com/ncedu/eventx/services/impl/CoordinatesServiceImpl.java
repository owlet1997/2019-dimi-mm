package com.ncedu.eventx.services.impl;

import com.ncedu.eventx.converters.CoordinatesMapper;
import com.ncedu.eventx.models.DTO.CoordinatesDTO;
import com.ncedu.eventx.models.entities.CoordinatesEntity;
import com.ncedu.eventx.repositories.CoordinatesRepository;
import com.ncedu.eventx.services.CoordinatesService;
import org.locationtech.jts.geom.Point;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordinatesServiceImpl implements CoordinatesService {

    final CoordinatesRepository coordinatesRepository;

    public CoordinatesServiceImpl(CoordinatesRepository coordinatesRepository) {
        this.coordinatesRepository = coordinatesRepository;
    }

    CoordinatesMapper coordinatesMapper = Mappers.getMapper(CoordinatesMapper.class);

    @Override
    public CoordinatesEntity createPlace(String name, Point coord) {
        CoordinatesEntity coordinatesEntity = new CoordinatesEntity();
        coordinatesEntity.setName(name);
        coordinatesEntity.setPoint(coord);
        coordinatesRepository.save(coordinatesEntity);
        return coordinatesEntity;
    }

    @Override
    public CoordinatesDTO getPlaceById(int id) {
        CoordinatesEntity coordinatesEntity = coordinatesRepository.findById(id);
        return coordinatesMapper.toCoordinatesDTO(coordinatesEntity);
    }

    @Override
    public List<CoordinatesDTO> getAllPlaces() {
        List<CoordinatesEntity> entities = coordinatesRepository.findAll();
        return coordinatesMapper.toCoordinatesDTOList(entities);
    }

    @Override
    public CoordinatesEntity getPlaceByName(String name) {
        return coordinatesRepository.findByName(name);
    }


}
