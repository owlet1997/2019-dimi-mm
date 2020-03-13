package com.ncedu.eventx.repositories;

import com.ncedu.eventx.models.entities.UserRoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends CrudRepository<UserRoleEntity, Integer> {
    List<UserRoleEntity> findAll();

    UserRoleEntity findByName(String name);
}
