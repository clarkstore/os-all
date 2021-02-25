package com.onestop.common.core.util;

import com.onestop.starter.common.core.StarterCoreApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = StarterCoreApplication.class)
public class OsMailUtilsTest {

    @Autowired
    private OsMailUtils osMailUtils;

    @Test
    public void sendMail() {
        osMailUtils.sendMail("changch@neusoft.com","test", null);
    }
}