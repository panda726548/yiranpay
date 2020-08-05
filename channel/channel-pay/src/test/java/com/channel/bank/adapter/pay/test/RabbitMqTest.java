package com.channel.bank.adapter.pay.test;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.channel.bank.adapter.pay.domain.AmqoRequrst;
import com.channel.bank.adapter.pay.domain.ChannelFundResult;
import com.channel.bank.adapter.pay.enums.FundChannelApiType;
import com.channel.bank.adapter.pay.service.IAmqpService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class RabbitMqTest extends BaseJunit{

    @Autowired
    private RabbitTemplate rabbitTemplate;
 
    @Autowired
    private ObjectMapper objectMapper;
	@Autowired
	private IAmqpService amqpService;
	/**
	 * 点对点
	 * @throws JsonProcessingException 
	 */
	@Test
	public void test1() throws JsonProcessingException{
		//message需要自己构造，可以定制消息体内容和消息头
		//rabbitTemplate.send(exchange, routingKey, message);
		
		//object默认被当成消息体，只需要传入要发送的对象，自动序列化发送给rabbit
		//rabbitTemplate.convertAndSend(exchange, routingKey, object);
		//消息被序列化后发送
		//rabbitTemplate.convertAndSend("exchange.direct", "atguigu", map);
		AmqoRequrst requrst = new AmqoRequrst();
    	requrst.setExchange("exchange.payresult.process");
    	requrst.setRoutingKey("key.payresult.process");
		Map<String,Object> map = new HashMap<String,Object>();
		ChannelFundResult result = new ChannelFundResult();
		result.setApiResultCode("0000");
		result.setApiResultMessage("异步通知支付成功");
		result.setApiResultSubCode("SUCCESS");
		result.setApiType(FundChannelApiType.NOTIFY);
		result.setInstOrderNo("WXPAY20200212002549000000");
		result.setInstReturnOrderNo("4200000505202002127272026788");
		result.setSuccess(true);
		result.setExtension("");
		//map.put("result", result);
		//requrst.setMap(map);
    	//amqpService.sendMessage(requrst);
    	
    	
    	rabbitTemplate.setExchange("exchange.payresult.process");
        rabbitTemplate.setRoutingKey("key.payresult.process");
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        Message msg=MessageBuilder.withBody(objectMapper.writeValueAsBytes(result)).setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                .build();
        rabbitTemplate.convertAndSend(msg);
		System.out.println("消息发送完毕");
	}
	
	/*@Test
	public void test2(){
		
		Object obj = rabbitTemplate.receiveAndConvert("pay_fundchannel_queue");
		System.out.println(obj.getClass());
		System.out.println(obj);
		
	}*/
}
