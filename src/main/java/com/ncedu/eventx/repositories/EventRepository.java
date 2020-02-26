package com.ncedu.eventx.repositories;

import com.ncedu.eventx.models.entities.CityEntity;
import com.ncedu.eventx.models.entities.EventEntity;
import com.ncedu.eventx.models.entities.EventTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<EventEntity, Integer> {

    List<EventEntity> findAllByCity(CityEntity city);

    List<EventEntity> findAll();

    List<EventEntity> findAllByType(EventTypeEntity type);

    List<EventEntity> findById(int id);

}
