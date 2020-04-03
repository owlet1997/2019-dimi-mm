package com.ncedu.eventx.services;

import com.ncedu.eventx.models.DTO.UserDTO;

import com.ncedu.eventx.models.DTO.UserForUpdateDTO;
import com.ncedu.eventx.models.entities.UserEntity;


import java.util.List;

public interface UsersService {

    List<UserDTO> getAllUsers();

    UserForUpdateDTO updateUser(UserForUpdateDTO userDTO);

    boolean createRegisteredUser(UserDTO userDTO);

    UserEntity getUserById(int id);

    boolean deleteUser(int id);

    UserDTO getUserByUsername(String username);
}
