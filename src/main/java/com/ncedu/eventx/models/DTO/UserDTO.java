package com.ncedu.eventx.models.DTO;

import com.ncedu.eventx.models.entities.UserRoleEntity;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private int id;
    private UserRoleEntity roleId;
    private String login;
    private String email;
    private String password;
    private String name;
    private String organizationName;
    private String positionName;
    private String avatarImg;

}
