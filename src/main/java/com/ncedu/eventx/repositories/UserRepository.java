package com.ncedu.eventx.repositories;

import com.ncedu.eventx.models.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    List<UserEntity> findAll();

    //List<UserEntity> findByRoleId(UserRoleEntity roles);

    UserEntity findById(int id);


    UserEntity findByUsername(String username);

}
