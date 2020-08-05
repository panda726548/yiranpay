package com.channel.bank.adapter.pay.service.impl;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.channel.bank.adapter.pay.domain.AmqoRequrst;
import com.channel.bank.adapter.pay.service.IAmqpService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AmqpServiceImpl implements IAmqpService {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
    private ObjectMapper objectMapper;
	@Override
	public boolean sendMessage(AmqoRequrst requrst) {
		try {
			rabbitTemplate.setExchange(requrst.getExchange());
	        rabbitTemplate.setRoutingKey(requrst.getRoutingKey());
	        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

	        Message msg=MessageBuilder.withBody(objectMapper.writeValueAsBytes(requrst.getMap().get("message"))).setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
	                .build();
	        rabbitTemplate.convertAndSend(msg);
			return true;
		} catch (AmqpException | JsonProcessingException e) {
			e.printStackTrace();
		}
		return false;
	}

	
}
