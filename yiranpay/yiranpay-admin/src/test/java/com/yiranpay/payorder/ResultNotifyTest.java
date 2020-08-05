package com.yiranpay.payorder;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yiranpay.base.BaseJunit;
import com.yiranpay.paychannel.enums.CurrencyType;
import com.yiranpay.paychannel.enums.InstOrderStatus;
import com.yiranpay.payorder.domain.PayInstOrderResult;
import com.yiranpay.payorder.domain.ReturnInfo;
import com.yiranpay.payorder.enums.InstOrderGlideStatus;
import com.yiranpay.payorder.enums.InstOrderProcessStatus;
import com.yiranpay.payorder.enums.InstOrderResultStatus;
import com.yiranpay.payorder.facade.IResultNotifyFacade;
import com.yiranpay.payorder.notify.OrderResult;

public class ResultNotifyTest  extends BaseJunit{
	
	@Autowired
	private IResultNotifyFacade resultNotifyFacade;
	
	@Test
    public void notifyCMF() {
        //1. 发请求到CMF, 模拟网银发送请求
        ReturnInfo result = resultNotifyFacade.notify(getNotifyRequest("DBLLPAY20190818004901002046"));
        System.out.println("结果返回：" + JSON.toJSONString(result));

    }

    @SuppressWarnings("deprecation")
    protected OrderResult getNotifyRequest(String instOrderNo) {
        OrderResult request = new OrderResult();
        PayInstOrderResult instOrderResult = new PayInstOrderResult();
        instOrderResult.setRealCurrency(CurrencyType.CNY);
        instOrderResult.setRealAmount(new BigDecimal("1000").setScale(2, BigDecimal.ROUND_DOWN));
        instOrderResult.setInstOrderNo(instOrderNo);
        instOrderResult.setStatus(InstOrderResultStatus.SUCCESSFUL);
        instOrderResult.setProcessStatus(InstOrderProcessStatus.SUCCESS);
        instOrderResult.setGlideStatus(InstOrderGlideStatus.NONEED);
        request.setOrderResult(instOrderResult);
        return request;
    }

}
