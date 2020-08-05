package com.yiranpay.gateway.service;

import java.util.Map;

import com.yiranpay.gateway.response.ChinaH5PayQueryResponse;
import com.yiranpay.gateway.response.ChinaH5PayResponse;

public interface IH5PayService {

	public ChinaH5PayResponse process(Map<String, String> paraMap) throws Exception;
	
	public ChinaH5PayQueryResponse queryPayprocess(Map<String, String> paramMap) throws Exception;
}
