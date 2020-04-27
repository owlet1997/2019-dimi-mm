package com.ncedu.eventx.services;

import com.ncedu.eventx.models.DTO.EventItemDTO;
import com.ncedu.eventx.models.DTO.EventItemForCreateDTO;
import com.ncedu.eventx.models.DTO.EventItemWithUsersDTO;
import com.ncedu.eventx.models.entities.EventItemEntity;
import com.ncedu.eventx.models.entities.UserEntity;

import java.util.List;

public interface EventItemService {

   EventItemEntity createEventItem(EventItemForCreateDTO eventItemDTO);

   List<EventItemDTO> getItemsByUser(int userId);

   List<EventItemWithUsersDTO> getEventItemWithUsersListByParent(int id, UserEntity user);

   EventItemWithUsersDTO getEventItemWithUsers(EventItemEntity e, UserEntity userEntity);

   boolean deleteItemById(int id);
}
