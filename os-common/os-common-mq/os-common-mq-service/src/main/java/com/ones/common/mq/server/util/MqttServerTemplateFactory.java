package com.ones.common.mq.server.util;

import lombok.Getter;
import net.dreamlu.iot.mqtt.spring.server.MqttServerTemplate;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * MqttServerTemplate 单例
 * @author Clark
 * @version 2023-11-23
 */
@Component
public class MqttServerTemplateFactory implements SmartInitializingSingleton {
    @Autowired
    private ApplicationContext applicationContext;
    @Getter
    private MqttServerTemplate mqttServerTemplate;

    /**
     * 单例 bean 初始化完成之后从 ApplicationContext 中获取 bean
     */
    @Override
    public void afterSingletonsInstantiated() {
        this.mqttServerTemplate = applicationContext.getBean(MqttServerTemplate.class);
    }
}