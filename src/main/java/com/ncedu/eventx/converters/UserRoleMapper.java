package com.ncedu.eventx.converters;

import com.ncedu.eventx.models.DTO.UserRoleDTO;
import com.ncedu.eventx.models.entities.UserRoleEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserRoleMapper {

    UserRoleEntity toUserRoleEntity(UserRoleDTO userRoleDTO);

    UserRoleDTO toUserRoleDTO(UserRoleEntity userRoleEntity);

}
