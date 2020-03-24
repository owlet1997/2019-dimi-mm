package com.ncedu.eventx.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.logging.Logger;

@Configuration
public class DataSourceConfig {

    private final Environment env;

    Logger log = Logger.getLogger("log");

    public DataSourceConfig(Environment env) {
        this.env = env;
    }


    //    бин для подключения к хероку
//    @Bean(name = "dataSource")
//    public DataSource getDataSource(){
//        String databaseUrl = System.getenv("URI");
//
//        log.info("Initializing PostgreSQL database:" + databaseUrl);
//
//        URI dbUri;
//        try{
//            dbUri = new URI(databaseUrl);
//        } catch (URISyntaxException e) {
//            log.info(String.format("Invalid DATABASE_URL: %s", databaseUrl));
//            return null;
//        }
//
//        String userName = dbUri.getUserInfo().split(":")[0];
//        String password = dbUri.getUserInfo().split(":")[1];
//        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':'
//                + dbUri.getPort() + dbUri.getPath();
//
//        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
//
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl(dbUrl);
//        dataSource.setUsername(userName);
//        dataSource.setPassword(password);
//        dataSource.setTestOnBorrow(true);
//        dataSource.setTestWhileIdle(true);
//        dataSource.setTestOnReturn(true);
//        dataSource.setValidationQuery("SELECT 1");
//
//        return dataSource;
//    }
    // локальная база постгреса
    @Bean(name = "dataSource")
    public DataSource getDataSource(){

        // подключение к локальной бд postgres
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

//        String userName = "postgres";
//        String password = "1";
//        String dbUrl = "jdbc:postgresql://localhost:5432/eventx";
//
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl(dbUrl);
//        dataSource.setUsername(userName);
//        dataSource.setPassword(password);

//        return dataSource;

        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.datasource.driver-class-name")));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

}
