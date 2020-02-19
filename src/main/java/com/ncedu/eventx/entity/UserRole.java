package com.ncedu.eventx.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "userRole")
public class UserRole {

    @Id
    private int id;

    @Column(name = "NAME")
    @Size(min=2, max=30)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userRole")
    private Set<User> users;

    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> setUsers) {
        this.users = setUsers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}