package com.ncedu.eventx.repositories;

import com.ncedu.eventx.model.domain.EventDAO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<EventDAO, Integer> {
    List<EventDAO> findAll();

    EventDAO findById(int id);

    List<EventDAO> findAllByCity(String city);

    List<EventDAO> findAllByType(String type);

}
