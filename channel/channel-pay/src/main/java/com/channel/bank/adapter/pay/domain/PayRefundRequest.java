package com.channel.bank.adapter.pay.domain;
/**
 * 退款请求
 * @author admin
 *
 */
public class PayRefundRequest {
	/**
	 * 消息ID
	 */
	private String msgId;
	/**
	 * 消息来源  必填
	 */
	private String msgSrc;
	/**
	 * 消息类型  必填  query
	 */
	private String msgType;
	/**
	 * 报文请求时间，格式yyyy-MM-dd HH:mm:ss
	 */
	private String requestTimestamp;
	/**
	 * 请求系统预留字段
	 */
	private String srcReserve;
	/**
	 * 商户订单号 原交易订单号
	 */
	private String merOrderId;
	
	/**
	 * 业务类型   YUEDANDEFAULT
	 */
	private String instMid;
	
	/**
	 * 商户号
	 */
	private String mid;
	/**
	 * 终端号  必填
	 */
	private String tid;
	/**
	 * 要退货的金额  若下单接口中上送了分钟标记字段divisionFlag，则该字段refundAmount等于platformAmount  加上subOrders中totalAmount。
		全额退款时refundAmount等于支付总金额totalAmount
	 */
	private String refundAmount;
	/**
	 * 针对平台商户分账金额的退款金额
	 */
	private String platformAmount;
	/**
	 * 退货说明 
	 */
	private String refundDesc;
	/**
	 * 退货交易的订单号，如不指定，则系统自动生成。如商户指定，须以4位来源编号（由银商分配）开头。
	 */
	private String refundOrderId;
	/**
	 * 签名算法  值为：MD5或 SHA256；若不上送默认为MD5
	 */
	private String signType;
	/**
	 * 签名
	 */
	private String sign;
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
	public String getSrcReserve() {
		return srcReserve;
	}
	public void setSrcReserve(String srcReserve) {
		this.srcReserve = srcReserve;
	}
	public String getMerOrderId() {
		return merOrderId;
	}
	public void setMerOrderId(String merOrderId) {
		this.merOrderId = merOrderId;
	}
	public String getInstMid() {
		return instMid;
	}
	public void setInstMid(String instMid) {
		this.instMid = instMid;
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
	public String getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}
	public String getPlatformAmount() {
		return platformAmount;
	}
	public void setPlatformAmount(String platformAmount) {
		this.platformAmount = platformAmount;
	}
	public String getRefundDesc() {
		return refundDesc;
	}
	public void setRefundDesc(String refundDesc) {
		this.refundDesc = refundDesc;
	}
	public String getRefundOrderId() {
		return refundOrderId;
	}
	public void setRefundOrderId(String refundOrderId) {
		this.refundOrderId = refundOrderId;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	@Override
	public String toString() {
		return "PayRefundRequest [msgId=" + msgId + ", msgSrc=" + msgSrc
				+ ", msgType=" + msgType + ", requestTimestamp="
				+ requestTimestamp + ", srcReserve=" + srcReserve
				+ ", merOrderId=" + merOrderId + ", instMid=" + instMid
				+ ", mid=" + mid + ", tid=" + tid + ", refundAmount="
				+ refundAmount + ", platformAmount=" + platformAmount
				+ ", refundDesc=" + refundDesc + ", refundOrderId="
				+ refundOrderId + ", signType=" + signType + ", sign=" + sign
				+ "]";
	}
	
	
}
