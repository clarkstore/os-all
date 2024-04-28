/*
 *
 *  * Copyright (C) 2021 ClarkChang
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *         http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.ones.common.log.service.impl;

import com.ones.common.log.mapper.BizLogMapper;
import com.ones.common.log.model.entity.BizLog;
import com.ones.common.log.service.IBizLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 接口日志表 服务实现类
 * </p>
 *
 * @author Clark
 * @version 2022-03-03
 */
@Service
public class BizLogServiceImpl implements IBizLogService {
    @Autowired
    private BizLogMapper bizLogMapper;

    @Async("asyncLogExecutor")
    @Override
    public void save(BizLog bizLog) {
        this.bizLogMapper.insert(bizLog);
    }
}
