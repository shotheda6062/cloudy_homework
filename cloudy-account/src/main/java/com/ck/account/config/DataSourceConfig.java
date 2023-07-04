package com.ck.account.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${cloudSQL.socketFactory}")
    private String socketFactory;

    @Value("${cloudSQL.cloudSqlInstance}")
    private String cloudSqlInstance;

    @Value("${cloudSQL.unixSocketPath}")
    private String unixSocketPath;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dataSourceUrl);
        config.setUsername(user);
        config.setPassword(password);

        config.addDataSourceProperty("socketFactory", socketFactory);
        config.addDataSourceProperty("cloudSqlInstance", cloudSqlInstance);
        config.addDataSourceProperty("unixSocketPath", unixSocketPath);

        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }
}
