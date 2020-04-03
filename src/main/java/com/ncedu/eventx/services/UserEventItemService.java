package com.ncedu.eventx.services;

import com.ncedu.eventx.models.DTO.EventItemDTO;
import com.ncedu.eventx.models.DTO.UserDTO;
import com.ncedu.eventx.models.entities.UserEventItemEntity;

import java.util.List;

public interface UserEventItemService {

    List<UserEventItemEntity> getAll();

    boolean createEventItem(EventItemDTO itemDTO, UserDTO userDTO);

    boolean addToFeatured(int itemId, String username);

    boolean isFeatured(int itemId, String username);

    boolean removeFromFeatured(int itemId, String username);
}
