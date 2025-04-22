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
public class PostgresDbConfig {

	@Autowired
	private PostgresDbProperties properties;

	private JdbcTemplate jdbcTemplate;

	@Bean(name = "postgresDbDataSource")
	public DataSource mariaDbDataSource() {
		System.out.println("<<POSTGRES-DB>>>");
		System.out.println("URL: "+properties.getUrl());
		System.out.println("getUsername: "+properties.getUsername());
		System.out.println("getPassword: "+properties.getPassword());
		return DataSourceBuilder.create()
				.url(properties.getUrl())
				.username(properties.getUsername())
				.password(properties.getPassword())
				.build();
	}

	@Bean(name = "postgresDbJdbcTemplate")
	public JdbcTemplate mariaDbJdbcTemplate(@Qualifier("postgresDbDataSource") DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
		return this.jdbcTemplate;
	}

	//✅ Method 1: Check database availability
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


	// @Bean(name = "mariaDbJdbcTemplate")
	// public JdbcTemplate mariaDbJdbcTemplate(@Qualifier("mariaDbDataSource") DataSource ds) {
	//     return new JdbcTemplate(ds);
	// }
}
