package com.yiran.project.merchant.order.domain;

import java.util.Date;

import com.yiran.framework.aspectj.lang.annotation.Excel;

public class Accountflow {

	@Excel(name = "商户号")
    private String memberId;
	
	/** 支付流水号 */
    @Excel(name = "支付流水号")
    private String paymentSeqNo;
    
    /** 金额 */
    @Excel(name = "金额")
    private Double amount;
    
    /** 机构订单号 */
    @Excel(name = "渠道订单号")
    private String bizNo;
    
    /** 银行编码 */
    @Excel(name = "银行编码")
    private String bankCode;
    
    @Excel(name = "业务类型，I:充值，O:提现，B:退款")
    private String bizType;
    
    @Excel(name = "交易时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

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

	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	@Override
	public String toString() {
		return "Accountflow [memberId=" + memberId + ", paymentSeqNo=" + paymentSeqNo + ", amount=" + amount
				+ ", bizNo=" + bizNo + ", bankCode=" + bankCode + ", bizType=" + bizType + ", gmtCreate=" + gmtCreate
				+ "]";
	}
    
    
	
}
