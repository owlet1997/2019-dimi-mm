package com.ncedu.eventx.services;

import com.ncedu.eventx.models.DTO.UserDTO;
import com.ncedu.eventx.models.DTO.UserForCreateDTO;

import java.util.List;

public interface UsersService {

    List<UserDTO> getAllUsers();

    boolean createRegisteredUser(UserForCreateDTO userDTO);

    UserDTO getUserById(int id);

    UserForCreateDTO updateUser(UserForCreateDTO userDTO);
}
