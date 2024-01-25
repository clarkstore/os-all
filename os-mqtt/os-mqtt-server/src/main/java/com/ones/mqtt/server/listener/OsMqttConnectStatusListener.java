package com.ones.mqtt.server.listener;

import com.ones.mqtt.common.constant.OsMqttConsts;
import com.ones.mqtt.common.model.OsMqttMsgDto;
import com.ones.mqtt.server.service.OsMqttServerService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.iot.mqtt.core.server.event.IMqttConnectStatusListener;
import org.tio.core.ChannelContext;

/**
 * mqtt 连接状态
 *
 * @author Clark
 * @version 2024-01-24
 */
@Slf4j
public class OsMqttConnectStatusListener implements IMqttConnectStatusListener {
	@Resource
	protected OsMqttServerService service;

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