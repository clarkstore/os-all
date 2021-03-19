package com.onestop.starter.common.core.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Token配置
 *
 * @author Clark
 * @version 2021-01-08
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "os.token")
public class TokenProperties {
	/**
	 * 设置token的Secret
	 */
	private String secret = "1234567890ABCDEF";
	/**
	 * 设置token过期时间
	 */
	private long expireTime = 7200000;
	/**
	 * 设置token的claim
	 */
	private String claimKey = "keyword";
}