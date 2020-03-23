package com.ncedu.eventx.converters;

import com.ncedu.eventx.models.DTO.RoleDTO;
import com.ncedu.eventx.models.entities.RoleEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserRoleMapper {

    RoleEntity toUserRoleEntity(RoleDTO roleDTO);

    RoleDTO toUserRoleDTO(RoleEntity roleEntity);

}
