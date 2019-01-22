package com.common.backend.sockets;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketsController {

	private final SimpMessagingTemplate template;

	@Autowired
	WebSocketsController(SimpMessagingTemplate template) {
		this.template = template;
	}

	@MessageMapping("/send/message")
	public void onReceivedMessage(String message) {
		this.template.convertAndSend("/chat", new SimpleDateFormat("HH:mm:ss").format(new Date()) + " - " + message);
	}

	@MessageMapping("/action")
	public void handle(SimpMessageHeaderAccessor headerAccessor) {
		Map<String, Object> attrs = headerAccessor.getSessionAttributes();
		// ...
	}
}
