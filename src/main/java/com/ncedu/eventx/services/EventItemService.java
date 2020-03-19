package com.ncedu.eventx.services;

import com.ncedu.eventx.models.DTO.EventItemDTO;
import com.ncedu.eventx.models.DTO.EventItemWithUsersDTO;
import com.ncedu.eventx.models.entities.EventItemEntity;

import java.util.List;

public interface EventItemService {

   EventItemEntity createEventItem(EventItemDTO eventItemDTO);

   List<EventItemWithUsersDTO> getEventItemsListByParent(int id);

}
