package com.ncedu.eventx.models.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name="user_event", schema = "eventx")
public class UserEventEntity implements Serializable {

    @EmbeddedId
    UserEventKey id;

    @ManyToOne
    @MapsId("user")
    @JoinColumn(nullable = false)
    private UserEntity user;

    @ManyToOne
    @MapsId("event")
    @JoinColumn(nullable = false)
    private EventEntity event;

    @ManyToOne
    @MapsId("role")
    @JoinColumn(nullable = false)
    private RoleEntity role;

    @Column(nullable = false)
    private int showOrder;

}
