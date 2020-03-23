package com.ncedu.eventx.models.entities;

import lombok.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

import java.util.*;


@Data
@Getter
@Setter
@Entity
@ToString

@Table(name="t_user", schema = "eventx")
public class UserEntity implements Serializable, UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private List<RoleEntity> roles;

    @OneToMany(mappedBy = "user")
    List<UserEventEntity> userEvents = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<UserEventItemEntity> userEventItems = new ArrayList<>();


    @OneToMany(mappedBy = "user")
    List<UserEventItemEntity> userEventItems = new ArrayList<>();

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String organizationName;

    @Column(nullable = false)
    private String positionName;

    @Column(nullable = false)
    private String avatarImg;

    @Email
    @Column(nullable = false)
    private String email;

    // Удалить

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public List<UserEventEntity> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(List<UserEventEntity> userEvents) {
        this.userEvents = userEvents;
    }

    public List<UserEventItemEntity> getUserEventItems() {
        return userEventItems;
    }

    public void setUserEventItems(List<UserEventItemEntity> userEventItems) {
        this.userEventItems = userEventItems;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
