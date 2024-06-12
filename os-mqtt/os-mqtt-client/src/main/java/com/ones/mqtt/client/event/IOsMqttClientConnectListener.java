package com.ones.mqtt.client.event;

import net.dreamlu.iot.mqtt.spring.client.event.MqttConnectedEvent;
import net.dreamlu.iot.mqtt.spring.client.event.MqttDisconnectEvent;
import org.springframework.context.event.EventListener;

/**
 * 客户端连接状态监听
 *
 * @author Clark
 * @version 2024-01-25
 */
@Deprecated
public interface IOsMqttClientConnectListener {
	/**
	 * 已连接
	 * @param event
	 */
	@EventListener
	default void onConnected(MqttConnectedEvent event) {
//		log.info("================已连接==================");
//		log.info("MqttConnectedEvent:{}", event);
		this.onConnected();
	}

	/**
	 * 重写已连接业务逻辑
	 */
	void onConnected();

	/**
	 * 已断开
	 * @param event
	 */
	@EventListener
	default void onDisconnect(MqttDisconnectEvent event) {
//		log.info("================已断开==================");
//		log.info("MqttDisconnectEvent:{}", event);
		// 在断线时更新 clientId、username、password
//		mqttClientCreator.clientId("newClient" + System.currentTimeMillis())
//			.username("newUserName")
//			.password("newPassword");
		this.onDisconnect();
	}

	/**
	 * 重写已断开业务逻辑
	 */
	void onDisconnect();
}