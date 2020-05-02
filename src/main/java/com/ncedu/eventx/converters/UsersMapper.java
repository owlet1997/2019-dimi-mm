package com.ncedu.eventx.converters;

import com.ncedu.eventx.models.DTO.UserDTO;
import com.ncedu.eventx.models.DTO.UserForUpdateDTO;
import com.ncedu.eventx.models.entities.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface  UsersMapper {
    UserDTO toDTO(UserEntity userEntity);

    UserEntity toUserEntity(UserDTO userDTO);

    List<UserDTO> toUserDTOList(List<UserEntity> userEntityList);

    UserForUpdateDTO toUserForUpdateDTO(UserEntity user);

    UserForUpdateDTO toUserForUpdateDTO(UserDTO user);

    UserDTO toUserDTO(UserDTO userById);


}
