package com.yiranpay.gateway.service;

import java.util.Map;

import com.yiranpay.gateway.response.ALIPayResponse;


public interface IALIPayService {

	public ALIPayResponse process(Map<String, String> paraMap) throws Exception;
}
