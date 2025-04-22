package com.example.configdemo;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class MariaDbConfig {

    @Autowired
    private MariaDbProperties properties;

    private JdbcTemplate jdbcTemplate;

    @Bean(name = "mariaDbDataSource")
    public DataSource mariaDbDataSource() {
        System.out.println("<<MARIA-DB CONFIG>>");
        System.out.println("URL: " + properties.getUrl());
        System.out.println("Username: " + properties.getUsername());

        // Construct JDBC URL with SSL configuration
        String fullUrl = String.format(
            "%s?useSSL=true&requireSSL=true&verifyServerCertificate=true" +
            "&trustCertificateKeyStoreUrl=file:%s" +
            "&trustCertificateKeyStoreType=JKS" +
            "&clientCertificateKeyStoreUrl=file:%s" +
            "&clientCertificateKeyStoreType=PKCS12" +
            "&clientCertificateKeyStorePassword=%s",
            properties.getUrl(),
            properties.getSslCa(),
            properties.getClientCert(),
            properties.getClientCertPassword()
        );

        return DataSourceBuilder.create()
                .url(fullUrl)
                .username(properties.getUsername())
                .password(properties.getPassword())
                .build();
    }

    @Bean(name = "mariaDbJdbcTemplate")
    public JdbcTemplate mariaDbJdbcTemplate(@Qualifier("mariaDbDataSource") DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
        return this.jdbcTemplate;
    }

    // ✅ Method 1: Check database availability
    public boolean isDatabaseUp() {
        try {
            Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            return result != null && result == 1;
        } catch (Exception e) {
            System.err.println("❌ Could not connect to MariaDB: " + e.getMessage());
            return false;
        }
    }

    // ✅ Method 2: Fetch user names from 'users' table
    public List<String> fetchUserNames() {
        String sql = "SELECT name FROM users";
        try {
            return jdbcTemplate.queryForList(sql, String.class);
        } catch (Exception e) {
            System.err.println("❌ Failed to fetch users: " + e.getMessage());
            return List.of();
        }
    }
}
