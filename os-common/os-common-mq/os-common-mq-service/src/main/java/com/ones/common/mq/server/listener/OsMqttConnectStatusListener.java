/*
 * Copyright (c) 2019-2029, Dreamlu 卢春梦 (596392912@qq.com & dreamlu.net).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ones.common.mq.server.listener;

import com.ones.common.mq.server.model.OsMqttMsgDto;
import com.ones.common.mq.server.service.OsMqttService;
import com.ones.common.mq.server.util.MqttServerTemplateFactory;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.iot.mqtt.codec.MqttQoS;
import net.dreamlu.iot.mqtt.core.server.event.IMqttConnectStatusListener;
import net.dreamlu.iot.mqtt.spring.server.MqttServerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tio.core.ChannelContext;
import org.tio.utils.hutool.StrUtil;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * mqtt 连接状态
 *
 * @author Clark
 * @version 2023-11-20
 */
@Slf4j
@Service
public class OsMqttConnectStatusListener implements IMqttConnectStatusListener {
	@Autowired
	private OsMqttService service;
	@Override
	public void online(ChannelContext context, String clientId, String username) {
		log.error("客户端上线通知-clientId:{}, username:{}", clientId, username);
		OsMqttMsgDto dto = new OsMqttMsgDto();
		dto.setTopic("online");
		dto.setPayload(clientId);
		dto.setRetain(Boolean.TRUE);
		this.service.publish(dto);
	}

	/**
	 * 客户端离线监听
	 * @param context
	 * @param clientId
	 * @param username
	 * @param reason
	 */
	@Override
	public void offline(ChannelContext context, String clientId, String username, String reason) {
		log.info("客户端下线通知-clientId:{}, username:{}, reason:{}.", clientId, username, reason);
		OsMqttMsgDto dto = new OsMqttMsgDto();
		dto.setTopic("offline");
		dto.setPayload(clientId);
		dto.setRetain(Boolean.TRUE);
		this.service.publish(dto);
	}
}
