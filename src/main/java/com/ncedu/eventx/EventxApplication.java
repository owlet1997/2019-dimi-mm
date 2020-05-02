package com.ncedu.eventx;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EventxApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventxApplication.class, args);
    }

//    @Bean // Need to expose SessionFactory to be able to work with BLOBs
//    public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
//        return hemf.getSessionFactory();
//    }
}