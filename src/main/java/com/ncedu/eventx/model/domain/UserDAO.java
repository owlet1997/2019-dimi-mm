package com.ncedu.eventx.model.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="USER_")
public class UserDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id", nullable = false)
    private UserRoleDAO roleId;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(name="ORGANIZATION_NAME", nullable = false)
    private String organizationName;

    @Column(name="POSITION_NAME", nullable = false)
    private String positionName;

    @Column(name="AVATAR_IMG", nullable = false)
    private String avatarImg;

    public int getId() {
        return id;
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
