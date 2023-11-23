package com.ones.common.mq.client.listener;

import net.dreamlu.iot.mqtt.codec.MqttQoS;
import net.dreamlu.iot.mqtt.spring.client.MqttClientSubscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * 客户端消息监听
 *
 * @author L.cm
 */
@Service
public class MqttClientSubscribeListener {
	private static final Logger logger = LoggerFactory.getLogger(MqttClientSubscribeListener.class);

//	@MqttClientSubscribe(value = "/test", qos = MqttQoS.EXACTLY_ONCE)
	public void subQos0(String topic, byte[] payload) {
		logger.info("topic:{} payload:{}", topic, new String(payload, StandardCharsets.UTF_8));
	}

	@MqttClientSubscribe(value = "online", qos = MqttQoS.EXACTLY_ONCE)
	public void online(String topic, byte[] payload) {
		logger.info("--------------------online----------------------");
		logger.info("topic:{} payload:{}", topic, new String(payload, StandardCharsets.UTF_8));
	}

	@MqttClientSubscribe(value = "offline", qos = MqttQoS.EXACTLY_ONCE)
	public void offline(String topic, byte[] payload) {
		logger.info("--------------------offline----------------------");
		logger.info("topic:{} payload:{}", topic, new String(payload, StandardCharsets.UTF_8));
	}

//	@MqttClientSubscribe(value = "/qos1/#", qos = MqttQoS.AT_LEAST_ONCE)
	public void subQos1(String topic, byte[] payload) {
		logger.info("topic:{} payload:{}", topic, new String(payload, StandardCharsets.UTF_8));
	}

//	@MqttClientSubscribe("/sys/${productKey}/${deviceName}/thing/sub/register")
	public void thingSubRegister(String topic, byte[] payload) {
		// 1.3.8 开始支持，@MqttClientSubscribe 注解支持 ${} 变量替换，会默认替换成 +
		// 注意：mica-mqtt 会先从 Spring boot 配置中替换参数 ${}，如果存在配置会优先被替换。
		logger.info("topic:{} payload:{}", topic, new String(payload, StandardCharsets.UTF_8));
	}
}