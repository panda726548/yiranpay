package com.channel.bank.adapter.pay.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * 发送请求信息
 * @author pandaa
 *
 */
public class AmqoRequrst implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9006675973480719589L;
	/**
	 * 交换器
	 */
	private String exchange; 
	/**
	 * 路由key
	 */
	private String routingKey;
	/**
	 * 发送数据
	 */
	private Map<String,Object> map;
	/**
	 * 队列名称
	 */
	private String queueName;
	
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public String getRoutingKey() {
		return routingKey;
	}
	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	@Override
	public String toString() {
		return "AmqoRequrst [exchange=" + exchange + ", routingKey=" + routingKey + ", map=" + map + ", queueName="
				+ queueName + "]";
	}
	
	
	
}
