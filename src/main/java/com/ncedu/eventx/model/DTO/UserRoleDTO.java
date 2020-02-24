package com.ncedu.eventx.model.DTO;

public class UserRoleDTO {

    private int id;

    private String name;

    public UserRoleDTO() {
    }

    public UserRoleDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
