package com.onestop.common.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Token配置
 *
 * @author Clark
 * @version 2021-01-08
 */
@Data
@ConfigurationProperties(prefix = "os.token")
public class TokenProperties {
	/**
	 * 设置token过期时间
	 */
	private long expireTime;
	/**
	 * 设置token的Secret
	 */
	private String secret;
	/**
	 * 设置token的claim
	 */
	private String claim;
}