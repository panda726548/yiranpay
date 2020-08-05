package com.yiranpay.cashier.support.response;

public class ChinaH5PayQueryResponse extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7729157963540164948L;
	/**
	 * 商户网站唯一订单号
	 */
	private String outerTradeNo;
	/**
	 * 聚合支付交易号
	 */
	private String innerTradeNo;
	/**
	 * 支付状态
	 */
	private String payStatus;
	
	private String errorMessage;
	
	private String errorCode;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getOuterTradeNo() {
		return outerTradeNo;
	}
	public void setOuterTradeNo(String outerTradeNo) {
		this.outerTradeNo = outerTradeNo;
	}
	public String getInnerTradeNo() {
		return innerTradeNo;
	}
	public void setInnerTradeNo(String innerTradeNo) {
		this.innerTradeNo = innerTradeNo;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	
}
