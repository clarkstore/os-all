package com.ones.mqtt.client.service;

import com.ones.mqtt.common.model.OsMqttMsgDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.iot.mqtt.codec.MqttQoS;
import net.dreamlu.iot.mqtt.spring.client.MqttClientTemplate;
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
	@Getter
	@Autowired
	private MqttClientTemplate client;

	/**
	 *
	 * @param msgDto
	 * @return
	 */
	public boolean publish(OsMqttMsgDto msgDto) {
		boolean result = this.client.publish(msgDto.getTopic(), msgDto.getPayload().getBytes(StandardCharsets.UTF_8), msgDto.getQoS(), msgDto.isRetain());
		return result;
	}

	public boolean sub() {
		log.error("-----------------sub-------------------");
		client.subQos0("/test/#", (context, topic, message, payload) -> {
			log.info(topic + '\t' + new String(payload, StandardCharsets.UTF_8));
		});
		return true;
	}

}
