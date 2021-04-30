package com.onestop.starter.wx.mp.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Clark
 * @version 2021-04-23
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "os.wxmp")
public class OsWxmpProperties {
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
