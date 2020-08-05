package com.channel.bank.adapter.pay.mode;

import com.channel.bank.adapter.pay.enums.BestPayTypeEnum;

/**
 * 支付订单查询
 */
public class OrderQueryRequest {

    /**
     * 支付方式.
     */
    private BestPayTypeEnum payTypeEnum;

    private String orderId;

	public BestPayTypeEnum getPayTypeEnum() {
		return payTypeEnum;
	}

	public void setPayTypeEnum(BestPayTypeEnum payTypeEnum) {
		this.payTypeEnum = payTypeEnum;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
    
}
