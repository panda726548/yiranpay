package com.yiranpay.reconciliation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiranpay.common.annotation.Excel;
import com.yiranpay.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 入账流水对象 reconciliation_accountflow
 * 
 * @author panda
 * @date 2020-04-25
 */
public class ReconciliationAccountflow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 入账流水id */
    private Long glideId;

    /** 支付流水号 */
    @Excel(name = "支付流水号")
    private String paymentSeqNo;

    /** 清算指令ID */
    @Excel(name = "清算指令ID")
    private String clearingOrderId;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 支付编码 */
    @Excel(name = "支付编码")
    private String paymentCode;

    /** 资金渠道 */
    @Excel(name = "资金渠道")
    private String fundsChannel;

    /** 金额 */
    @Excel(name = "金额")
    private Double amount;

    /** 入账日期 */
    @Excel(name = "入账日期")
    private String accountDate;

    /** 机构订单号 */
    @Excel(name = "机构订单号")
    private String bizNo;

    /** 是否勾销流水，Y:是，N:否 */
    @Excel(name = "是否勾销流水，Y:是，N:否")
    private String isWriteoff;

    /** 对账状态。I:初始/S:成功/W:已勾销/L:逻辑错误/U:金额不等 */
    @Excel(name = "对账状态。I:初始/S:成功/W:已勾销/L:逻辑错误/U:金额不等")
    private String compareFlag;

    /** 对账日期 */
    @Excel(name = "对账日期")
    private String compareDate;

    /** 对账批次号 */
    @Excel(name = "对账批次号")
    private String compareBatchNo;

    /** 操作员（对账操作员） */
    @Excel(name = "操作员", readConverterExp = "对=账操作员")
    private String operator;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtCreate;

    /** 最后修改时间 */
    @Excel(name = "最后修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtModified;

    /** 备注 */
    @Excel(name = "备注")
    private String memo;

    /** 银行编码 */
    @Excel(name = "银行编码")
    private String bankCode;

    /** 业务类型，I:充值，O:提现，B:退款 */
    @Excel(name = "业务类型，I:充值，O:提现，B:退款")
    private String bizType;

    /** 会计凭证号，用于对应销账凭证，0表示未申请销账 */
    @Excel(name = "会计凭证号，用于对应销账凭证，0表示未申请销账")
    private String voucherNo;

    /** 待入历史标记，默认为空，Y:汇总确认待移入历史 */
    @Excel(name = "待入历史标记，默认为空，Y:汇总确认待移入历史")
    private String hisFlag;
    @Excel(name = "商户号")
    private String memberId;

    public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setGlideId(Long glideId) 
    {
        this.glideId = glideId;
    }

    public Long getGlideId() 
    {
        return glideId;
    }
    public void setPaymentSeqNo(String paymentSeqNo) 
    {
        this.paymentSeqNo = paymentSeqNo;
    }

    public String getPaymentSeqNo() 
    {
        return paymentSeqNo;
    }
    public void setClearingOrderId(String clearingOrderId) 
    {
        this.clearingOrderId = clearingOrderId;
    }

    public String getClearingOrderId() 
    {
        return clearingOrderId;
    }
    public void setProductCode(String productCode) 
    {
        this.productCode = productCode;
    }

    public String getProductCode() 
    {
        return productCode;
    }
    public void setPaymentCode(String paymentCode) 
    {
        this.paymentCode = paymentCode;
    }

    public String getPaymentCode() 
    {
        return paymentCode;
    }
    public void setFundsChannel(String fundsChannel) 
    {
        this.fundsChannel = fundsChannel;
    }

    public String getFundsChannel() 
    {
        return fundsChannel;
    }
    public void setAmount(Double amount) 
    {
        this.amount = amount;
    }

    public Double getAmount() 
    {
        return amount;
    }
    public void setAccountDate(String accountDate) 
    {
        this.accountDate = accountDate;
    }

    public String getAccountDate() 
    {
        return accountDate;
    }
    public void setBizNo(String bizNo) 
    {
        this.bizNo = bizNo;
    }

    public String getBizNo() 
    {
        return bizNo;
    }
    public void setIsWriteoff(String isWriteoff) 
    {
        this.isWriteoff = isWriteoff;
    }

    public String getIsWriteoff() 
    {
        return isWriteoff;
    }
    public void setCompareFlag(String compareFlag) 
    {
        this.compareFlag = compareFlag;
    }

    public String getCompareFlag() 
    {
        return compareFlag;
    }
    public void setCompareDate(String compareDate) 
    {
        this.compareDate = compareDate;
    }

    public String getCompareDate() 
    {
        return compareDate;
    }
    public void setCompareBatchNo(String compareBatchNo) 
    {
        this.compareBatchNo = compareBatchNo;
    }

    public String getCompareBatchNo() 
    {
        return compareBatchNo;
    }
    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }
    public void setGmtCreate(Date gmtCreate) 
    {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtCreate() 
    {
        return gmtCreate;
    }
    public void setGmtModified(Date gmtModified) 
    {
        this.gmtModified = gmtModified;
    }

    public Date getGmtModified() 
    {
        return gmtModified;
    }
    public void setMemo(String memo) 
    {
        this.memo = memo;
    }

    public String getMemo() 
    {
        return memo;
    }
    public void setBankCode(String bankCode) 
    {
        this.bankCode = bankCode;
    }

    public String getBankCode() 
    {
        return bankCode;
    }
    public void setBizType(String bizType) 
    {
        this.bizType = bizType;
    }

    public String getBizType() 
    {
        return bizType;
    }
    public void setVoucherNo(String voucherNo) 
    {
        this.voucherNo = voucherNo;
    }

    public String getVoucherNo() 
    {
        return voucherNo;
    }
    public void setHisFlag(String hisFlag) 
    {
        this.hisFlag = hisFlag;
    }

    public String getHisFlag() 
    {
        return hisFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("glideId", getGlideId())
            .append("paymentSeqNo", getPaymentSeqNo())
            .append("clearingOrderId", getClearingOrderId())
            .append("productCode", getProductCode())
            .append("paymentCode", getPaymentCode())
            .append("fundsChannel", getFundsChannel())
            .append("amount", getAmount())
            .append("accountDate", getAccountDate())
            .append("bizNo", getBizNo())
            .append("isWriteoff", getIsWriteoff())
            .append("compareFlag", getCompareFlag())
            .append("compareDate", getCompareDate())
            .append("compareBatchNo", getCompareBatchNo())
            .append("operator", getOperator())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .append("memo", getMemo())
            .append("bankCode", getBankCode())
            .append("bizType", getBizType())
            .append("voucherNo", getVoucherNo())
            .append("hisFlag", getHisFlag())
            .toString();
    }
}
