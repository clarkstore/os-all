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

package com.ones.mqtt.server.listener;

import com.ones.mqtt.common.constant.OsMqttConsts;
import com.ones.mqtt.common.model.OsMqttMsgDto;
import com.ones.mqtt.server.service.OsMqttService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.iot.mqtt.core.server.event.IMqttConnectStatusListener;
import org.tio.core.ChannelContext;

/**
 * mqtt 连接状态
 *
 * @author Clark
 * @version 2023-11-20
 */
@Slf4j
public class OsMqttConnectStatusListener implements IMqttConnectStatusListener {
	@Resource
	private OsMqttService service;

	/**
	 * 客户端上线监听
	 * @param context
	 * @param clientId
	 * @param username
	 */
	@Override
	public void online(ChannelContext context, String clientId, String username) {
		log.info("客户端上线通知-clientId:{}, username:{}", clientId, username);
		OsMqttMsgDto dto = new OsMqttMsgDto();
		dto.setTopic(OsMqttConsts.TopicConsts.CLIENT_ONLINE);
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
		dto.setTopic(OsMqttConsts.TopicConsts.CLIENT_OFFLINE);
		dto.setPayload(clientId);
		dto.setRetain(Boolean.TRUE);
		this.service.publish(dto);
	}
}