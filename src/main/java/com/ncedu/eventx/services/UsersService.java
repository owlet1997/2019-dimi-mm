package com.ncedu.eventx.services;

import com.ncedu.eventx.models.DTO.PasswordChangeDTO;
import com.ncedu.eventx.models.DTO.UserDTO;

import com.ncedu.eventx.models.DTO.UserForUpdateDTO;
import com.ncedu.eventx.models.entities.UserEntity;

import org.springframework.web.multipart.MultipartFile;



import java.io.IOException;
import java.util.List;

public interface UsersService {

    List<UserDTO> getAllUsers();

    UserForUpdateDTO updateUser(UserForUpdateDTO userDTO);

    UserForUpdateDTO updatePassword(PasswordChangeDTO data);

    boolean createRegisteredUser(UserDTO userDTO);

    UserEntity getUserById(int id);

    boolean deleteUser(int id);

    UserDTO getUserByUsername(String username);

    UserForUpdateDTO savePicture(MultipartFile file, String username) throws IOException;
}
