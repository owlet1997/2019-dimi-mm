package com.ncedu.eventx.enums;

public enum UserRoleItems {
    USER("ROLE_USER"),
    CREATOR("creator"),
    SPEAKER("speaker"),
    VISITOR("visitor");

    private String description;

    UserRoleItems(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
