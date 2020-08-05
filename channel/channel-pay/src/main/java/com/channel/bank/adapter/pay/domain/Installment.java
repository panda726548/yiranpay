package com.channel.bank.adapter.pay.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 *
 * <p>分期付款信息</p>
 */
public class Installment {
	public static final String  ZERO_MONEY_STRING          = "0.0";
    protected BigDecimal totalBigDecimal= new BigDecimal(ZERO_MONEY_STRING).setScale(2, BigDecimal.ROUND_DOWN); //总金额
    protected BigDecimal fee= new BigDecimal(ZERO_MONEY_STRING).setScale(2, BigDecimal.ROUND_DOWN); //手续费
    protected BigDecimal firstPaymentBigDecimal= new BigDecimal(ZERO_MONEY_STRING).setScale(2, BigDecimal.ROUND_DOWN); //首期金额
    protected BigDecimal lastPaymentBigDecimal= new BigDecimal(ZERO_MONEY_STRING).setScale(2, BigDecimal.ROUND_DOWN); //末期金额
    protected BigDecimal dividedBigDecimal= new BigDecimal(ZERO_MONEY_STRING).setScale(2, BigDecimal.ROUND_DOWN); //中间每期金额
    protected Integer numberOfPeroid; //期数
    public BigDecimal getTotalBigDecimal() {
        return totalBigDecimal;
    }
    public void setTotalBigDecimal(BigDecimal totalBigDecimal) {
        this.totalBigDecimal = totalBigDecimal;
    }
    public BigDecimal getFee() {
        return fee;
    }
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
    public BigDecimal getFirstPaymentBigDecimal() {
        return firstPaymentBigDecimal;
    }
    public void setFirstPaymentBigDecimal(BigDecimal firstPaymentBigDecimal) {
        this.firstPaymentBigDecimal = firstPaymentBigDecimal;
    }
    public BigDecimal getLastPaymentBigDecimal() {
        return lastPaymentBigDecimal;
    }
    public void setLastPaymentBigDecimal(BigDecimal lastPaymentBigDecimal) {
        this.lastPaymentBigDecimal = lastPaymentBigDecimal;
    }
    public BigDecimal getDividedBigDecimal() {
        return dividedBigDecimal;
    }
    public void setDividedBigDecimal(BigDecimal dividedBigDecimal) {
        this.dividedBigDecimal = dividedBigDecimal;
    }
    public Integer getNumberOfPeroid() {
        return numberOfPeroid;
    }
    public void setNumberOfPeroid(Integer numberOfPeroid) {
        this.numberOfPeroid = numberOfPeroid;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
