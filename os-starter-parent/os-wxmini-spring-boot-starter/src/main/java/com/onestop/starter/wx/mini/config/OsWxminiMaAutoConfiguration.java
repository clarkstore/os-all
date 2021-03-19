package com.onestop.starter.wx.mini.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 小程序配置
 * @author Clark
 * @version 2021-03-18
 */
@Configuration
@EnableConfigurationProperties(WxMaProperties.class)
@ConditionalOnProperty(prefix = "os.wxmini", name = "isopen", havingValue = "true")
public class OsWxminiMaAutoConfiguration {
    @Autowired
    private WxMaProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public WxMaService build() {
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid(this.properties.getAppid());
        config.setSecret(this.properties.getSecret());
        config.setToken(this.properties.getToken());
        config.setAesKey(this.properties.getAesKey());
        config.setMsgDataFormat(this.properties.getMsgDataFormat());

        WxMaService service = new WxMaServiceImpl();
        service.setWxMaConfig(config);
        return service;
    }
}
