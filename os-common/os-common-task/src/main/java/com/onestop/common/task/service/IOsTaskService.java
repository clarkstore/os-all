package com.onestop.common.task.service;

/**
 * 定时任务服务接口
 * @author Clark
 * @version 2021-04-08
 */
public interface IOsTaskService extends Runnable {
    /**
     * 任务编号
     * @return 任务编号
     */
    String getTaskNo();
}
