package com.example.configdemo.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceA {

    public String generateReport() {
        // Simulate business logic
    	System.out.println("SERVICE A");
        return "Report generated at " + java.time.LocalDateTime.now();
    }
}
