package com.channel.bank.adapter.pay.domain;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.channel.bank.adapter.pay.enums.AccessChannel;
import com.channel.bank.adapter.pay.enums.PayMode;

/**
 * <p>EBank渠道支付请求类</p>
 */
public class EBankChannelFundRequest extends ChannelFundRequest {
    private static final long serialVersionUID = 3566102968597941518L;
    protected String memeberId; //会员ID
    protected String userId; //用户ID
    protected String userIp;
    protected String userDomain;
    protected PayMode payMode;
    protected AccessChannel accessChannel;
    protected Date orderDate; //订单日期
    protected Installment installmentInfo; //分期付款
    protected String merchantName; //商户名称
    protected List<MerchantOrder> merchantOrders; //商户商品信息

    public String getMemeberId() {
        return memeberId;
    }


    public void setMemeberId(String memeberId) {
        this.memeberId = memeberId;
    }


    public String getUserId() {
        return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getUserIp() {
        return userIp;
    }


    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }


    public String getUserDomain() {
        return userDomain;
    }


    public void setUserDomain(String userDomain) {
        this.userDomain = userDomain;
    }


    public Date getOrderDate() {
        return orderDate;
    }


    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }


    public AccessChannel getAccessChannel() {
        return accessChannel;
    }


    public void setAccessChannel(AccessChannel accessChannel) {
        this.accessChannel = accessChannel;
    }


    public Installment getInstallmentInfo() {
        return installmentInfo;
    }


    public void setInstallmentInfo(Installment installmentInfo) {
        this.installmentInfo = installmentInfo;
    }


    public PayMode getPayMode() {
        return payMode;
    }


    public void setPayMode(PayMode payMode) {
        this.payMode = payMode;
    }

    public String getMerchantName() {
        return merchantName;
    }


    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }


    public List<MerchantOrder> getMerchantOrders() {
        return merchantOrders;
    }


    public void setMerchantOrders(List<MerchantOrder> merchantOrders) {
        this.merchantOrders = merchantOrders;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
