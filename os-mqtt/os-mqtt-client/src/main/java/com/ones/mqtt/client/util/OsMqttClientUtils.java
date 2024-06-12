package com.ones.mqtt.client.util;

import com.ones.mqtt.common.model.OsMqttMsgDto;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.iot.mqtt.core.client.IMqttClientMessageListener;
import net.dreamlu.iot.mqtt.spring.client.MqttClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 *  客户端工具类
 * @author Clark
 * @version 2024-06-12
 */
@Slf4j
@Component
public class OsMqttClientUtils {
	@Getter
	@Resource
	private MqttClientTemplate client;

	/**
	 * 消息发布
	 * @param msgDto
	 * @return
	 */
	public boolean publish(OsMqttMsgDto msgDto) {
		boolean result = this.client.publish(msgDto.getTopic(), msgDto.getPayload().getBytes(StandardCharsets.UTF_8), msgDto.getQoS(), msgDto.isRetain());
		return result;
	}
}