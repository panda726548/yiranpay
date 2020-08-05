package com.channel.bank.adapter.pay.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.channel.bank.adapter.pay.domain.QueryRequest;
import com.channel.bank.adapter.pay.enums.FundChannelApiType;

public class RefundQueryBaseTestCase extends BaseJunit {
	
	public void processRefundQuery(String fccode,String instcode,String date,String orderNo) throws ParseException {
		String querySeqNo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		QueryRequest request = new QueryRequest();
		request.setFundChannelCode(fccode);
		request.setInstOrderNo(querySeqNo);
		request.setOriginalInstOrderNo(orderNo);
		request.setApiType(FundChannelApiType.SINGLE_REFUND_QUERY);
		Date orderDate = new SimpleDateFormat("yyyyMMdd").parse(date);
		if (orderDate != null) {
			request.setOriginalInstOrderSubmitTime(orderDate);
		}
		String dd = JSON.toJSONString(request,
				SerializerFeature.UseISO8601DateFormat);
		System.out.println(dd);

	}


    
}
