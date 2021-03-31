package com.onestop.starter.common.azure.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Storage配置
 *
 * @author Clark
 * @version 2021-03-30
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "os.azure.storage")
public class StorageProperties {
	/**
	 * 连接字符串
	 */
	private String connectionString;
	/**
	 * 容器名称必须为小写
	 */
	private String containerName = "os-storage";
	/**
	 * token超时时长，默认120分钟
	 */
	private int expireTimeInMinutes = 120;
}