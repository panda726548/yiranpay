package com.channel.bank.adapter.pay.test;

import java.math.BigDecimal;
import java.text.ParseException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.channel.bank.adapter.pay.enums.FundChannelApiType;
import com.channel.bank.adapter.pay.mode.RefundRequest;


public class RefundBaseTestCase extends BaseJunit {
	
	public void processRefund(String fccode,String instCode,String amount1,String OrignalInstOrderNo,String InstOrderNo) throws ParseException {
		RefundRequest refundrequest = new RefundRequest();
		refundrequest.setApiType(FundChannelApiType.SINGLE_REFUND);
		refundrequest.setFundChannelCode(fccode);
		refundrequest.setInstCode(instCode);
		refundrequest.setAmount(new BigDecimal(amount1));
		refundrequest.setOrignalInstOrderNo(OrignalInstOrderNo);
		//退款订单号
		refundrequest.setInstOrderNo(InstOrderNo);
		
		String dd = JSON.toJSONString(refundrequest,
				SerializerFeature.UseISO8601DateFormat);
		
		System.out.println(dd);
	}

}
