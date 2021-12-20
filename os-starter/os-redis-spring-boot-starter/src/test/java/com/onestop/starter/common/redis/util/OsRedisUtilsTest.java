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

package com.onestop.starter.common.redis.util;

import cn.onestop.starter.common.redis.StarterRedisApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Clark
 * @version 2021/5/24
 */
@Slf4j
@SpringBootTest(classes = StarterRedisApplication.class)
class OsRedisUtilsTest {
    @Autowired
    private OsRedisUtils osRedisUtils;

    @Test
    public void redis() {
        try {
            osRedisUtils.del("abc");
            log.error("Expire=" + osRedisUtils.getExpire("abc"));
            osRedisUtils.set("abc", 2, 3);

            for (int i = 0; i < 2; i++) {
                log.error("1 abc=" + osRedisUtils.get("abc"));
                log.error("Expire=" + osRedisUtils.getExpire("abc"));
//                if (osRedisUtils.getExpire("abc") == -2) {
//                    osRedisUtils.set("abc",5, 5);
//                } else {
//                    osRedisUtils.decr("abc");
//                }
//                log.error("2 abc=" + osRedisUtils.get("abc"));
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}