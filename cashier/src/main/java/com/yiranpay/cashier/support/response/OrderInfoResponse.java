package com.yiranpay.cashier.support.response;

import java.io.Serializable;

public class OrderInfoResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 813879520159260844L;
	
	/** 支付流水号 */
	private String paymentSeqNo;
	
	/** 金额 */
	private Double amount;
	
	/** 业务发生时间 */
	private String bizDate;
	
	private String instCode;

	public String getPaymentSeqNo() {
		return paymentSeqNo;
	}

	public void setPaymentSeqNo(String paymentSeqNo) {
		this.paymentSeqNo = paymentSeqNo;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getBizDate() {
		return bizDate;
	}

	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}

	public String getInstCode() {
		return instCode;
	}

	public void setInstCode(String instCode) {
		this.instCode = instCode;
	}

	@Override
	public String toString() {
		return "OrderInfoResponse [paymentSeqNo=" + paymentSeqNo + ", amount=" + amount + ", bizDate=" + bizDate
				+ ", instCode=" + instCode + "]";
	}
	
	

}
