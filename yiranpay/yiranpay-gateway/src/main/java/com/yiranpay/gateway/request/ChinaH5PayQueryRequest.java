package com.yiranpay.gateway.request;

/**
 * 请求响应
 * 
 * @author pandaa
 *
 */
public class ChinaH5PayQueryRequest extends RequestBase {

	/**
	 * 商户支付请求号,商户网站唯一
	 */
	private String requestNo;
	
	public String getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	
	

}
