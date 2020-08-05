package com.channel.bank.adapter.pay.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.channel.bank.adapter.pay.domain.ChannelFundResult;
import com.channel.bank.adapter.pay.domain.EBankChannelFundRequest;
import com.channel.bank.adapter.pay.enums.AccessChannel;
import com.channel.bank.adapter.pay.enums.FundChannelApiType;


/**
 * 
 * 
 * @author Administrator
 *
 */
public class ALIPayFunInTest extends BaseJunit {
	/**
	 * 
	 */
	@Test
	public void FundInTest() {
		EBankChannelFundRequest req = new EBankChannelFundRequest();

		req.setAccessChannel(AccessChannel.WEB);

		req.setApiType(FundChannelApiType.SIGN);
		req.setApiUrl("");
		req.setCallbackPageUrl("http://api.yiranpay.xyz/api/yiran/channelpay/alipay/notify/ALIPAY10101");
		req.setCallbackServerUrl("http://api.yiranpay.xyz/api/yiran/channelpay/alipay/notify/ALIPAY10101");
		req.setFundChannelCode("CHINAPAY70001");

		req.setInstCode("ALIPAY");
		req.setInstOrderNo("ALIPAY20200307046123");
		req.setOrderDate(new Date());
		req.setUserIp("180.169.68.170");	
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberId", "1000011350181");
		map.put("subject", "测试支付");
		req.setInstOrderSubmitTime(new Date());
		req.setAmount(new BigDecimal("0.01"));
		req.setTargetInstCode("CITIC");
		map.put("accountName", "许盼");
		map.put("idNo", "42082119881026253X");
		map.put("mobileNo", "13477353282");
		map.put("cardNo", "6217710808791636");
		req.setExtension(map);
		String dd = JSON.toJSONString(req,SerializerFeature.UseISO8601DateFormat);
		System.out.println(dd);

	}

}
