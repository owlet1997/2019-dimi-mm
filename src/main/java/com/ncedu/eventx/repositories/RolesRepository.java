package com.ncedu.eventx.repositories;

import com.ncedu.eventx.models.entities.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends CrudRepository<RoleEntity, Integer> {
    List<RoleEntity> findAll();

    RoleEntity findByName(String name);
}
