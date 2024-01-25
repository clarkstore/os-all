package com.ones.mqtt.client.event;

import com.ones.mqtt.common.constant.OsMqttConsts;
import net.dreamlu.iot.mqtt.codec.MqttQoS;
import net.dreamlu.iot.mqtt.spring.client.MqttClientSubscribe;

import java.nio.charset.StandardCharsets;

/**
 * 客户端连接状态消息监听
 *
 * @author L.cm
 */
public interface IOsMqttConnectStatusListener {
	/**
	 * 客户端上线通知
	 * @param topic
	 * @param payload
	 */
	@MqttClientSubscribe(value = OsMqttConsts.TopicConsts.SUB_CLIENT_ONLINE_ALL, qos = MqttQoS.EXACTLY_ONCE)
	default void online(String topic, byte[] payload) {
//		log.info("--------------------online----------------------");
//		log.info("topic:{} payload:{}", topic, new String(payload, StandardCharsets.UTF_8));
		this.online(topic, new String(payload, StandardCharsets.UTF_8));
	}

	/**
	 * 重写上线通知业务逻辑
	 * @param topic
	 * @param payload
	 */
	void online(String topic, String payload);

	/**
	 * 客户端离线通知
	 * @param topic
	 * @param payload
	 */
	@MqttClientSubscribe(value = OsMqttConsts.TopicConsts.SUB_CLIENT_OFFLINE_ALL, qos = MqttQoS.EXACTLY_ONCE)
	default void offline(String topic, byte[] payload) {
//		log.info("--------------------offline----------------------");
//		log.info("topic:{} payload:{}", topic, new String(payload, StandardCharsets.UTF_8));
		this.offline(topic, new String(payload, StandardCharsets.UTF_8));
	}

	/**
	 * 重写离线通知业务逻辑
	 * @param topic
	 * @param payload
	 */
	void offline(String topic, String payload);
}