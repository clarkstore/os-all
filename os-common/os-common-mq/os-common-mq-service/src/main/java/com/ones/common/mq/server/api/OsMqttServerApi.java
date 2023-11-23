package com.ones.common.mq.server.api;

import com.ones.common.mq.server.model.OsMqttMsgDto;
import com.ones.common.mq.server.service.OsMqttService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息发布Api
 *
 * @author Clark
 * @version 2023-11-20
 */
@Slf4j
@Tag(name = "Mqtt::服务端")
@RequestMapping("/mqtt/server")
@RestController
public class OsMqttServerApi {
	@Autowired
	private OsMqttService service;

	/**
	 * 发布消息
	 * @param msgDto
	 * @return
	 */
	@Operation(summary = "publish")
	@PostMapping("/publish")
	public boolean publish(@RequestBody OsMqttMsgDto msgDto) {
		return service.publish(msgDto);
	}
}