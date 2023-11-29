package com.ones.common.mq.server.listener;

import lombok.extern.slf4j.Slf4j;
import net.dreamlu.iot.mqtt.codec.MqttPublishMessage;
import net.dreamlu.iot.mqtt.codec.MqttQoS;
import net.dreamlu.iot.mqtt.core.server.event.IMqttMessageListener;
import org.tio.core.ChannelContext;

import java.nio.charset.StandardCharsets;

/**
 * 消息监听器示例2,优点：性能损失小
 *
 * @author Clark
 * @version 2023-11-20
 */
@Slf4j
public class OsMqttServerMessageListener implements IMqttMessageListener {
	@Override
	public void onMessage(ChannelContext context, String clientId, String topic, MqttQoS qos, MqttPublishMessage message) {
		log.debug("context:{} clientId:{} message:{} payload:{}", context, clientId, message, new String(message.payload(), StandardCharsets.UTF_8));
	}
}
