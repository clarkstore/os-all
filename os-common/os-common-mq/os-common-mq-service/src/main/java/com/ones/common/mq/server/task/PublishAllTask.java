package com.ones.common.mq.server.task;

import net.dreamlu.iot.mqtt.core.server.MqttServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @author wsq
 */
@Service
public class PublishAllTask {
	private static final Logger logger = LoggerFactory.getLogger(PublishAllTask.class);
	@Autowired
	private MqttServer mqttServer;
	static int num = 0;

	@Scheduled(fixedDelay = 3000)
	public void run() {
		logger.error("-----------------");
//		boolean res = mqttServer.publishAll("clark", ("共享订阅"+num++).getBytes(StandardCharsets.UTF_8));
//		logger.error("res:" + res);
		boolean res = mqttServer.publishAll("abc", ("分组订阅"+num++).getBytes(StandardCharsets.UTF_8));
		logger.error("res:" + res);
	}
}
