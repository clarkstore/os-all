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
import java.util.concurrent.locks.ReentrantLock;

/**
 * 流水号工具类
 *
 * @author Clark
 * @version 2023-02-16
 */
public class OsSeqUtils {
    /**
     * 记录已有流水单
     */
    private long seqNo = 1;
    /**
     * 配置参数
     */
    Map<String, String> map = MapUtil.newHashMap();
    private static ReentrantLock RLock = new ReentrantLock();
    /**
     * redis缓存24小时过期
     */
    private final long REDIS_DURATION_SECONDS = 60 * 60 * 24;
    /**
     * 配置缓存可以根据不同业务类型分别创建序列号
     */
    @Autowired(required = false)
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
        // 使用redis
        if (this.seqConfig.isUseRedis()) {
            return this.getSeqByRedis(StrUtil.EMPTY);
        }
        return this.getSeq(StrUtil.EMPTY);
    }

    /**
     * 根据业务标识取流水号
     * @param bizFlag
     * @return
     */
    public String getSeqByRedis(String bizFlag) {
        String todayStr = DateUtil.format(LocalDateTime.now(), this.seqConfig.getDateFormat());
        this.map.put("bizFlag", bizFlag);
        this.map.put("date", todayStr);

        String redisKey = StrUtil.format(OsSeqConfig.redisKeyTemplate, this.map);
        if (this.redisUtils.hasKey(redisKey)) {
            //上锁
            RLock.lock();
            try {
                this.seqNo = this.redisUtils.incr(redisKey);
            } finally {
                //释放锁
                RLock.unlock();
            }
        } else {
            // 缓存24小时
            this.redisUtils.set(redisKey, this.seqNo, REDIS_DURATION_SECONDS);
        }

        String seqStr = StrUtil.fillBefore(String.valueOf(this.seqNo), '0', this.seqConfig.getSeqLength());
        map.put("seq", seqStr);

        return StrUtil.format(this.seqConfig.getTemplate(), this.map, false);
    }

    /**
     * 取得流水号
     * @param bizFlag
     * @return
     */
    public String getSeq(String bizFlag) {
        //上锁
        RLock.lock();
        try {
            String todayStr = DateUtil.format(LocalDateTime.now(), this.seqConfig.getDateFormat());
            if (todayStr.equals(this.map.get("date"))) {
                //当日流水号+1
                this.seqNo += 1;
            } else {
                //重置流水号
                this.seqNo = 1;
            }
            this.map.put("bizFlag", bizFlag);
            this.map.put("date", todayStr);

            String seqStr = StrUtil.fillBefore(String.valueOf(this.seqNo), '0', this.seqConfig.getSeqLength());
            this.map.put("seq", seqStr);

            return StrUtil.format(this.seqConfig.getTemplate(), this.map, false);
        } finally {
            //释放锁
            RLock.unlock();
        }
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
         * 序号部分长度
         */
        private int seqLength;
        /**
         * 使用redis
         */
        private boolean useRedis;
    }
}
