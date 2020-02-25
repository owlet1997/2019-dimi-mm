package com.ncedu.eventx.model.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="EVENT_ITEM")
public class EventItemDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id", nullable = false)
    private EventDAO parentId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "TIME", nullable = false)
    private Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventDAO getParentId() {
        return parentId;
    }

    public void setParentId(EventDAO parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
