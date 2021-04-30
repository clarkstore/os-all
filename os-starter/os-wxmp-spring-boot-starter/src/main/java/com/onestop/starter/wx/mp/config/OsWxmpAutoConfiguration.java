package com.onestop.starter.wx.mp.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
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
@ConditionalOnClass(WxMpService.class)
@EnableConfigurationProperties(WxmpProperties.class)
@ConditionalOnProperty(prefix = "os.wxmp", name = "enabled", havingValue = "true")
public class OsWxmpAutoConfiguration {
    @Autowired
    private WxmpProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public WxMpService wxMpService() {
        WxMpDefaultConfigImpl configStorage = new WxMpDefaultConfigImpl();
        configStorage.setAppId(this.properties.getAppid());
        configStorage.setSecret(this.properties.getSecret());
        configStorage.setToken(this.properties.getToken());
        configStorage.setAesKey(this.properties.getAesKey());

        WxMpService service = new WxMpServiceImpl();
        service.setWxMpConfigStorage(configStorage);
        return service;
    }
}
