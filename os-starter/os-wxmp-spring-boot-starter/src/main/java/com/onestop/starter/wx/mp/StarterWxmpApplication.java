package com.onestop.starter.wx.mp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.onestop"})
public class StarterWxmpApplication {
    public static void main(String[] args) {
        SpringApplication.run(StarterWxmpApplication.class, args);
    }
}
