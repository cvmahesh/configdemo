package com.example.configdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.configdemo.service.ServiceA;
import com.example.configdemo.service.ServiceB;

@Component
public class TaskDispatcher implements CommandLineRunner {
    @Autowired 
    ServiceA serviceA;
    
    @Autowired 
    ServiceB serviceB;
    
    

    @Override
    public void run(String... args) {
        if (args.length == 0) {
            System.out.println("Please provide a command");
            return;
        }

        switch (args[0]) {
            case "service-a": serviceA.generateReport(); break;
            case "service-b": serviceB.generateReport(); break;
            default: System.out.println("Unknown command: " + args[0]);
        }
    }
}
