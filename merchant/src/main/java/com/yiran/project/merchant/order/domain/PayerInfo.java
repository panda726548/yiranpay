package com.yiran.project.merchant.order.domain;
/**
 * 付款方信息
 * @author pandaa
 *
 */
public class PayerInfo {
	/**
	 * 微信openId
	 */
	private String openId;
	/**
	 * 订单名称
	 */
	private String orderName;
	/**
	 * 账户名
	 */
	private String accountName;
	/**
	 * 手机号
	 */
	private String mobileNo;
	/**
	 * 身份证
	 */
	private String idNo;
	/**
	 * 卡号
	 */
	private String cardNo;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	@Override
	public String toString() {
		return "PayerInfo [openId=" + openId + ", orderName=" + orderName + ", accountName=" + accountName
				+ ", mobileNo=" + mobileNo + ", idNo=" + idNo + ", cardNo=" + cardNo + "]";
	}
	
	

}
