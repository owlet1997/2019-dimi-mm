package com.ncedu.eventx.models.DTO;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private int id;
    private UserRoleDTO roleId;
    private String login;
    private String email;
    private String password;
    private String name;
    private String organizationName;
    private String positionName;
    private String avatarImg;

}
