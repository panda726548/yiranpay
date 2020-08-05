package com.yiranpay.gateway.service;

import java.util.Map;

import com.yiranpay.gateway.response.UESResponse;



public interface IEncryptionDecryptionService {

	public UESResponse process(Map<String, String> paraMap) throws Exception;
}
