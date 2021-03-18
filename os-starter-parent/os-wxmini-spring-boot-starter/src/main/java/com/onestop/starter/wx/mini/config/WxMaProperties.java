package com.onestop.starter.wx.mini.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Clark
 * @version 2020-08-25
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "os.wxmini")
public class WxMaProperties {
    /**
     * 设置微信小程序的appid
     */
    private String appid;
    /**
     * 设置微信小程序的Secret
     */
    private String secret;
    /**
     * 设置微信小程序消息服务器配置的token
     */
    private String token = "onestop";
    /**
     * 设置微信小程序消息服务器配置的EncodingAESKey
     */
    private String aesKey = "SRXP07cF633daFYh7jasMRDHYMKjANeEQe8kwrfWB5p";
    /**
     * 消息格式，XML或者JSON
     */
    private String msgDataFormat = "JSON";
}
