package com.channel.bank.adapter.pay.domain;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 *
 * <p>商品订单详细信息</p>
 */
public class MerchantOrder {
	public static final String  ZERO_MONEY_STRING          = "0.0";
    protected String commodityName; //商品名称
    protected BigDecimal unitPrice = new BigDecimal(ZERO_MONEY_STRING).setScale(2, BigDecimal.ROUND_DOWN); //商品单价
    protected BigDecimal quantity; //数量
    protected BigDecimal totalAmount = new BigDecimal(ZERO_MONEY_STRING).setScale(2, BigDecimal.ROUND_DOWN); //总金额
    protected String orderDesc; //订单描述
    protected String merchantId; //商户号
    protected String merchantOrderNo;//商户订单号
    protected Map<String, String> extension;

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    public String getCommodityName() {
        return commodityName;
    }
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
    public BigDecimal getQuantity() {
        return quantity;
    }
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getOrderDesc() {
        return orderDesc;
    }
    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }
    public String getMerchantId() {
        return merchantId;
    }
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }
    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }
    public Map<String, String> getExtension() {
        return extension;
    }
    public void setExtension(Map<String, String> extension) {
        this.extension = extension;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
