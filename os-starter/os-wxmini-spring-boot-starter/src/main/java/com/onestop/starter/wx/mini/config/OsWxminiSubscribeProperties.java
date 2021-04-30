package com.onestop.starter.wx.mini.config;

import com.onestop.wx.mini.util.dto.SubscribeDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 订阅消息模板配置
 *
 * @author Clark
 * @version 2021-03-18
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "os.wxmini.subscribe")
public class OsWxminiSubscribeProperties {
    /**
     * 跳转小程序类型：developer为开发版；trial为体验版；formal为正式版；默认为正式版
     */
    private String miniprogramState = "formal";
    /**
     * 订阅消息配置
     */
    private List<SubscribeDto> configs;
}
