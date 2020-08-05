package com.yiranpay.gateway.service;

import java.util.Map;

import com.yiranpay.gateway.response.WeiXinPayResponse;


public interface IWeiXinPayService {

	public WeiXinPayResponse process(Map<String, String> paraMap) throws Exception;
	
	//public ChinaH5PayQueryResponse queryPayprocess(Map<String, String> paramMap) throws Exception;
}
