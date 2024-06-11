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

package com.ones.kit.oss;

import com.aizuda.common.toolkit.IoUtils;
import com.aizuda.oss.IFileStorage;
import com.aizuda.oss.model.OssResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

/**
 * 基于aizuda OSS文件存储
 * <p>https://gitee.com/aizuda/aizuda-components/tree/master/aizuda-oss</p>
 *
 * @author Clark
 * @version 2024-04-23
 */
@Component
public class OsOssUtils {
    @Autowired
    private IFileStorage fileStorage;

    public void upload(String filename) {
        OssResult ossResult = null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            IoUtils.write(new FileInputStream(filename), os);
            ByteArrayInputStream bis = new ByteArrayInputStream(os.toByteArray());
            ossResult = fileStorage.bucket("dev").upload(bis, "abc");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(ossResult.toString());
    }
}
