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

package com.onestop.wx.mp.model.dto;

import cn.hutool.core.map.MapUtil;
import com.onestop.common.redis.util.OsRedisUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 关键字回复
 *
 * @author Clark
 * @version 2021/05/10
 */
@Getter
@Setter
@ToString
public class ReplyConfigs {
    private static final String REDIS_KEY = "wxmpReply";
    /**
     * 使用Redis
     */
    @Autowired(required = false)
    private OsRedisUtils osRedisUtils;

    /**
     * 关键字回复配置
     */
    private List<ReplyDto> configs;
    /**
     * 文本关键字回复
     */
    private Map<String, String> replyTextMap;

    /**
     * 设置关键字配置
     * @param replyTextMap
     */
    public void setReplyTextMap(Map<String, String> replyTextMap) {
        if (this.osRedisUtils == null) {
            this.replyTextMap = replyTextMap;
        } else {
            // 缓存数据
            Map<String, Object> map = MapUtil.newHashMap();
            replyTextMap.forEach((key, value) -> {
                map.put(key, value);
            });
            this.osRedisUtils.del(REDIS_KEY);
            this.osRedisUtils.hmset(REDIS_KEY, map);
        }
    }

    /**
     * 取得文本关键字回复Map
     * @return Map<String, String>
     */
    public Map<String, String> getReplyTextMap() {
        if (this.replyTextMap == null) {
            this.replyTextMap = MapUtil.newHashMap();

            if (this.osRedisUtils == null) {
                // 取配置数据
                if (this.configs != null) {
                    this.configs.forEach(item -> {
                        this.replyTextMap.put(item.getKeyword(), item.getReplyText());
                    });
                }
            } else {
                // 取缓存数据
                Map<Object, Object> map = this.osRedisUtils.hmget(REDIS_KEY);
                if (map != null) {
                    map.forEach((key, value) -> {
                        replyTextMap.put(String.valueOf(key), String.valueOf(value));
                    });
                }
            }
        }

        return this.replyTextMap;
    }

    /**
     * 取得关键字回复文本
     * @param keyword
     * @return 关键字回复文本
     */
    public String getReplyText(String keyword) {
        return this.getReplyTextMap().get(keyword);
    }
}
