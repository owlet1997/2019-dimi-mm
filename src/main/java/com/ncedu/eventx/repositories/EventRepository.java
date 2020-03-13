package com.ncedu.eventx.repositories;

import com.ncedu.eventx.models.entities.CityEntity;
import com.ncedu.eventx.models.entities.EventEntity;
import com.ncedu.eventx.models.entities.EventTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<EventEntity, Integer> {
    List<EventEntity> findAllByCity(CityEntity city);

    List<EventEntity> findAll();

    List<EventEntity> findAllByType(EventTypeEntity type);

    List<EventEntity> findAllByTimeStart(Date date);

    List<EventEntity> findAllByTypeAndTimeStart(EventTypeEntity typeEntity, Date date);

    List<EventEntity> findAllByTypeAndCity(EventTypeEntity typeEntity, CityEntity cityEntity);

    List<EventEntity> findAllByCityAndTimeStart(CityEntity cityEntity, Date date);

    List<EventEntity> findAllByTypeAndCityAndTimeStart(EventTypeEntity eventTypeEntity,
                                                       CityEntity cityEntity, Date date);

    EventEntity findById(int id);

    EventEntity findByName(String name);
}
