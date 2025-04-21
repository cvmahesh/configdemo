package com.example.configdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConfigdemoApplication  {
//public class ConfigdemoApplication implements CommandLineRunner{
	@Autowired
	static MariaDbConfig mariaDbConfig;
//
//	@Autowired
//	static PostgresDbConfig postgresDbConfig;

	public static void main(String[] args) {
		SpringApplication.run(ConfigdemoApplication.class, args);
		 
		System.exit(0);
		
		//for prod pass as args: --spring.profiles.active=prod 
	}
	
//	 @Override
//	    public void run(String... args) throws Exception {
//	        if (mariaDbConfig.isDatabaseUp()) {
//	            System.out.println("✅ MariaDB is UP at startup!");
//	            mariaDbConfig.fetchUserNames().forEach(name -> System.out.println("👤 " + name));
//	        } else {
//	            System.out.println("❌ Could not connect to MariaDB at startup.");
//	        }
//	    }

}
