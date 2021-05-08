package com.onestop.starter.common.core.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Mail配置
 *
 * @author Clark
 * @version 2021-02-24
 */
@Getter
@Setter
@ToString
//@ConfigurationProperties(prefix = "os.redis")
public class RedisProperties {
	/**
	 * 邮件服务器的SMTP地址
	 */
	private String host = "localhost";
	/**
	 * 邮件服务器的SMTP端口
	 */
	private Integer port = 25;
	/**
	 * 超时，默认2000
	 */
	private Integer timeout;
	/**
	 * 连接超时，默认timeout
	 */
	private Integer connectionTimeout;
	/**
	 * 读取超时，默认timeout
	 */
	private Integer soTimeout;
	/**
	 * 密码，默认空
	 */
	private String password = "";
	/**
	 * 客户端名，默认"Hutool"
	 */
	private String clientName = "onestop";
	/**
	 * SSL连接，默认false
	 */
	private boolean ssl = false;
}