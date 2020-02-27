package com.ncedu.eventx.repositories;

import com.ncedu.eventx.models.entities.CityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitiesRepository extends CrudRepository<CityEntity, Integer> {
    List<CityEntity> findAll();

    List<CityEntity> findByCityId(String cityId);
}
