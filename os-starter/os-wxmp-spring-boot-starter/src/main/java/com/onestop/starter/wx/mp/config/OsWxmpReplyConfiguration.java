package com.onestop.starter.wx.mp.config;

import com.onestop.wx.mp.model.dto.ReplyConfigs;
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
@EnableConfigurationProperties(WxmpReplyProperties.class)
@ConditionalOnProperty(prefix = "os.wxmp.reply", name = "enabled", havingValue = "true")
public class OsWxmpReplyConfiguration {
    @Autowired
    private WxmpReplyProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public ReplyConfigs replyConfigs() {
        ReplyConfigs config = new ReplyConfigs();
        config.setConfigs(this.properties.getConfigs());
        return config;
    }
}
