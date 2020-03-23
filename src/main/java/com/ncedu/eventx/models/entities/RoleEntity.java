package com.ncedu.eventx.models.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@ToString
@Table(name = "t_roles", schema = "eventx")
public class RoleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @OneToMany(mappedBy = "role",
            cascade = {CascadeType.REFRESH, CascadeType.MERGE},
            orphanRemoval = true)
    private List<UserEntity> users = new ArrayList<>();

    @OneToMany(mappedBy = "role")
    private List<UserEventEntity> userEventList = new ArrayList<>();



}
