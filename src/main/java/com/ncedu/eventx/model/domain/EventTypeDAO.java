package com.ncedu.eventx.model.domain;

import javax.persistence.*;

@Entity
@Table(name="EVENT_TYPE")
public class EventTypeDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "TYPE", nullable = false)
    private String type;

    public int getId() {
        return id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
