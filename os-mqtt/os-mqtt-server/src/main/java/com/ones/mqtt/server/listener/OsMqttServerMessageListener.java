package com.ones.mqtt.server.listener;

import net.dreamlu.iot.mqtt.codec.MqttPublishMessage;
import net.dreamlu.iot.mqtt.codec.MqttQoS;
import net.dreamlu.iot.mqtt.core.server.event.IMqttMessageListener;
import org.springframework.stereotype.Service;
import org.tio.core.ChannelContext;

/**
 * 消息监听器示例2,优点：性能损失小
 *
 * @author wsq
 */
@Service
public class OsMqttServerMessageListener implements IMqttMessageListener {

	@Override
	public void onMessage(ChannelContext context, String clientId, String topic, MqttQoS qos, MqttPublishMessage message) {
	}
}