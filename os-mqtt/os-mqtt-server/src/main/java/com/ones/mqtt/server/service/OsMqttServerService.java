package com.ones.mqtt.server.service;

import com.ones.mqtt.common.model.OsMqttMsgDto;
import com.ones.mqtt.server.util.MqttServerFactory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tio.utils.hutool.StrUtil;

import java.nio.charset.StandardCharsets;

/**
 * 服务端服务
 *
 * @author Clark
 * @version 2023-11-20
 */
@Slf4j
@Service
public class OsMqttServerService {
	@Resource
	protected MqttServerFactory factory;

	/**
	 * 发布消息
	 * @param msgDto
	 * @return
	 */
	public boolean publish(OsMqttMsgDto msgDto) {
		boolean result;
		if (StrUtil.isBlank(msgDto.getClientId())) {
			result = this.factory.getMqttServer().publishAll(msgDto.getTopic(), msgDto.getPayload().getBytes(StandardCharsets.UTF_8), msgDto.getQoS(), msgDto.isRetain());
		} else {
			result = this.factory.getMqttServer().publish(msgDto.getClientId(), msgDto.getTopic(), msgDto.getPayload().getBytes(StandardCharsets.UTF_8), msgDto.getQoS(), msgDto.isRetain());
		}
		log.debug("publish:{}, result:{}", msgDto, result);
		return result;
	}
}