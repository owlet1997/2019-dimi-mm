package com.ncedu.eventx.repositories;

import com.ncedu.eventx.models.entities.CoordinatesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoordinatesRepository extends CrudRepository<CoordinatesEntity, Integer> {
    CoordinatesEntity findById(int id);

    CoordinatesEntity findByName(String name);

    List<CoordinatesEntity> findAll();
}
