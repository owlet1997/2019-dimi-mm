package com.ncedu.eventx.repositories;

import com.ncedu.eventx.model.domain.CoordinatesDAO;
import org.springframework.data.repository.CrudRepository;

public interface CoordinatesRepository extends CrudRepository<CoordinatesDAO, Integer> {
}
