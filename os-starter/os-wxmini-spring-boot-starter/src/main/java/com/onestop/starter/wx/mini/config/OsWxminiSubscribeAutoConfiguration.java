package com.onestop.starter.wx.mini.config;

import cn.hutool.core.collection.CollUtil;
import com.onestop.wx.mini.util.dto.SubscribeConfigs;
import com.onestop.wx.mini.util.dto.SubscribeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 小程序订阅消息配置
 * @author Clark
 * @version 2021-03-18
 */
@Configuration
@EnableConfigurationProperties(OsWxminiSubscribeProperties.class)
@ConditionalOnProperty(prefix = "os.wxmini.subscribe", name = "enabled", havingValue = "true")
public class OsWxminiSubscribeAutoConfiguration {
    @Autowired
    private OsWxminiSubscribeProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public SubscribeConfigs subscribeConfigs() {
        Map<String, SubscribeDto> configMap = CollUtil.newHashMap();
        this.properties.getConfigs().forEach(item -> {
            configMap.put(item.getMsgId(), item);
        });

        return SubscribeConfigs.builder()
                .configMap(configMap)
                .miniprogramState(this.properties.getMiniprogramState())
                .build();
    }
}
