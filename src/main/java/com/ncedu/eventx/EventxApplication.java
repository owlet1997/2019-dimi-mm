package com.ncedu.eventx;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.sql.DataSource;
import java.io.IOException;

@SpringBootApplication
@Configuration
@EnableTransactionManagement
public class EventxApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventxApplication.class, args);
    }

    @Autowired
    ApplicationContext context;

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

    @Bean
    public SpringResourceTemplateResolver templateResolver(){

        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(context);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");

        templateResolver.setTemplateMode(TemplateMode.HTML);

        templateResolver.setCacheable(true);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(){

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());

        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }


}
