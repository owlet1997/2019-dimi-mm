package com.ncedu.eventx.services;

import com.ncedu.eventx.models.DTO.EventItemDTO;
import com.ncedu.eventx.models.DTO.UserDTO;
import com.ncedu.eventx.models.entities.UserEventItemEntity;

import java.util.List;

public interface UserEventItemService {

    List<UserEventItemEntity> getAll();

    boolean createEventItem(EventItemDTO itemDTO, UserDTO userDTO);

    boolean addToFeatured(int itemId, int userId);

    boolean isFeatured(int itemId, int userId);

    boolean removeFromFeatured(int itemId, int userId);
}
