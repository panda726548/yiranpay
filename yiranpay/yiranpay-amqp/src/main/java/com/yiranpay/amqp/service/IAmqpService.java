package com.yiranpay.amqp.service;

import java.util.Map;

import com.yiranpay.amqp.domain.AmqoRequrst;
import com.yiranpay.common.core.domain.ResultWrapper;


public interface IAmqpService {

	/**
	 * 发送消息
	 * @return
	 */
	public ResultWrapper<Map<String,Object>> sendMessage(AmqoRequrst requrst);
	/**
	 * 接收消息
	 * @param requrst
	 * @return
	 */
	public ResultWrapper<Map<String,Object>> receive(AmqoRequrst requrst);
	
}
