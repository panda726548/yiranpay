package com.channel.bank.adapter.pay.domain;
/**
 * 支付结果查询接口响应参数
 * @author admin
 *
 */
public class PayQueryResponse {
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
	 * 业务类型   YUEDANDEFAULT
	 */
	private String instMid;
	/**
	 * 附加数据
	 */
	private String attachedData;
	/**
	 * 平台流水号，类似检索参考号
	 */
	private String seqId;
	/**
	 * 清分ID，如果来源方传了bankRefId就等于bankRefId，否则等于seqId
	 */
	private String settleRefId;
	/**
	 * 检索参考号，用在银联体系交易中
	 */
	private String refId;
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
	 * 买家ID
	 */
	private String buyerId;
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
	private String billFunds;
	/**
	 * 支付渠道描述
	 */
	private String billFundsDesc;
	/**
	 * 买家付款的金额，支付宝会有
	 */
	private String buyerPayAmount;
	/**
	 * 买家用户名
	 */
	private String buyerUsername;
	/**
	 * 网付计算的优惠金额
	 */
	private String couponAmount;
	/**
	 * 交易中可给用户开具发票的金额
	 */
	private String invoiceAmount;
	/**
	 * 支付时间，格式yyyy-MM-dd HH:mm:ss
	 */
	private String payTime;
	/**
	 * 商户实收金额，支付宝会有
	 */
	private String receiptAmount;
	/**
	 * 结算日期，格式yyyy-MM-dd
	 */
	private String settleDate;
	/**
	 * 子买家ID，比如微信的subOpenId
	 */
	private String subBuyerId;
	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 微信活动ID
	 */
	private String activityIds;
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
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
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
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
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
	public String getBillFunds() {
		return billFunds;
	}
	public void setBillFunds(String billFunds) {
		this.billFunds = billFunds;
	}
	public String getBillFundsDesc() {
		return billFundsDesc;
	}
	public void setBillFundsDesc(String billFundsDesc) {
		this.billFundsDesc = billFundsDesc;
	}
	public String getBuyerPayAmount() {
		return buyerPayAmount;
	}
	public void setBuyerPayAmount(String buyerPayAmount) {
		this.buyerPayAmount = buyerPayAmount;
	}
	public String getBuyerUsername() {
		return buyerUsername;
	}
	public void setBuyerUsername(String buyerUsername) {
		this.buyerUsername = buyerUsername;
	}
	public String getCouponAmount() {
		return couponAmount;
	}
	public void setCouponAmount(String couponAmount) {
		this.couponAmount = couponAmount;
	}
	public String getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getReceiptAmount() {
		return receiptAmount;
	}
	public void setReceiptAmount(String receiptAmount) {
		this.receiptAmount = receiptAmount;
	}
	public String getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	public String getSubBuyerId() {
		return subBuyerId;
	}
	public void setSubBuyerId(String subBuyerId) {
		this.subBuyerId = subBuyerId;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getActivityIds() {
		return activityIds;
	}
	public void setActivityIds(String activityIds) {
		this.activityIds = activityIds;
	}
	public String getYxlmAmount() {
		return yxlmAmount;
	}
	public void setYxlmAmount(String yxlmAmount) {
		this.yxlmAmount = yxlmAmount;
	}
	@Override
	public String toString() {
		return "PayQueryResponse [errCode=" + errCode + ", errMsg=" + errMsg
				+ ", msgId=" + msgId + ", msgType=" + msgType + ", msgSrc="
				+ msgSrc + ", srcReserve=" + srcReserve
				+ ", responseTimestamp=" + responseTimestamp + ", mid=" + mid
				+ ", tid=" + tid + ", instMid=" + instMid + ", attachedData="
				+ attachedData + ", seqId=" + seqId + ", settleRefId="
				+ settleRefId + ", refId=" + refId + ", status=" + status
				+ ", totalAmount=" + totalAmount + ", merName=" + merName
				+ ", merOrderId=" + merOrderId + ", targetOrderId="
				+ targetOrderId + ", targetSys=" + targetSys
				+ ", targetStatus=" + targetStatus + ", buyerId=" + buyerId
				+ ", targetMid=" + targetMid + ", bankCardNo=" + bankCardNo
				+ ", bankInfo=" + bankInfo + ", billFunds=" + billFunds
				+ ", billFundsDesc=" + billFundsDesc + ", buyerPayAmount="
				+ buyerPayAmount + ", buyerUsername=" + buyerUsername
				+ ", couponAmount=" + couponAmount + ", invoiceAmount="
				+ invoiceAmount + ", payTime=" + payTime + ", receiptAmount="
				+ receiptAmount + ", settleDate=" + settleDate
				+ ", subBuyerId=" + subBuyerId + ", sign=" + sign
				+ ", activityIds=" + activityIds + ", yxlmAmount=" + yxlmAmount
				+ "]";
	}
	
	
	
}
