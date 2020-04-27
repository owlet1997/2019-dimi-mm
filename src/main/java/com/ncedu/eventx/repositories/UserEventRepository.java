package com.ncedu.eventx.repositories;

import com.ncedu.eventx.models.entities.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEventRepository extends CrudRepository<UserEventEntity, UserEventKey> {

    List<UserEventEntity> findAll();

    List<UserEventEntity> findAllByEvent(EventEntity eventEntity);

    List<UserEventEntity> findAllByRole(RoleEntity roleEntity);


    List<UserEventEntity> findAllByUser(UserEntity userEntity);

    boolean deleteAllByEventAndRole(EventEntity eventEntity, RoleEntity roleEntity);

    UserEventEntity findByEventAndUser(EventEntity eventEntity, UserEntity userEntity);


    UserEventEntity findByEventAndRole(EventEntity eventEntity, RoleEntity roleEntity);

    boolean deleteByEventAndUserAndRole(EventEntity entity, UserEntity userEntity, RoleEntity roleEntity);


}
