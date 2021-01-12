package com.onestop.starter.demo.config;

import com.onestop.starter.demo.service.DemoStarterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 *
 * @author Clark
 * @version 2021-01-04
 */
@Configuration
@EnableConfigurationProperties(DemoProperties.class)
@ConditionalOnProperty(
        prefix = "demo",
        name = "isopen",
        havingValue = "true"
)
//在application.yml"demo.isopen"，对应的值为true
public class DemoConfiguration {
    @Autowired
    private DemoProperties demoProperties;

    @Bean
    public DemoStarterService demoService() {
        return new DemoStarterService(demoProperties.getSayWhat(), demoProperties.getToWho());
    }
}
