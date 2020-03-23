package com.ncedu.eventx.models.DTO;

import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleWithUsersDTO {

    private int id;
    private String name;
    Set<UserDTO> userDTOSet;

}