package com.eseict.gondo;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ConnectionPoolConfig{

    @Value("${spring.datasource.username}")  // 1
    private String username;

    @Value("${spring.datasource.password}")  // 1
    private String password;

    @Value("${spring.datasource.url}")  // 1
    private String url;

    // 2
    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();  // 3

        hikariConfig.setUsername("firstDo");
        hikariConfig.setPassword("123456");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/firstdb?autoReconnect=true&serverTimezone=UTC&characterEncoding=UTF-8");
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setMaximumPoolSize(100);  // Connection Pool에서 갖고있을 Connection의 갯수
        hikariConfig.setLeakDetectionThreshold(30000);
       // hikariConfig.setPoolName("Mariadb-HikariCP");

        return new HikariDataSource(hikariConfig); // 4
    }
}