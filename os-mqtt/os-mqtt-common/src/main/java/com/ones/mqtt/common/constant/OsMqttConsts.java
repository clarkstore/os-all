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

package com.ones.mqtt.common.constant;

import cn.hutool.core.text.StrPool;

/**
 * 常量类
 *
 * @author Clark
 * @version 2023-11-28
 */
public interface OsMqttConsts {
    /**
     * topic常量类
     */
    interface TopicConsts {
        /**
         * 客户端上线
         */
        String CLIENT_ONLINE = "online";

        /**
         * 未知客户端上线
         */
        String CLIENT_ONLINE_UNKNOWN = CLIENT_ONLINE + StrPool.SLASH + "unknown";

        /**
         * 订阅全部客户端上线通知
         */
        String SUB_CLIENT_ONLINE_ALL = CLIENT_ONLINE + StrPool.SLASH + "#";

        /**
         * 客户端离线
         */
        String CLIENT_OFFLINE = "offline";

        /**
         * 未知客户端离线
         */
        String CLIENT_OFFLINE_UNKNOWN = CLIENT_OFFLINE + StrPool.SLASH + "unknown";

        /**
         * 订阅全部客户端离线通知
         */
        String SUB_CLIENT_OFFLINE_ALL = CLIENT_OFFLINE + StrPool.SLASH + "#";
    }
}