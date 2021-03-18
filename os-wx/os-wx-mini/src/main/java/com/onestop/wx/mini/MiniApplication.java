package com.onestop.wx.mini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Clark
 * @version 2020-08-27
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.onestop.wx"})
public class MiniApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniApplication.class, args);
    }
}
