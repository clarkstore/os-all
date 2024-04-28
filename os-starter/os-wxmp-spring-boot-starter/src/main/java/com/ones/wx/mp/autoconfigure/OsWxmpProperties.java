/*
 *
 *  * Copyright (C) 2021 ClarkChang
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *         http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.ones.wx.mp.autoconfigure;

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
