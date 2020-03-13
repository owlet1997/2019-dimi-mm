package com.ncedu.eventx.repositories;

import com.ncedu.eventx.models.entities.UserEventItemEntity;
import com.ncedu.eventx.models.entities.UserEventItemKey;
import org.springframework.data.repository.CrudRepository;

public interface UserEventItemRepository extends CrudRepository<UserEventItemEntity, UserEventItemKey> {

}
