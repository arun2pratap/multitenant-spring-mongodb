package com.multitenant.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com")

public class MultiTenantApplication {
    public static void main(String[] args) {
        SpringApplication.run(MultiTenantApplication.class, args);
    }
}
