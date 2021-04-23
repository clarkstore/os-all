package com.onestop.starter.wx.mp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Clark
 * @version 2021-04-23
 */
@Data
@ConfigurationProperties(prefix = "os.wxmp")
public class WxMpProperties {
    /**
     * 设置微信公众号的appid
     */
    private String appid;

    /**
     * 设置微信公众号的app secret
     */
    private String secret;

    /**
     * 设置微信公众号的token
     */
    private String token = "onestop";

    /**
     * 设置微信公众号的EncodingAESKey
     */
    private String aesKey = "SRXP07cF633daFYh7jasMRDHYMKjANeEQe8kwrfWB5p";
}
