package com.ncedu.eventx.services;

import com.ncedu.eventx.models.DTO.UserDTO;
import com.ncedu.eventx.models.DTO.UserForUpdateDTO;

import java.util.List;

public interface UsersService {

    List<UserDTO> getAllUsers();

    boolean createRegisteredUser(UserForUpdateDTO userDTO);

    UserDTO getUserById(int id);

    UserForUpdateDTO updateUser(UserForUpdateDTO userDTO);
}
