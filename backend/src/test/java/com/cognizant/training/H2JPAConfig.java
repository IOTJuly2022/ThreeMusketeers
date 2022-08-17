package com.cognizant.training;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.cognizant.training")
@EnableTransactionManagement
public class H2JPAConfig {

    @Bean
    @Profile("mem-test")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("app");
        dataSource.setPassword("app");
        dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");

        return dataSource;
    }
}