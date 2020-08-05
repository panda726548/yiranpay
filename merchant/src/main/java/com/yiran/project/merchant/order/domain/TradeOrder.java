package com.yiran.project.merchant.order.domain;

import java.io.Serializable;
import java.util.Map;

import com.yiran.framework.web.domain.BaseEntity;
/**
 * 交易订单
 * @author pandaa
 *
 */
public class TradeOrder extends BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6737713006828677253L;
	/**
	 * 会员ID
	 */
	private String memberId;
	/**
	 * 业务订单
	 */
	private String businessOrderNO;
	/**
	 * 交易订单
	 */
	private String tradeOrderNo;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 交易类型
	 */
	private String tradeType;
	/**
	 * 支付方式
	 */
	private String payMode;
	/**
	 * 金额
	 */
	private String amount;
	/**
	 * 服务费
	 */
	private String serviceFee ="0.00";
	/**
	 * 结算金额
	 */
	private String settlementAmount = "0.00";
	/**
	 * 支付状态
	 */
	private String status;
	/**
	 * 创建时间
	 */
	private String creatTime;
	/**
	 * 支付时间
	 */
	private String payTime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 扩展数据
	 */
	private String extend;
	/**
	 * 产品编码
	 */
	private String productCode;
	
	private PayerInfo payerInfo;
	
	private String totalAmount;
	
	private String totalCount;
	
	
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public PayerInfo getPayerInfo() {
		return payerInfo;
	}
	public void setPayerInfo(PayerInfo payerInfo) {
		this.payerInfo = payerInfo;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getBusinessOrderNO() {
		return businessOrderNO;
	}
	public void setBusinessOrderNO(String businessOrderNO) {
		this.businessOrderNO = businessOrderNO;
	}
	public String getTradeOrderNo() {
		return tradeOrderNo;
	}
	public void setTradeOrderNo(String tradeOrderNo) {
		this.tradeOrderNo = tradeOrderNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}
	public String getSettlementAmount() {
		return settlementAmount;
	}
	public void setSettlementAmount(String settlementAmount) {
		this.settlementAmount = settlementAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getExtend() {
		return extend;
	}
	public void setExtend(String extend) {
		this.extend = extend;
	}
	@Override
	public String toString() {
		return "TradeOrder [businessOrderNO=" + businessOrderNO + ", tradeOrderNo=" + tradeOrderNo + ", name=" + name
				+ ", tradeType=" + tradeType + ", payMode=" + payMode + ", amount=" + amount + ", serviceFee="
				+ serviceFee + ", settlementAmount=" + settlementAmount + ", status=" + status + ", creatTime="
				+ creatTime + ", payTime=" + payTime + ", remark=" + remark + ", extend=" + extend + "]";
	}
	
	
}
