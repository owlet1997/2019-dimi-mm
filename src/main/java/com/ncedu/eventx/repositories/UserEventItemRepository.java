package com.ncedu.eventx.repositories;

import com.ncedu.eventx.models.entities.*;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface UserEventItemRepository extends CrudRepository<UserEventItemEntity, UserEventItemKey> {

    List<UserEventItemEntity> getAllByItem(EventItemEntity eventItemEntity);

    List<UserEventItemEntity> getAllByUser(UserEntity userEntity);

    List<UserEventItemEntity> getAllByUserAndRoleAndItemTimeStartAfter(UserEntity userEntity, RoleEntity roleEntity, Date date);

    List<UserEventItemEntity> getAllByUserAndRole(UserEntity userEntity, RoleEntity roleEntity);

    List<UserEventItemEntity> getAllByItemAndRole(EventItemEntity eventItemEntity, RoleEntity roleEntity);

    boolean deleteAllByItemAndRole(EventItemEntity eventItemEntity, RoleEntity roleEntity);

}
