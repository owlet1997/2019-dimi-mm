package com.ncedu.eventx.repositories;

import com.ncedu.eventx.models.entities.EventItemEntity;
import com.ncedu.eventx.models.entities.UserEntity;
import com.ncedu.eventx.models.entities.UserEventItemEntity;
import com.ncedu.eventx.models.entities.UserEventItemKey;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserEventItemRepository extends CrudRepository<UserEventItemEntity, UserEventItemKey> {

    List<UserEventItemEntity> getAllByItem(EventItemEntity eventItemEntity);

    List<UserEventItemEntity> getAllByUser(UserEntity userEntity);

}
