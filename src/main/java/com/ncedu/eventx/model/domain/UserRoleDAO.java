package com.ncedu.eventx.model.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USER_ROLE")
public class UserRoleDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column
    private String name;

    @OneToMany(mappedBy = "roleId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserDAO> users;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserDAO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDAO> users) {
        this.users = users;
    }
}
