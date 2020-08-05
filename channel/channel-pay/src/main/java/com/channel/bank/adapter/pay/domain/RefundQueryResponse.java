package com.channel.bank.adapter.pay.domain;
/**
 * 退款查询响应参数
 * @author admin
 *
 */
public class RefundQueryResponse {
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
	 * 退款状态
	 */
	private String refundStatus;
	/**
	 * 退货订单号
	 */
	private String refundOrderId;
	/**
	 * 目标系统退货订单号
	 */
	private String refundTargetOrderId;
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
	 * 平台流水号，类似检索参考号
	 */
	private String seqId;
	/**
	 * 清分ID，如果来源方传了bankRefId就等于bankRefId，否则等于seqId
	 */
	private String settleRefId;
	/**
	 * 交易状态
	 */
	private String status;
	/**
	 * 支付总金额
	 */
	private String totalAmount;
	/**
	 * 商户名称
	 */
	private String merName;
	/**
	 * 商户订单号
	 */
	private String merOrderId;
	/**
	 * 第三方订单号
	 */
	private String targetOrderId;
	/**
	 * 目标平台代码
	 */
	private String targetSys;
	/**
	 * 目标平台的状态
	 */
	private String targetStatus;
	/**
	 * 支付渠道商户号，各渠道情况不同，酌情转换。
	 */
	private String targetMid;
	
	/**
	 * bankCardNo	银行卡号，如果有的话
	 */
	private String bankCardNo;
	/**
	 * 银行信息
	 */
	private String bankInfo;
	/**
	 * 支付渠道列表 示例：支付宝余额:33|优惠券:55
	 */
	private String refundFunds;
	/**
	 * 支付渠道描述
	 */
	private String refundFundsDesc;
	/**
	 * 支付时间，格式yyyy-MM-dd HH:mm:ss
	 */
	private String payTime;
	/**
	 * 结算日期，格式yyyy-MM-dd
	 */
	private String settleDate;
	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 商户实退金额
	 */
	private String sendBackAmount;
	/**
	 * 营销联盟优惠金额
	 */
	private String yxlmAmount;
	/**
	 * 实付部分退款金额
	 */
	private String refundInvoiceAmount;
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
	public String getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
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
	public String getSeqId() {
		return seqId;
	}
	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}
	public String getSettleRefId() {
		return settleRefId;
	}
	public void setSettleRefId(String settleRefId) {
		this.settleRefId = settleRefId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getMerOrderId() {
		return merOrderId;
	}
	public void setMerOrderId(String merOrderId) {
		this.merOrderId = merOrderId;
	}
	public String getTargetOrderId() {
		return targetOrderId;
	}
	public void setTargetOrderId(String targetOrderId) {
		this.targetOrderId = targetOrderId;
	}
	public String getTargetSys() {
		return targetSys;
	}
	public void setTargetSys(String targetSys) {
		this.targetSys = targetSys;
	}
	public String getTargetStatus() {
		return targetStatus;
	}
	public void setTargetStatus(String targetStatus) {
		this.targetStatus = targetStatus;
	}
	public String getTargetMid() {
		return targetMid;
	}
	public void setTargetMid(String targetMid) {
		this.targetMid = targetMid;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getBankInfo() {
		return bankInfo;
	}
	public void setBankInfo(String bankInfo) {
		this.bankInfo = bankInfo;
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
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSendBackAmount() {
		return sendBackAmount;
	}
	public void setSendBackAmount(String sendBackAmount) {
		this.sendBackAmount = sendBackAmount;
	}
	public String getYxlmAmount() {
		return yxlmAmount;
	}
	public void setYxlmAmount(String yxlmAmount) {
		this.yxlmAmount = yxlmAmount;
	}
	public String getRefundInvoiceAmount() {
		return refundInvoiceAmount;
	}
	public void setRefundInvoiceAmount(String refundInvoiceAmount) {
		this.refundInvoiceAmount = refundInvoiceAmount;
	}
	
	
	
}
