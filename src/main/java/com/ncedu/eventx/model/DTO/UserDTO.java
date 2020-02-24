package com.ncedu.eventx.model.DTO;

import com.ncedu.eventx.model.domain.UserRoleDAO;

import javax.persistence.*;

public class UserDTO {

    private int id;

    private UserRoleDAO roleId;

    private String login;

    private String email;

    private String password;

    private String name;

    private String organizationName;

    private String positionName;

    private String avatarImg;

    public UserDTO() {
    }

    public UserDTO(int id, UserRoleDAO roleId, String login,
                   String email, String password, String name,
                   String organizationName, String positionName,
                   String avatarImg) {
        this.id = id;
        this.roleId = roleId;
        this.login = login;
        this.email = email;
        this.password = password;
        this.name = name;
        this.organizationName = organizationName;
        this.positionName = positionName;
        this.avatarImg = avatarImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserRoleDAO getRoleId() {
        return roleId;
    }

    public void setRoleId(UserRoleDAO roleId) {
        this.roleId = roleId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getAvatarImg() {
        return avatarImg;
    }

    public void setAvatarImg(String avatarImg) {
        this.avatarImg = avatarImg;
    }
}
