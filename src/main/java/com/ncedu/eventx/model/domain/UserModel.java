package com.ncedu.eventx.model.domain;

import javax.persistence.*;

@Entity
@Table(name="USER_")
public class UserModel {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private int id;

}
