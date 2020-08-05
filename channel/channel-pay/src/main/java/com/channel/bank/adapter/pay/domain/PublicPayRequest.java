package com.channel.bank.adapter.pay.domain;
/**
 * 3.6	下单请求
 * @author pandaa
 *
 */
public class PublicPayRequest {
	/**
	 * 消息ID，原样返回
	 */
	private String msgId;
	/**
	 * 消息来源  必填
	 */
	private String msgSrc;
	/**
	 * 消息类型     WXPay.jsPay :微信公众号支,trade.jsPay :支付宝 ,acp.jsPay :银联云闪付
	 */
	private String msgType;
	/**
	 * 报文请求时间，格式yyyy-MM-dd HH:mm:ss   必填
	 */
	private String requestTimestamp;
	/**
	 * 订单过期时间   订单过期时间，为空则使用系统默认过期时间（30分钟），格式yyyy-MM-dd HH:mm:ss
	 * 微信、支付宝及云闪付：若自行上送，建议过期时间上送为下单请求时间之后的1小时内
	 * qmf.webPay :无卡暂不支持上送expireTime
	 */
	private String expireTime;
	/**
	 * 商户订单号  商户自行生成  注意：长度不要超过32位   必填
	 */
	private String merOrderId;
	/**
	 * 请求系统预留字段  
	 */
	private String srcReserve;
	/**
	 * 商户号 必填
	 */
	private String mid;
	/**
	 * 终端号  必填
	 */
	private String tid;
	/**
	 * 业务类型   YUEDANDEFAULT
	 */
	private String instMid;
	/**
	 * 商户附加数据
	 */
	private String attachedData;
	/**
	 * 账单描述 不超过255个字节（微信支付：上送值不大于128个字节，超过128字节后的部分会被截掉）
	 */
	private String orderDesc;
	/**
	 * 商品标记，用于优惠活动
	 */
	private String goodsTag;
	/**
	 * 订单原始金额，单位分，用于记录前端系统打折前的金额
	 */
	private String originalAmount;
	/**
	 * 支付总金额，单位分  必填
	 */
	private String totalAmount;
	/**
	 * 支付结果通知地址
	 */
	private String notifyUrl;
	/**
	 * 网页跳转地址
	 */
	private String returnUrl;
	/**
	 * 系统ID
	 */
	private String systemId;
	/**
	 * 签名算法  值为：MD5或 SHA256；若不上送默认为MD5
	 */
	private String signType;
	/**
	 * 重要)商户自己公众号appid下的用户openid，可以通过微信oauth接口获取。
		用户子标识
	 */
	private String subOpenId;
	/**
	 * 微信子商户appId
	 */
	private String subAppId;
	/**
	 * 实名认证姓名    Base64编码
	 */
	private String name;
	/**
	 * 实名认证手机号   Base64编码
	 */
	private String mobile;
	/**
	 * 实名认证证件类型   证件类型，微信支持身份证、支付宝支持身份证：IDENTITY_CARD、护照：PASSPORT、军官证：OFFICER_CARD、士兵证：SOLDIER_CARD、户口本：HOKOU
	 */
	private String certType;
	/**
	 * 实名认证证件号  Base64编码
	 */
	private String certNo;
	/**
	 * 是否需要实名认证    需要实名认证时置为 T
	 */
	private String fixBuyer;
	/**
	 * 是否需要限制信用卡支付   取值：true或false，默认false
	 */
	private String limitCreditCard;

	/**
	 * 标识是否是担保交易   取值：true或false，默认false
		若上送为true，则交易的金额将会被暂缓结算。调用担保完成接口后，完成部分金额会在t+1日结算给商户，剩余部分金额退还用户。调用担保撤销接口，则全部资金退还给用户。
		30天后 没有主动调用担保完成 且 没有主动调用担保撤销的交易 将会自动按撤销处理。
	 */
	private String secureTransaction;
	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 花呗分期数  仅支持3、6、12仅支持支付宝支付
	 */
	private String installmentNumber;
	
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getMsgSrc() {
		return msgSrc;
	}
	public void setMsgSrc(String msgSrc) {
		this.msgSrc = msgSrc;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getRequestTimestamp() {
		return requestTimestamp;
	}
	public void setRequestTimestamp(String requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public String getMerOrderId() {
		return merOrderId;
	}
	public void setMerOrderId(String merOrderId) {
		this.merOrderId = merOrderId;
	}
	public String getSrcReserve() {
		return srcReserve;
	}
	public void setSrcReserve(String srcReserve) {
		this.srcReserve = srcReserve;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getInstMid() {
		return instMid;
	}
	public void setInstMid(String instMid) {
		this.instMid = instMid;
	}
	public String getAttachedData() {
		return attachedData;
	}
	public void setAttachedData(String attachedData) {
		this.attachedData = attachedData;
	}
	public String getOrderDesc() {
		return orderDesc;
	}
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}
	public String getGoodsTag() {
		return goodsTag;
	}
	public void setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
	}
	public String getOriginalAmount() {
		return originalAmount;
	}
	public void setOriginalAmount(String originalAmount) {
		this.originalAmount = originalAmount;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getSubOpenId() {
		return subOpenId;
	}
	public void setSubOpenId(String subOpenId) {
		this.subOpenId = subOpenId;
	}
	public String getSubAppId() {
		return subAppId;
	}
	public void setSubAppId(String subAppId) {
		this.subAppId = subAppId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCertType() {
		return certType;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getFixBuyer() {
		return fixBuyer;
	}
	public void setFixBuyer(String fixBuyer) {
		this.fixBuyer = fixBuyer;
	}
	public String getLimitCreditCard() {
		return limitCreditCard;
	}
	public void setLimitCreditCard(String limitCreditCard) {
		this.limitCreditCard = limitCreditCard;
	}
	public String getSecureTransaction() {
		return secureTransaction;
	}
	public void setSecureTransaction(String secureTransaction) {
		this.secureTransaction = secureTransaction;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getInstallmentNumber() {
		return installmentNumber;
	}
	public void setInstallmentNumber(String installmentNumber) {
		this.installmentNumber = installmentNumber;
	}
	@Override
	public String toString() {
		return "PublicPayRequest [msgId=" + msgId + ", msgSrc=" + msgSrc + ", msgType=" + msgType
				+ ", requestTimestamp=" + requestTimestamp + ", expireTime=" + expireTime + ", merOrderId=" + merOrderId
				+ ", srcReserve=" + srcReserve + ", mid=" + mid + ", tid=" + tid + ", instMid=" + instMid
				+ ", attachedData=" + attachedData + ", orderDesc=" + orderDesc + ", goodsTag=" + goodsTag
				+ ", originalAmount=" + originalAmount + ", totalAmount=" + totalAmount + ", notifyUrl=" + notifyUrl
				+ ", returnUrl=" + returnUrl + ", systemId=" + systemId + ", signType=" + signType + ", subOpenId="
				+ subOpenId + ", subAppId=" + subAppId + ", name=" + name + ", mobile=" + mobile + ", certType="
				+ certType + ", certNo=" + certNo + ", fixBuyer=" + fixBuyer + ", limitCreditCard=" + limitCreditCard
				+ ", secureTransaction=" + secureTransaction + ", sign=" + sign + ", installmentNumber="
				+ installmentNumber + "]";
	}
	
	
	
}
