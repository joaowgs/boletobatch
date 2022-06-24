package com.boletobatch.boletobatch.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class SpringBatchDataSourceLocalConfig {

    @Bean(name = "boletoDbBatch")
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(mountDbUrl("localhost", "5432"))
                .username("postgres")
                .password("postgres")
                .driverClassName("org.postgresql.Driver")
                .build();
    }

    private String mountDbUrl (String host, String port) {
        return "jdbc:postgresql://" + host + ":" + port + "/postgres?currentSchema=spring-batch";
    }

}
