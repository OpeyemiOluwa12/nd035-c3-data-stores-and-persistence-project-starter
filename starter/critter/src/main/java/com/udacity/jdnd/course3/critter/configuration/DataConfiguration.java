package com.udacity.jdnd.course3.critter.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "com.opeyemi.critter.datasource")
    public DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.url("jdbc:mysql://127.0.0.1:3306/critter?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
//        dataSourceBuilder.username("ope");
//        dataSourceBuilder.password("ope123");
        return dataSourceBuilder.build();
    }
}
