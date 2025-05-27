package com.pragma.home360;

import com.pragma.home360.infrastructure.security.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.pragma")
@EnableConfigurationProperties(JwtProperties.class)
public class ServicesHomeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicesHomeApplication.class, args);
    }
}