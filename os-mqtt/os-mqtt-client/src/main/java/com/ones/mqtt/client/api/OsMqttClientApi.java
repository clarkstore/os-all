package com.ones.mqtt.client.api;

import com.ones.mqtt.client.util.OsMqttClientUtils;
import com.ones.mqtt.common.model.OsMqttMsgDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息发布Api
 *
 * @author Clark
 * @version 2024-01-25
 */
@Tag(name = "Mqtt::客户端")
@RequestMapping("/mqtt/client")
@RestController
public class OsMqttClientApi {

	@Autowired
	private OsMqttClientUtils service;

	@Operation(summary = "publish")
	@PostMapping("/publish")
	public boolean publish(@RequestBody OsMqttMsgDto msgDto) {
		return service.publish(msgDto);
	}
}