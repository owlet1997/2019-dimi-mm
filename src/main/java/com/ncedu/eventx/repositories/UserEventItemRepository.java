package com.ncedu.eventx.repositories;

import com.ncedu.eventx.models.entities.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserEventItemRepository extends CrudRepository<UserEventItemEntity, UserEventItemKey> {

    List<UserEventItemEntity> getAllByItem(EventItemEntity eventItemEntity);

    List<UserEventItemEntity> getAllByUser(UserEntity userEntity);

    List<UserEventItemEntity> getAllByUserAndRole(UserEntity userEntity, RoleEntity roleEntity);

    boolean deleteAllByItemAndRole(EventItemEntity eventItemEntity, RoleEntity roleEntity);

}
