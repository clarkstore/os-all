package com.onestop.common.core.util;

import com.onestop.starter.common.core.StarterCoreApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = StarterCoreApplication.class)
public class OsUtilsTest {

    @Autowired(required = false)
    private OsMailUtils osMailUtils;
    @Autowired(required = false)
    private OsTokenUtils osTokenUtils;

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
}