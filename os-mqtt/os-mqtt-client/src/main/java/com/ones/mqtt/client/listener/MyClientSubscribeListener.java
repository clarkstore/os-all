package com.ones.mqtt.client.listener;

import net.dreamlu.iot.mqtt.core.client.MqttClientCreator;
import net.dreamlu.iot.mqtt.core.common.TopicFilterType;
import net.dreamlu.iot.mqtt.spring.client.MqttClientSubscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;

/**
 * 客户端消息监听
 *
 * @author L.cm
 */
//@Service
public class MyClientSubscribeListener {
	private static final Logger logger = LoggerFactory.getLogger(MyClientSubscribeListener.class);
	@Autowired
	private MqttClientCreator mqttClientCreator;

	@MqttClientSubscribe(value = "$queue/clark")
	public void clark1(String topic, byte[] payload) {
		logger.error("----------queue-------------" + this.mqttClientCreator.getClientId());
		logger.info("topic:{} payload:{}", topic, new String(payload, StandardCharsets.UTF_8));
	}

	@MqttClientSubscribe(TopicFilterType.SHARE_GROUP_PREFIX + "wcs1/abc")
	public void shareGroup1(String topic, byte[] payload) {
		logger.error("----------wcs1-------------MyClientSubscribeListener");
		logger.info("topic:{} payload:{}", topic, new String(payload, StandardCharsets.UTF_8));
	}

	@MqttClientSubscribe(TopicFilterType.SHARE_GROUP_PREFIX + "wcs2/abc")
	public void shareGroup2(String topic, byte[] payload) {
		logger.error("----------wcs2-------------MyClientSubscribeListener");
		logger.info("topic:{} payload:{}", topic, new String(payload, StandardCharsets.UTF_8));
	}
}

