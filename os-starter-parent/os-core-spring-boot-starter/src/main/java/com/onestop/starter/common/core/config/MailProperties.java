package com.onestop.starter.common.core.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Mail配置
 *
 * @author Clark
 * @version 2021-02-24
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "os.mail")
public class MailProperties {
	/**
	 * 邮件服务器的SMTP地址
	 */
	private String host = "smtp.126.com";
	/**
	 * 邮件服务器的SMTP端口
	 */
	private Integer port = 25;
	/**
	 * 设置token的claim
	 */
	private String from;
	/**
	 * 发件人（必须正确，否则发送失败）
	 */
	private String user;
	/**
	 * 密码（注意，某些邮箱需要为SMTP服务单独设置密码，详情查看相关帮助）
	 */
	private String pass;
	/**
	 * 使用 STARTTLS安全连接，STARTTLS是对纯文本通信协议的扩展
	 */
	private boolean starttlsEnable = false;
	/**
	 * 使用SSL安全连接
	 */
	private boolean sslEnable = false;
}