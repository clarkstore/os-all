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