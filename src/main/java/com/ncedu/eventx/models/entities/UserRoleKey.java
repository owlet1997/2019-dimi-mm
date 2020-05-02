package com.ncedu.eventx.models.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserRoleKey {

    @Column(nullable = false)
    int user_id;

    @Column(nullable = false)
    int role_id;
}
