package com.ncedu.eventx.models.DTO;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserForCreateDTO {

    private int id;
    private String login;
    private String email;
    private String password;
    private String name;
    private String organizationName;
    private String positionName;

}
