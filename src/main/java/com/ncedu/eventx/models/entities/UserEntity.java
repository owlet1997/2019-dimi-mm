package com.ncedu.eventx.models.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Set;

@Data
@Getter
@Setter
@Entity
@Table(name="user", schema = "eventx")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private UserRoleEntity roleId;

    @OneToMany(mappedBy = "userId")
    Set<UserEventEntity> userEvents;

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
