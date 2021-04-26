package com.onestop.wx.mp.config;

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
 * @version 2021-04-23
 */
@Configuration
@EnableConfigurationProperties(WxMpMenuProperties.class)
@ConditionalOnProperty(prefix = "os.wxmp.menu", name = "isopen", havingValue = "true")
public class WxmpMenuConfiguration {
    @Autowired
    private WxMpMenuProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public MenuConfigs menuConfigs() {
        MenuConfigs config = new MenuConfigs();
        config.setConfigs(this.properties.getConfigs());
        return config;
    }
}
