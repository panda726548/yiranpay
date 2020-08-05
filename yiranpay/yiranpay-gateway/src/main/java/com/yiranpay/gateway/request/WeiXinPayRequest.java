package com.yiranpay.gateway.request;

/**
 * 请求响应
 * 
 * @author pandaa
 *
 */
public class WeiXinPayRequest extends RequestBase {

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
	/**
	 * 微信openid(H5支付和JSAPI支付必填)
	 */
	private String openId;
	/**
	 * 微信公众账号支付:wxpay_h5,微信Native支付:NATIVE
	 */
	private String payType;
	
	private String channelName;
	
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
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
		return "WeiXinPayRequest [requestNo=" + requestNo + ", amount=" + amount + ", productName=" + productName
				+ ", productDesc=" + productDesc + ", productCode=" + productCode + ", orderId=" + orderId + ", userId="
				+ userId + ", instcode=" + instcode + ", notifyUrl=" + notifyUrl + ", paymentCode=" + paymentCode
				+ ", openId=" + openId + ", payType=" + payType + ", channelName=" + channelName + "]";
	}
	
	
	

}
