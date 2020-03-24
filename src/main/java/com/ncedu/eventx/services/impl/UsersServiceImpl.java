package com.ncedu.eventx.services.impl;

import com.ncedu.eventx.converters.UsersMapper;
import com.ncedu.eventx.models.DTO.UserDTO;
import com.ncedu.eventx.models.DTO.UserForUpdateDTO;
import com.ncedu.eventx.models.entities.UserEntity;
import com.ncedu.eventx.models.entities.UserRoleEntity;
import com.ncedu.eventx.repositories.RolesRepository;
import com.ncedu.eventx.repositories.UserRepository;
import com.ncedu.eventx.services.UsersService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    final UserRepository userRepository;
    final RolesRepository rolesRepository;

    UsersMapper usersMapper = Mappers.getMapper(UsersMapper.class);

    public UsersServiceImpl(UserRepository userRepository, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return usersMapper.toUserDTOList(userEntityList);
    }

    @Override
    public boolean createRegisteredUser(UserForUpdateDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        UserRoleEntity role = rolesRepository.findByName("user");

        userEntity.setId(userDTO.getId());
        userEntity.setRole(role);
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setName(userDTO.getName());
//        userEntity.setPassword(userDTO.getPassword());
        userEntity.setOrganizationName(userDTO.getOrganizationName());
        userEntity.setPositionName(userDTO.getPositionName());
        userEntity.setAvatarImg(null);

        userRepository.save(userEntity);
        return true;
    }

    @Override
    public UserForUpdateDTO updateUser(UserForUpdateDTO userDTO) {
        UserEntity userEntity = userRepository.findById(userDTO.getId());

        userEntity.setEmail(userDTO.getEmail());
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setName(userDTO.getName());
        userEntity.setOrganizationName(userDTO.getOrganizationName());
        userEntity.setPositionName(userDTO.getPositionName());
        userEntity.setAvatarImg(null);

        userRepository.save(userEntity);

        return null;
    }

    @Override
    public UserDTO getUserById(int id) {
        UserEntity userEntity = userRepository.findById(id);
        return usersMapper.toDTO(userEntity);
    }
}
