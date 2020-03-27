package com.ncedu.eventx.models.DTO;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserForUpdateDTO {

    private int id;
    private String name;
    private String login;
    private String email;
    private String organizationName;
    private String positionName;

}
