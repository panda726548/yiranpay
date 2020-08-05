package com.channel.bank.adapter.pay.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.channel.bank.adapter.pay.enums.FundChannelApiType;


/**
 * <p>渠道支付结果对象</p>
 */
public class ChannelFundResult extends ChannelResult {
    private static final long serialVersionUID = -5232985693057220878L;

    /** 实际金额 */
    private BigDecimal             realAmount;
    /** 机构清算时间 */
    private Date              instSettleTime;
    /** 机构处理时间 */
    private Date              processTime;
    /** 机构URL地址*/
    private String            instUrl;
    /** 渠道编码*/
    private String            fundChannelCode;

    /**
     * 默认构造
     */
    public ChannelFundResult() {
    }

    /**
     * 构造
     * @param success
     * @param resultCode
     */
    public ChannelFundResult(boolean success, String resultCode) {
        super(success, resultCode);
    }

    /**
     * 根据详细信息构造
     * @param success
     * @param resultCode
     * @param message
     */
    public ChannelFundResult(boolean success, String resultCode, String message) {
        super(success, resultCode, message);
    }

    /**
     * 根据详细信息构造
     * @param success
     * @param resultCode
     * @param message
     * @param apiType
     */
    public ChannelFundResult(boolean success, String resultCode, String message,
                             FundChannelApiType apiType) {
        super(success, resultCode, message, apiType);
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public Date getInstSettleTime() {
        return instSettleTime;
    }

    public void setInstSettleTime(Date instSettleTime) {
        this.instSettleTime = instSettleTime;
    }

    public Date getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
    }

    public String getInstUrl() {
        return instUrl;
    }

    public void setInstUrl(String instUrl) {
        this.instUrl = instUrl;
    }

    public String getFundChannelCode() {
        return fundChannelCode;
    }

    public void setFundChannelCode(String fundChannelCode) {
        this.fundChannelCode = fundChannelCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
