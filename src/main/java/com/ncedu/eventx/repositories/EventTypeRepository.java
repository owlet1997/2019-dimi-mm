package com.ncedu.eventx.repositories;

import com.ncedu.eventx.models.entities.EventTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventTypeRepository extends CrudRepository<EventTypeEntity, Integer> {

    List<EventTypeEntity> findAll();

    List<EventTypeEntity> findById(int id);
}
