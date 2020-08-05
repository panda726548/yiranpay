package com.yiranpay.channel;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yiranpay.base.BaseJunit;
import com.yiranpay.paychannel.domain.TmFundChannel;
import com.yiranpay.paychannel.enums.PayMode;
import com.yiranpay.paychannel.enums.RequestType;
import com.yiranpay.paychannel.exception.RouteChannelException;
import com.yiranpay.paychannel.service.IFundChannelRouter;
/**
 * 路由测试
 * @author pandaa
 *
 */
public class RouterFundChannelTest extends BaseJunit{
	@Autowired
	private IFundChannelRouter fundChannelRouter;
	@Test
	public void routerTest(){
		
		try {
			Map<String,String> map = new HashMap<String,String>();
			map.put("partnerId", "20000126504");
			TmFundChannel routerFundChannel = fundChannelRouter.routerFundChannel("WXPAY", PayMode.QUICKPAY, RequestType.FUND_IN.getCode(), null);
			System.out.println(JSON.toJSONString(routerFundChannel));
		} catch (RouteChannelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
