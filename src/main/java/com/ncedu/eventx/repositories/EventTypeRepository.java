package com.ncedu.eventx.repositories;

import com.ncedu.eventx.model.domain.EventTypeDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EventTypeRepository extends CrudRepository<EventTypeDAO, Integer> {

}
