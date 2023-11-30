package com.ones.mqtt.client.api;

import com.ones.mqtt.client.service.OsMqttClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Mqtt::客户端")
@RequestMapping("/mqtt/client")
@RestController
public class OsMqttClientApi {

	@Autowired
	private OsMqttClientService service;

	@Operation(summary = "publish")
	@PostMapping("/publish")
	public boolean publish(@RequestBody String body) {
		return service.publish(body);
	}

	@Operation(summary = "sub")
	@GetMapping("/sub")
	public boolean sub() {
		return service.sub();
	}
}
