package com.channel.bank.adapter.pay.domain;
/**
 * 退款响应
 * @author admin
 *
 */
public class PayRefundResponse {

	/**
	 * 平台错误码  true
	 */
	private String errCode;
	/**
	 * 平台错误信息
	 */
	private String errMsg;
	/**
	 * 消息ID
	 */
	private String msgId;
	/**
	 * 消息类型
	 */
	private String msgType;
	/**
	 * 消息来源
	 */
	private String msgSrc;
	/**
	 * 请求系统预留字段
	 */
	private String srcReserve;
	/**
	 * 报文响应时间，格式yyyy-MM-dd HH:mm:ss
	 */
	private String responseTimestamp;
	
	/**
	 * 商户号
	 */
	private String mid;
	/**
	 * 终端号  必填
	 */
	private String tid;
	/**
	 * 商户订单号
	 */
	private String merOrderId;
	/**
	 * 商户名称
	 */
	private String merName;
	/**
	 * 平台流水号
	 */
	private String seqId;
	/**
	 * 交易状态
	 */
	private String status;
	/**
	 * 支付渠道商户号
	 */
	private String targetMid;
	/**
	 * 第三方订单号，退货交易时不返回
	 */
	private String targetOrderId;
	/**
	 * 目标平台的状态
	 */
	private String targetStatus;
	/**
	 * 目标平台代码
	 */
	private String targetSys;
	/**
	 * 支付总金额
	 */
	private String totalAmount;
	/**
	 * 退款渠道列表
	 */
	private String refundFunds;
	/**
	 * 退款渠道描述
	 */
	private String refundFundsDesc;
	/**
	 * 实付部分退款金额
	 */
	private String refundInvoiceAmount;
	/**
	 * 退货订单号
	 */
	private String refundOrderId;
	/**
	 * 目标系统退货订单号
	 */
	private String refundTargetOrderId;
	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 退款状态
	 */
	private String refundStatus;
	/**
	 * 退款总金额
	 */
	private String refundAmount;
	/**
	 * 营销联盟优惠金额
	 */
	private String yxlmAmount;
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgSrc() {
		return msgSrc;
	}
	public void setMsgSrc(String msgSrc) {
		this.msgSrc = msgSrc;
	}
	public String getSrcReserve() {
		return srcReserve;
	}
	public void setSrcReserve(String srcReserve) {
		this.srcReserve = srcReserve;
	}
	public String getResponseTimestamp() {
		return responseTimestamp;
	}
	public void setResponseTimestamp(String responseTimestamp) {
		this.responseTimestamp = responseTimestamp;
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
	public String getMerOrderId() {
		return merOrderId;
	}
	public void setMerOrderId(String merOrderId) {
		this.merOrderId = merOrderId;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getSeqId() {
		return seqId;
	}
	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTargetMid() {
		return targetMid;
	}
	public void setTargetMid(String targetMid) {
		this.targetMid = targetMid;
	}
	public String getTargetOrderId() {
		return targetOrderId;
	}
	public void setTargetOrderId(String targetOrderId) {
		this.targetOrderId = targetOrderId;
	}
	public String getTargetStatus() {
		return targetStatus;
	}
	public void setTargetStatus(String targetStatus) {
		this.targetStatus = targetStatus;
	}
	public String getTargetSys() {
		return targetSys;
	}
	public void setTargetSys(String targetSys) {
		this.targetSys = targetSys;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getRefundFunds() {
		return refundFunds;
	}
	public void setRefundFunds(String refundFunds) {
		this.refundFunds = refundFunds;
	}
	public String getRefundFundsDesc() {
		return refundFundsDesc;
	}
	public void setRefundFundsDesc(String refundFundsDesc) {
		this.refundFundsDesc = refundFundsDesc;
	}
	public String getRefundInvoiceAmount() {
		return refundInvoiceAmount;
	}
	public void setRefundInvoiceAmount(String refundInvoiceAmount) {
		this.refundInvoiceAmount = refundInvoiceAmount;
	}
	public String getRefundOrderId() {
		return refundOrderId;
	}
	public void setRefundOrderId(String refundOrderId) {
		this.refundOrderId = refundOrderId;
	}
	public String getRefundTargetOrderId() {
		return refundTargetOrderId;
	}
	public void setRefundTargetOrderId(String refundTargetOrderId) {
		this.refundTargetOrderId = refundTargetOrderId;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
	public String getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}
	public String getYxlmAmount() {
		return yxlmAmount;
	}
	public void setYxlmAmount(String yxlmAmount) {
		this.yxlmAmount = yxlmAmount;
	}
	
}
