package com.ncedu.eventx.models.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_event_item", schema = "eventx")
public class UserEventItemEntity {

    @EmbeddedId
    UserEventItemKey id;

    @ManyToOne
    @MapsId("user")
    @JoinColumn(nullable = false)
    private UserEntity user;

    @ManyToOne
    @MapsId("item")
    @JoinColumn(nullable = false)
    private EventItemEntity item;

    @ManyToOne
    @MapsId("role")
    @JoinColumn(nullable = false)

    private RoleEntity role;


    @Column(nullable = false)
    private int showOrder;

}
