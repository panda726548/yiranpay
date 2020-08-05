package com.channel.bank.adapter.pay.test;

import java.text.ParseException;

import org.junit.Test;

/**
 * 退款查询
 * @author Administrator
 *
 */
public class PublicPayRefundQueryTest extends RefundQueryBaseTestCase {

	@Test
	public void refundQueryTest() throws ParseException{
		processRefundQuery("CHINAPAY70001", "WXPAY", "20200405", "3194RF20200312002149000025");
	}
}
