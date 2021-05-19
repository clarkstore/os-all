package com.onestop.starter.common.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.onestop"})
public class StarterRedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(StarterRedisApplication.class, args);
    }
}
