/*
 *
 *  * Copyright (c) 2021 os-parent Authors. All Rights Reserved.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.onestop.starter.common.redis.config;

import io.lettuce.core.RedisURI;
import lombok.*;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
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
@ConfigurationProperties(prefix = "spring.redis")
public class OsRedisProperties {
	/**
	 * 邮件服务器的SMTP地址
	 */
	private String host = "127.0.0.1";
	/**
	 * 邮件服务器的SMTP端口
	 */
	private Integer port = 6379;
	/**
	 * 密码，默认空
	 */
	private String password = "";
	/**
	 * 数据库默认使用db0
	 */
	private Integer database = 0;
	/**
	 * 超时
	 */
	private long timeout = RedisURI.DEFAULT_TIMEOUT;

	private Lettuce lettuce = new Lettuce();

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public class Lettuce {
		/**
		 * 在关闭客户端连接之前等待任务处理完成的最长时间，在这之后，无论任务是否执行完成，都会被执行器关闭，默认100ms
		 */
		private long shutdownTimeout = 100;

		private Pool pool = new Pool();

		@Getter
		@Setter
		@NoArgsConstructor
		@AllArgsConstructor
		public class Pool {
			/**
			 * 连接池最大连接数（使用负值表示没有限制）
			 */
			private int maxActive = GenericObjectPoolConfig.DEFAULT_MAX_TOTAL;
			/**
			 * 连接池中的最小空闲连接
			 */
			private int minIdle = GenericObjectPoolConfig.DEFAULT_MIN_IDLE;
			/**
			 * 连接池中的最大空闲连接
			 */
			private int maxIdle = GenericObjectPoolConfig.DEFAULT_MAX_IDLE;
			/**
			 * 连接池最大阻塞等待时间（使用负值表示没有限制）
			 */
			private long maxWait = GenericObjectPoolConfig.DEFAULT_MAX_WAIT_MILLIS;
		}
	}
}