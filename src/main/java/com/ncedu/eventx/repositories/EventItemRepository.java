package com.ncedu.eventx.repositories;

import com.ncedu.eventx.model.domain.EventItemDAO;
import org.springframework.data.repository.CrudRepository;

public interface EventItemRepository extends CrudRepository<EventItemDAO, Integer> {
}
