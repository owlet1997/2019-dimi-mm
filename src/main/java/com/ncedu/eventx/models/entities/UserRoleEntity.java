package com.ncedu.eventx.models.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@Table(name = "user_role", schema = "eventx")
public class UserRoleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @OneToMany(mappedBy = "roleId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEntity> users;

}
