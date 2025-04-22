package com.example.configdemo.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceB {

    public String generateReport() {
        // Simulate business logic
    	System.out.println("SERVICE B");
        return "Report generated at service B: " + java.time.LocalDateTime.now();
    }
}
