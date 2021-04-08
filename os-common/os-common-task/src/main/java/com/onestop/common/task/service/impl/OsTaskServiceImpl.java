package com.onestop.common.task.service.impl;

import com.onestop.common.task.service.IOsTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 定时任务服务实现类Demo
 *
 * @author Clark
 * @version 2021-04-08
 */
@Slf4j
@Service
public class OsTaskServiceImpl implements IOsTaskService {
    @Override
    public String getTaskNo() {
        return "ostask1";
    }

    @Override
    public void run() {
        log.debug("--------------业务实现---------------");
    }
}
