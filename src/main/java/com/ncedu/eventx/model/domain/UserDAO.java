package com.ncedu.eventx.model.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="USER_")
public class UserDAO implements Serializable {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private int id;

}
