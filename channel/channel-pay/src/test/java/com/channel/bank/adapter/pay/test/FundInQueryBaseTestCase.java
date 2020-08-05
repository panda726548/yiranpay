package com.channel.bank.adapter.pay.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.channel.bank.adapter.pay.domain.QueryRequest;
import com.channel.bank.adapter.pay.enums.FundChannelApiType;


public class FundInQueryBaseTestCase extends BaseJunit {


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
	
    /**
     * 单笔查询
     * @param fcCode
     * @param instCode
     * @param instOrderNo
     * @return
     */
    protected void QuerySingle(String fcCode, String instCode, String instOrderNo,
                                        Date orderDate) {

        String querySeqNo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        QuerySingle(fcCode, instCode, instOrderNo, orderDate, querySeqNo);
    }

    protected void QuerySingle(String fcCode, String instCode, String instOrderNo,
                                        Date orderDate, String querySeqNo) {

    	QueryRequest request = new QueryRequest();
		request.setFundChannelCode(fcCode);
		request.setInstOrderNo(instOrderNo);
		request.setQuerySeqNo(querySeqNo);
		request.setApiType(FundChannelApiType.SINGLE_QUERY);
		if (orderDate != null) {
			request.setInstOrderSubmitTime(orderDate);
		}
		String dd = JSON.toJSONString(request,
				SerializerFeature.UseISO8601DateFormat);
		
		System.out.println(dd);
    }

}
