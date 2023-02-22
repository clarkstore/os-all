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

package com.onestop.extra.toolkit;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.onestop.common.redis.util.OsRedisUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 流水号工具类
 *
 * @author Clark
 * @version 2023-02-16
 */
public class OsSeqUtils {
    @Autowired
    private OsRedisUtils redisUtils;
    private OsSeqConfig seqConfig;
    public OsSeqUtils(OsSeqConfig seqConfig) {
        this.seqConfig = seqConfig;
    }

    /**
     * 取流水号
     * @return
     */
    public String getSeqNo() {
        return this.getSeqNo(null);
    }

    /**
     * 根据业务标识取流水号
     * @param bizFlag
     * @return
     */
    public String getSeqNo(String bizFlag) {
        long seqNo = 1;
        String todayStr = DateUtil.format(LocalDateTime.now(), this.seqConfig.getDateFormat());
        Map<String, String> map = MapUtil.newHashMap();
        map.put("bizFlag", bizFlag);
        map.put("date", todayStr);

        String redisKey = StrUtil.format(OsSeqConfig.redisKeyTemplate, map);
        if (this.redisUtils.hasKey(redisKey)) {
            seqNo = this.redisUtils.incr(redisKey);
        } else {
            this.redisUtils.set(redisKey, seqNo);
        }

        String seqStr = StrUtil.fillBefore(String.valueOf(seqNo), '0', this.seqConfig.getSeqLength());
        map.put("seq", seqStr);

        return StrUtil.format(this.seqConfig.getTemplate(), map, false);
    }

    /**
     * 流水号配置类
     */
    @Setter
    @Getter
    @ToString
    public static class OsSeqConfig {
        /**
         * 序号缓存key
         */
        public static final String redisKeyTemplate = "os-{date}{bizFlag}";
        /**
         * 序号规则模板 {bizFlag}{date}{seq}三元素组合，默认值{bizFlag}{date}{seq}
         * bizFlag业务部分标识：序号规则模板中做前缀可以为字母和数字，中间建议为数字，通过传入bizFlag参数取得不同业务模块序号
         */
        private String template = "{bizFlag}{date}{seq}";
        /**
         * 日期部分格式：yyyyMMdd/yyMMdd/yyyyMMddHHmmss，可以缺省此项配置，默认值：yyyyMMdd
         */
        private String dateFormat = "yyyyMMdd";
        /**
         * 序号部分长度：可以缺省此项配置，默认值：6
         */
        private int seqLength = 6;
    }
}
