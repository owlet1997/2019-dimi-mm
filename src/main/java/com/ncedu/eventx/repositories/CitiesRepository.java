package com.ncedu.eventx.repositories;

import com.ncedu.eventx.model.domain.CityDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesRepository extends CrudRepository<CityDAO, Integer> {

}
