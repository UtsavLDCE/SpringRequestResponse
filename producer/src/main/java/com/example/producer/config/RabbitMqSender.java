package com.example.producer.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.example.producer.dto.User;

@Service
public class RabbitMqSender {

	private RabbitTemplate rabbitTemplate;

	@Autowired
	public RabbitMqSender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Value("${spring.rabbitmq.exchange}")
	private String exchange;
	@Value("${spring.rabbitmq.routingkey}")
	private String routingkey;

	public void send(com.example.producer.dto.User user) {
//		rabbitTemplate.convertAndSend(exchange, routingkey, user);
		User test=rabbitTemplate.convertSendAndReceiveAsType(exchange, routingkey, user, new ParameterizedTypeReference<>() {
		});
		System.out.println(test.toString());
	}
}
