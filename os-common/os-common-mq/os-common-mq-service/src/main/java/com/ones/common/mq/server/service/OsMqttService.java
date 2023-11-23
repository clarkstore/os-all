package com.ones.common.mq.server.service;

import com.ones.common.mq.server.model.OsMqttMsgDto;
import com.ones.common.mq.server.util.MqttServerTemplateFactory;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.iot.mqtt.codec.MqttQoS;
import net.dreamlu.iot.mqtt.spring.server.MqttServerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OsMqttService {
	@Autowired
	private MqttServerTemplateFactory factory;

	/**
	 * 发布消息
	 * @param msgDto
	 * @return
	 */
	public boolean publish(OsMqttMsgDto msgDto) {
		boolean result;
		if (StrUtil.isBlank(msgDto.getClientId())) {
			result = this.factory.getMqttServerTemplate().publishAll(msgDto.getTopic(), msgDto.getPayload().getBytes(StandardCharsets.UTF_8), msgDto.getQoS(), msgDto.isRetain());
		} else {
			result = this.factory.getMqttServerTemplate().publish(msgDto.getClientId(), msgDto.getTopic(), msgDto.getPayload().getBytes(StandardCharsets.UTF_8), msgDto.getQoS(), msgDto.isRetain());
		}
		log.debug("publish:{}, result:{}", msgDto, result);
		return result;
	}
}