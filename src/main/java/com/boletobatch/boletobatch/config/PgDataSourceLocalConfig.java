package com.boletobatch.boletobatch.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class PgDataSourceLocalConfig {
    @Bean(name = "pgDataSource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(mountDbUrl("localhost", "5432"))
                .username("postgres")
                .password("postgres")
                .driverClassName("org.postgresql.Driver")
                .build();
    }

    private String mountDbUrl (String host, String port) {
        return "jdbc:postgresql://" + host + ":" + port + "/postgres?currentSchema=cobranca";
    }
}
