package com.onestop.common.core.util;

import com.onestop.starter.common.core.StarterCoreApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
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
    private OsRedisUtils osRedisUtils;

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
//        try {
//            osRedisUtils.set("abc",5, 5);
//
//            for (int i = 0; i < 10; i++) {
//                log.error("1 abc=" + osRedisUtils.get("abc"));
//                if (osRedisUtils.getExpire("abc") == -2) {
//                    osRedisUtils.set("abc",5, 5);
//                } else {
//                    osRedisUtils.decr("abc");
//                }
//                log.error("2 abc=" + osRedisUtils.get("abc"));
//                Thread.sleep(1000);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}