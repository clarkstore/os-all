package com.onestop.wx.mp.config;

import cn.hutool.core.collection.CollUtil;
import com.onestop.wx.mp.extra.entity.MenuConfigs;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

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
