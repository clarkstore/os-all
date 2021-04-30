package com.onestop.starter.wx.mp.config;

import com.onestop.wx.mp.model.dto.MenuConfigs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * os-wx-mp配置
 * @author Clark
 * @version 2021-04-29
 */
@Configuration
@EnableConfigurationProperties(OsWxmpMenuProperties.class)
@ConditionalOnProperty(prefix = "os.wxmp.menu", name = "enabled", havingValue = "true")
public class OsWxmpMenuConfiguration {
    @Autowired
    private OsWxmpMenuProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public MenuConfigs menuConfigs() {
        MenuConfigs config = new MenuConfigs();
        config.setConfigs(this.properties.getConfigs());
        return config;
    }
}
