package com.onestop.common.log.service;

import com.onestop.common.log.model.entity.BizLog;

/**
 * <p>
 * 接口日志表 服务类
 * </p>
 *
 * @author Clark
 * @version 2022-03-03
 */
public interface IBizLogService {
    void save(BizLog bizLog);
}
