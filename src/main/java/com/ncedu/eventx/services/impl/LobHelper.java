package com.ncedu.eventx.services.impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.io.InputStream;
import java.sql.Blob;

@Service
public class LobHelper {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    public Blob createBlob(InputStream content, long size) {
        return entityManagerFactory.createEntityManager().unwrap(Session.class).getSession().getLobHelper().createBlob(content, size);
    }
}
