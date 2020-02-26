package com.ncedu.eventx.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class UserEventKeyEntity implements Serializable {

    @Column(nullable = false)
    int eventId;

    @Column(nullable = false)
    int userId;

}
