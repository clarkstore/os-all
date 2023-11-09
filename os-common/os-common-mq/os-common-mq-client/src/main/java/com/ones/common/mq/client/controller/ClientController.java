package com.ones.common.mq.client.controller;

import com.ones.common.mq.client.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Mqtt::客户端")
@RequestMapping("/mqtt/client")
@RestController
public class ClientController {

	@Autowired
	private ClientService service;

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
