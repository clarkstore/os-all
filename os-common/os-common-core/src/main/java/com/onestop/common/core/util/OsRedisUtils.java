package com.onestop.common.core.util;

import cn.hutool.db.nosql.redis.RedisDS;
import org.springframework.stereotype.Component;

/**
 * Redis工具类
 *
 * @author Clark
 * @version 2020-12-23
 */
public class OsRedisUtils {
    /**
     * 从Redis中获取值
     *
     * @param key 键
     * @return 状态码
     */
    public String getStr(String key) {
        return RedisDS.create().getStr(key);
    }

    /**
     * 向Redis中赋值
     *
     * @param key   键
     * @param value 值
     * @return 值
     */
    public String setStr(String key, String value) {
        return RedisDS.create().setStr(key, value);
    }


    /**
     * 从Redis中删除多个值
     *
     * @param keys 需要删除值对应的键列表
     * @return 删除个数，0表示无key可删除
     */
    public long del(String... keys) {
        return RedisDS.create().del(keys);
    }
}