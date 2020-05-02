package com.ncedu.eventx.models.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserEventKey implements Serializable {

    @Column(nullable = false)
    int event;

    @Column(nullable = false)
    int user;

    @Column(nullable = false)
    int role;

}
