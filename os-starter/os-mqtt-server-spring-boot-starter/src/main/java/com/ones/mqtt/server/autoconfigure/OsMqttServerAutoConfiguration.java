package com.ones.mqtt.server.autoconfigure;

import com.ones.mqtt.server.listener.OsMqttConnectStatusListener;
import com.ones.mqtt.server.listener.OsMqttServerMessageListener;
import net.dreamlu.iot.mqtt.core.server.event.IMqttConnectStatusListener;
import net.dreamlu.iot.mqtt.core.server.event.IMqttMessageListener;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * 配置
 *
 * @author Clark
 * @version 2023-11-28
 */
@AutoConfiguration
public class OsMqttServerAutoConfiguration {
    /**
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public IMqttConnectStatusListener mqttConnectStatusListener() {
        return new OsMqttConnectStatusListener();
    }

    /**
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public IMqttMessageListener mqttServerMessageListener() {
        return new OsMqttServerMessageListener();
    }
}