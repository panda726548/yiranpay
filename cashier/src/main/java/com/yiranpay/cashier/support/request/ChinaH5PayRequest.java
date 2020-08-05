package com.yiranpay.cashier.support.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 请求响应
 * 
 * @author pandaa
 *
 */
public class ChinaH5PayRequest extends RequestBase {

	/**
	 * 商户请求号:商户发起请求的,建议用时间戳唯一编号
	 */
	private String requestNo;
	/**
	 * 交易金额
	 */
	private String amount;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 商品描述
	 */
	private String productDesc;
	/**
	 * 产品编码
	 */
	private String productCode;
	/**
	 * 商户订单号:商户生成的唯一订单号，最长50位
	 */
	private String orderId;
	/**
	 * 会员ID:会员在商户的唯一编号
	 */
	private String userId;
	/**
	 * 机构编码
	 */
	private String instcode;
	/**
	 * 主动通知商户网站里指定的页面http路径。
	 */
	private String notifyUrl;
	/**
	 * 支付编码
	 */
	private String paymentCode;
	public String getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getInstcode() {
		return instcode;
	}
	public void setInstcode(String instcode) {
		this.instcode = instcode;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getPaymentCode() {
		return paymentCode;
	}
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
	
	

}
