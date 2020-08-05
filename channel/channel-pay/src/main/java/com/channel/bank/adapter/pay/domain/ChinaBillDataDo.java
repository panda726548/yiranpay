package com.channel.bank.adapter.pay.domain;
/**
 * 银联商务对账单
 * @author pandaa
 *
 */
public class ChinaBillDataDo {
	//清算日期
	private String settleDate;
	//交易日期 TRADE_DATE
	private String tradeDate;
	//交易时间 TRADE_TIME
	private String tradeTime;
	//交易金额 TRANSACTION_AMOUNT
	private String transactionAmount;
	//终端号 TERMINAL_NUMBER
	private String terminalNumber;
	//分店简称 BRANCH_STORE
	private String branchStore;
	//清算金额 SETTLE_AMOUNT
	private String settleAmount;
	//手续费 SERVICE_FEE
	private String serviceFee;
	//流水号 SERIAL_NUMBER
	private String serialNumber;
	//交易类型 TRANSACTION_TYPE
	private String transactionType;
	//参考号  REFERENCE_NUMBER
	private String referenceNumber;
	//卡号 CARD_NUMBER
	private String cardNumber;
	//商户号 MERCHANT_NUMBER
	private String merchantNumber;
	//商户名称  MERCHANT_NAME
	private String merchantName;
	//发卡行 ISSUING_BANK
	private String issuingBank;
	//卡类型  CARD_TYPE
	private String cardType;
	//商户订单号 ORDER_NUMBER
	private String orderNumber;
	//退货订单号 RETURN_ORDER_NO
	private String returnOrderNo;
	//支付类型  PAYMENT_TYPE
	private String paymentType;
	//实际支付金额  ACTUAL_PAYMENT_AMOUNT
	private String actualPaymentAmount;
	//备注 REMARK
	private String remark;
	//付款附言  PAYMENT_POSTSCRIPT
	private String paymentPostscript;
	//钱包优惠金额  WALLET_DISCOUNT_AMOUNT
	private String walletDiscountAmount;
	//商户优惠金额  MERCHANT_DISCOUNT_AMOUNT
	private String merchantDiscountAmount;
	//支付产品  PAYMENT_PRODUCTS
	private String paymentProducts;
	public String getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	public String getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTerminalNumber() {
		return terminalNumber;
	}
	public void setTerminalNumber(String terminalNumber) {
		this.terminalNumber = terminalNumber;
	}
	public String getBranchStore() {
		return branchStore;
	}
	public void setBranchStore(String branchStore) {
		this.branchStore = branchStore;
	}
	public String getSettleAmount() {
		return settleAmount;
	}
	public void setSettleAmount(String settleAmount) {
		this.settleAmount = settleAmount;
	}
	public String getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getMerchantNumber() {
		return merchantNumber;
	}
	public void setMerchantNumber(String merchantNumber) {
		this.merchantNumber = merchantNumber;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getIssuingBank() {
		return issuingBank;
	}
	public void setIssuingBank(String issuingBank) {
		this.issuingBank = issuingBank;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getReturnOrderNo() {
		return returnOrderNo;
	}
	public void setReturnOrderNo(String returnOrderNo) {
		this.returnOrderNo = returnOrderNo;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getActualPaymentAmount() {
		return actualPaymentAmount;
	}
	public void setActualPaymentAmount(String actualPaymentAmount) {
		this.actualPaymentAmount = actualPaymentAmount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPaymentPostscript() {
		return paymentPostscript;
	}
	public void setPaymentPostscript(String paymentPostscript) {
		this.paymentPostscript = paymentPostscript;
	}
	public String getWalletDiscountAmount() {
		return walletDiscountAmount;
	}
	public void setWalletDiscountAmount(String walletDiscountAmount) {
		this.walletDiscountAmount = walletDiscountAmount;
	}
	public String getMerchantDiscountAmount() {
		return merchantDiscountAmount;
	}
	public void setMerchantDiscountAmount(String merchantDiscountAmount) {
		this.merchantDiscountAmount = merchantDiscountAmount;
	}
	public String getPaymentProducts() {
		return paymentProducts;
	}
	public void setPaymentProducts(String paymentProducts) {
		this.paymentProducts = paymentProducts;
	}
	@Override
	public String toString() {
		return "ChinaBillDataDo [settleDate=" + settleDate + ", tradeDate=" + tradeDate + ", tradeTime=" + tradeTime
				+ ", transactionAmount=" + transactionAmount + ", terminalNumber=" + terminalNumber + ", branchStore="
				+ branchStore + ", settleAmount=" + settleAmount + ", serviceFee=" + serviceFee + ", serialNumber="
				+ serialNumber + ", transactionType=" + transactionType + ", referenceNumber=" + referenceNumber
				+ ", cardNumber=" + cardNumber + ", merchantNumber=" + merchantNumber + ", merchantName=" + merchantName
				+ ", issuingBank=" + issuingBank + ", cardType=" + cardType + ", orderNumber=" + orderNumber
				+ ", returnOrderNo=" + returnOrderNo + ", paymentType=" + paymentType + ", actualPaymentAmount="
				+ actualPaymentAmount + ", remark=" + remark + ", paymentPostscript=" + paymentPostscript
				+ ", walletDiscountAmount=" + walletDiscountAmount + ", merchantDiscountAmount="
				+ merchantDiscountAmount + ", paymentProducts=" + paymentProducts + "]";
	}
	
	
}
