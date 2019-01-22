package com.common.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@EnableAutoConfiguration
@ComponentScan("com.common.backend.*")
@EnableJpaRepositories("com.common.backend.*")
@EnableAsync
@EnableScheduling
@SpringBootApplication
@EnableWebSocketMessageBroker
public class CommonBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonBackEndApplication.class, args);
	}

}

