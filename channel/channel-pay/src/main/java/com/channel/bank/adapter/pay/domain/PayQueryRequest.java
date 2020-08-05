package com.channel.bank.adapter.pay.domain;
/**
 * 支付结果查询接口请求参数
 * @author admin
 *
 */
public class PayQueryRequest {
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
	 * 商户号
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
	 * 商户订单号    必填
	 */
	private String merOrderId;
	
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
	public String getMerOrderId() {
		return merOrderId;
	}
	public void setMerOrderId(String merOrderId) {
		this.merOrderId = merOrderId;
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
	
	

}
