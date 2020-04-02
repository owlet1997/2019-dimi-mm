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
//@Table(name="user", schema = "eventx")
public class UserEntity implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(nullable = false)
    private RoleEntity role;

    @OneToMany(mappedBy = "user")
    List<UserEventEntity> userEvents = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<UserEventItemEntity> userEventItems = new ArrayList<>();

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
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

}
