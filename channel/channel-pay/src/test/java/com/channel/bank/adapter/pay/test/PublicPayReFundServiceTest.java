package com.channel.bank.adapter.pay.test;

import java.text.ParseException;

import org.junit.Test;

public class PublicPayReFundServiceTest extends RefundBaseTestCase {
	
	@Test
	public void processRefundTest(){
		
		try {
			processRefund("CHINAPAY70001", "WXPAY", "0.01", "3194ALIPAY20200307046", "3194RF20200312002149000025");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
