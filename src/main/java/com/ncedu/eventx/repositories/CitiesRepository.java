package com.ncedu.eventx.repositories;

import com.ncedu.eventx.model.domain.CityDAO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CitiesRepository extends CrudRepository<CityDAO, Integer> {
    List<CityDAO> findAll();

    CityDAO findByCityId(String cityid);
}
