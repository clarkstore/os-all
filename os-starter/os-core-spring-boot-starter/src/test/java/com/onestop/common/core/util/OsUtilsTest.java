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