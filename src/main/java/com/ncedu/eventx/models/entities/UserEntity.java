package com.ncedu.eventx.models.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@Entity
@ToString
@Table(name="user", schema = "eventx")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(nullable = false)
    private UserRoleEntity role;

    @OneToMany(mappedBy = "user")
    List<UserEventEntity> userEvents = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<UserEventItemEntity> userEventItems = new ArrayList<>();

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(name="organization_name", nullable = false)
    private String organizationName;

    @Column(name="position_name", nullable = false)
    private String positionName;

    @Column(name="avatar_img", nullable = false)
    private String avatarImg;

    @Email(message = "Email address has invalid format: ${validatedValue}",
            regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    @Column(nullable = false)
    private String email;

}
