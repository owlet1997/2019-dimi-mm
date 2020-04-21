package com.ncedu.eventx.models.DTO;

import com.ncedu.eventx.models.entities.RoleEntity;
import lombok.*;

import javax.persistence.Transient;
import java.sql.Blob;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private int id;

    private String email;

    private String password;

    @Transient
    private String passwordConfirm;

    private String name;

    private String username;

    private String organizationName;

    private String positionName;

    private String avatarImg;

}
