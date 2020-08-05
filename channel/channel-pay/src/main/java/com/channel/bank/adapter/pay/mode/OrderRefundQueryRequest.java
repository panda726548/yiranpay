package com.channel.bank.adapter.pay.mode;



/**
 * 退款查询
 */
public class OrderRefundQueryRequest {

  

	/**
	 * 商户订单号
	 */
    private String orderId;


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
    
}
