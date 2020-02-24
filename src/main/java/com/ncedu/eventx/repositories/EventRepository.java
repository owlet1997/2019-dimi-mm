package com.ncedu.eventx.repositories;

import com.ncedu.eventx.model.domain.CityDAO;
import com.ncedu.eventx.model.domain.EventDAO;
import com.ncedu.eventx.model.domain.EventTypeDAO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<EventDAO, Integer> {
    List<EventDAO> findAll();

    EventDAO findById(int id);

    List<EventDAO> findAllByCity(CityDAO city);

    List<EventDAO> findAllByType(EventTypeDAO type);

}
