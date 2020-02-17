package com.ncedu.eventx.repositories;

import com.ncedu.eventx.model.domain.EventTypeDAO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventTypeRepository extends CrudRepository<EventTypeDAO, Integer> {
    List<EventTypeDAO> findAll();

    EventTypeDAO findById(int id);
}
