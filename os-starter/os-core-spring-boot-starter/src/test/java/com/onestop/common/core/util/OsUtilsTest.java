package com.onestop.common.core.util;

import com.onestop.starter.common.core.StarterCoreApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@Slf4j
@SpringBootTest(classes = StarterCoreApplication.class)
public class OsUtilsTest {

    @Autowired(required = false)
    private OsMailUtils osMailUtils;
    @Autowired(required = false)
    private OsTokenUtils osTokenUtils;

    @Autowired(required = false)
    private RedissonClient redissonClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void sendMail() {
        osMailUtils.sendMail("changch@neusoft.com","test", null);
    }

    @Test
    public void getToken() {
        String sign = osTokenUtils.sign("abc");
        log.error("======================");
        log.error("sign=" + sign);
        log.error("verify=" + osTokenUtils.verify(sign,"abc"));
    }

    @Test
    public void redis() {
        RAtomicLong atomicLong = this.redissonClient.getAtomicLong("abc");
        log.error("1 abc=" + atomicLong.get());
        atomicLong.delete();
        log.error("2 abc=" + atomicLong.get());
        atomicLong.set(1);
        log.error("3 abc=" + atomicLong.get());
        atomicLong.incrementAndGet();
        log.error("4 abc=" + atomicLong.get());
    }
}