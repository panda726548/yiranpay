package com.channel.bank.adapter.pay.service;

import java.util.Map;

import com.channel.bank.adapter.pay.domain.AmqoRequrst;


public interface IAmqpService {

	/**
	 * 发送消息
	 * @return
	 */
	public boolean sendMessage(AmqoRequrst requrst);
}
