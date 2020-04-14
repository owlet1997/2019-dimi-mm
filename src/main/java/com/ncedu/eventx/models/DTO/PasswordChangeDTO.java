package com.ncedu.eventx.models.DTO;

import lombok.*;

import java.sql.Blob;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeDTO {

    private int id;

    private String password;

    private String newPassword;

    private Blob avatarImg;

}
