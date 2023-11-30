package com.ones.mqtt.client.service;

import lombok.extern.slf4j.Slf4j;
import net.dreamlu.iot.mqtt.codec.MqttQoS;
import net.dreamlu.iot.mqtt.spring.client.MqttClientTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 *  客户端服务
 * @author Clark
 * @version 2023-11-20
 */
@Slf4j
@Service
public class OsMqttClientService {
	@Autowired
	private MqttClientTemplate client;

	public boolean publish(String body) {
//		client.publish("/test/client", body.getBytes(StandardCharsets.UTF_8));
		client.publish("/test", body.getBytes(StandardCharsets.UTF_8), MqttQoS.AT_LEAST_ONCE, true);
		return true;
	}

	public boolean sub() {
		log.error("-----------------sub-------------------");
		client.subQos0("/test/#", (context, topic, message, payload) -> {
			log.info(topic + '\t' + new String(payload, StandardCharsets.UTF_8));
		});
		return true;
	}

}
