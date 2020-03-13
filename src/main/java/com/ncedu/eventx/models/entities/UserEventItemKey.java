package com.ncedu.eventx.models.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserEventItemKey implements Serializable {

    @Column(nullable = false)
    int item;

    @Column(nullable = false)
    int user;

    @Column(nullable = false)
    int role;

}

// enum с ролями