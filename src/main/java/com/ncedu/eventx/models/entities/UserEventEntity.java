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
@Table(name="user_event", schema = "eventx")
public class UserEventEntity implements Serializable {

    @EmbeddedId
    UserEventKeyEntity id;

    @ManyToOne
    @MapsId("userid")
    @JoinColumn(nullable = false)
    private UserEntity userId;

    @ManyToOne
    @MapsId("eventid")
    @JoinColumn(nullable = false)
    private EventEntity eventId;

    @Column(name = "SHOW_ORDER", nullable = false)
    private int showOrder;

}
