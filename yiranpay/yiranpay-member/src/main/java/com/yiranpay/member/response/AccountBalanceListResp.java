/**
 * 
 */
package com.yiranpay.member.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * <p>账户余额明细信息</p>
 */
public class AccountBalanceListResp implements Serializable {
    private static final long serialVersionUID = 1238283893868583693L;

    private String            productCode;                            //产品编码
    private String            payCode;                                //支付编码（支付类型）
    private String            sysTraceNo;                             //外部订单号
    private BigDecimal             txnAmt = new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN);                                 //金额
    private Date              txnTime;                                //交易时间
    private Integer           txnType;                                //交易类型(2 支出 3 收入)
    private BigDecimal             afterAvailAmt = new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN);                          //交易后可用余额
    private BigDecimal             afterAmt = new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN);                               //交易后总余额
    private String            summary;                                //摘要
    private String            remark;                                 //备注
    private String            extention;                              //扩展字段

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getSysTraceNo() {
        return sysTraceNo;
    }

    public void setSysTraceNo(String sysTraceNo) {
        this.sysTraceNo = sysTraceNo;
    }

    public Date getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(Date txnTime) {
        this.txnTime = txnTime;
    }

    public Integer getTxnType() {
        return txnType;
    }

    public void setTxnType(Integer txnType) {
        this.txnType = txnType;
    }

   

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getExtention() {
        return extention;
    }

    public void setExtention(String extention) {
        this.extention = extention;
    }

    public BigDecimal getTxnAmt() {
		return txnAmt;
	}

	public void setTxnAmt(BigDecimal txnAmt) {
		this.txnAmt = txnAmt;
	}

	public BigDecimal getAfterAvailAmt() {
		return afterAvailAmt;
	}

	public void setAfterAvailAmt(BigDecimal afterAvailAmt) {
		this.afterAvailAmt = afterAvailAmt;
	}

	public BigDecimal getAfterAmt() {
		return afterAmt;
	}

	public void setAfterAmt(BigDecimal afterAmt) {
		this.afterAmt = afterAmt;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
