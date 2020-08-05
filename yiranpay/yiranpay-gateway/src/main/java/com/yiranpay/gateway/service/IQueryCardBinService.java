package com.yiranpay.gateway.service;

import java.util.Map;

import com.yiranpay.gateway.response.CarBinResponse;


public interface IQueryCardBinService {

	public CarBinResponse process(Map<String, String> paraMap) throws Exception;
}
