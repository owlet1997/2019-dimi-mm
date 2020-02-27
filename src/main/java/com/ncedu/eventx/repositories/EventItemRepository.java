package com.ncedu.eventx.repositories;

import com.ncedu.eventx.models.entities.EventItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventItemRepository extends CrudRepository<EventItemEntity, Integer> {
    List<EventItemEntity> findAllByParentId(int parentId);

    List<EventItemEntity> findById(int id);
}
