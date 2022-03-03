package com.onestop.common.log.service.impl;

import com.onestop.common.log.model.entity.BizLog;
import com.onestop.common.log.mapper.BizLogMapper;
import com.onestop.common.log.service.IBizLogService;
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
