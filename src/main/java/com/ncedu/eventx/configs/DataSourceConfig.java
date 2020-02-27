package com.ncedu.eventx.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment env;

    Logger log = Logger.getLogger("log");

    @Bean(name = "dataSource")
    public DataSource getDataSource(){
        String databaseUrl = System.getenv("URI");

        log.info("Initializing PostgreSQL database:" + databaseUrl);

        URI dbUri;
        try{
            dbUri = new URI(databaseUrl);
        } catch (URISyntaxException e) {
            log.info(String.format("Invalid DATABASE_URL: %s", databaseUrl));
            return null;
        }

        String userName = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':'
                + dbUri.getPort() + dbUri.getPath();

        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnReturn(true);
        dataSource.setValidationQuery("SELECT 1");

        return dataSource;
    }
}
