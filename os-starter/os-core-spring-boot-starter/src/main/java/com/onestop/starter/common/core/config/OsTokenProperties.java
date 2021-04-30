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
public class OsTokenProperties {
	/**
	 * 设置token的Secret
	 */
	private String secret = "1234567890ABCDEF";
	/**
	 * token超时时长，默认120分钟
	 */
	private int expireTimeInMinutes = 120;
	/**
	 * 设置token的claim
	 */
	private String claimKey = "keyword";
}