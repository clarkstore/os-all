package com.onestop.starter.common.azure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.onestop"})
public class StarterAzureApplication {
    public static void main(String[] args) {
        SpringApplication.run(StarterAzureApplication.class, args);
    }
}
