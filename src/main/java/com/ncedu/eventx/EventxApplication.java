package com.ncedu.eventx;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.sql.DataSource;
import java.io.IOException;

@SpringBootApplication
@EnableWebMvc
@Configuration
@EnableTransactionManagement
public class EventxApplication implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        SpringApplication.run(EventxApplication.class, args);
    }

    @Bean
    public DataSource getDataSource(){
        DataSourceBuilder builder = DataSourceBuilder.create();
        return builder.build();
    }

    @Bean
    public SessionFactory getSessionFactory() throws IOException {
        org.hibernate.cfg.Configuration config = new org.hibernate.cfg.Configuration();
        ClassPathResource res = new ClassPathResource("hibernate.cfg.xml");
        SessionFactory sessionFactory = config.configure(res.getURL()).buildSessionFactory();
        DataSourceBuilder builder = DataSourceBuilder.create();
        return sessionFactory;
    }

}
