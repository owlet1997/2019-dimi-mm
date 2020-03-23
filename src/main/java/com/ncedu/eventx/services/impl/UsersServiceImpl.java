package com.ncedu.eventx.services.impl;

import com.ncedu.eventx.converters.UsersMapper;
import com.ncedu.eventx.models.DTO.UserDTO;

import com.ncedu.eventx.models.entities.RoleEntity;
import com.ncedu.eventx.models.entities.UserEntity;

import com.ncedu.eventx.repositories.RolesRepository;
import com.ncedu.eventx.repositories.UserRepository;
import com.ncedu.eventx.services.UsersService;
import org.mapstruct.factory.Mappers;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UsersServiceImpl implements UsersService, UserDetailsService {


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

    public boolean createRegisteredUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        List<RoleEntity> roles = null;
        //List<UserRoleEntity> roles = rolesRepository.findByName(userDTO.getUsername());

        userEntity.setId(userDTO.getId());
        userEntity.setRoles(roles);
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setUsername(userDTO.getUsername());

        userEntity.setPassword(userDTO.getPassword());
        userEntity.setOrganizationName(userDTO.getOrganizationName());
        userEntity.setPositionName(userDTO.getPositionName());
        userEntity.setAvatarImg(null);

        userRepository.save(userEntity);
        return true;
    }

    @Override

    public boolean updateUser(UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(userDTO.getId());

        userEntity.setEmail(userDTO.getEmail());
        userEntity.setUsername(userDTO.getUsername());

        userEntity.setPassword(userDTO.getPassword());
        userEntity.setOrganizationName(userDTO.getOrganizationName());
        userEntity.setPositionName(userDTO.getPositionName());
        userEntity.setAvatarImg(null);

        userRepository.save(userEntity);

        return true;
    }

    @Override
    public boolean deleteUser(int id) {
            userRepository.deleteById(id);
            return true;

    }

    @Override
    public UserDTO getUserById(int id) {
        UserEntity userEntity = userRepository.findById(id);
        return usersMapper.toDTO(userEntity);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return (UserDetails) user;
    }

}
