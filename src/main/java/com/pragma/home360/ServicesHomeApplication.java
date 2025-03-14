package com.pragma.home360;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.pragma")
public class ServicesHomeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicesHomeApplication.class, args);
    }
}