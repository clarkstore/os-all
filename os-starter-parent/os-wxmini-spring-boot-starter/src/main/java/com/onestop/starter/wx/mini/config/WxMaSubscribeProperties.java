package com.onestop.starter.wx.mini.config;

import cn.hutool.core.collection.CollUtil;
import com.onestop.wx.mini.util.dto.SubscribeDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 订阅消息模板配置
 *
 * @author Clark
 * @version 2020-08-25
 */
@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "os.wxmini.subscribe")
public class WxMaSubscribeProperties {
    private List<SubscribeDto> configs;

    private Map<String, SubscribeDto> configMap;
    /**
     * 获取配置
     * @param msgId
     * @return
     */
    public SubscribeDto getConfig(String msgId) {
        if (configMap == null) {
            configMap = CollUtil.newHashMap();
            this.configs.forEach(item -> {
                configMap.put(item.getMsgId(), item);
            });
        }
        if (configMap != null) {
            return this.configMap.get(msgId);
        }
        return null;
    }
}
