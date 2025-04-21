package com.example.configdemo;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MariaDbService {

    @Autowired
    private MariaDbConfig mariaDbConfig;

    public void checkDbAndFetchUsers() {
        if (mariaDbConfig.isDatabaseUp()) {
            System.out.println("✅ MariaDB is UP!");
            List<String> users = mariaDbConfig.fetchUserNames();
            users.forEach(user -> System.out.println("👤 User: " + user));
        } else {
            System.out.println("❌ MariaDB is DOWN!");
        }
    }
}
