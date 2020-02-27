package com.ncedu.eventx.models.DTO;

import java.util.Set;

public class UserRoleWithUsersDTO {

    private int id;
    private String name;
    Set<UserDTO> userDTOSet;

    public UserRoleWithUsersDTO() {
    }

    public UserRoleWithUsersDTO(int id, String name, Set<UserDTO> userDTOSet) {
        this.id = id;
        this.name = name;
        this.userDTOSet = userDTOSet;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserDTO> getUserDTOSet() {
        return userDTOSet;
    }

    public void setUserDTOSet(Set<UserDTO> userDTOSet) {
        this.userDTOSet = userDTOSet;
    }
}
