package com.yiranpay.reconciliation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiranpay.common.annotation.Excel;
import com.yiranpay.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 清算流水对象 reconciliation_liquidationflow
 * 
 * @author panda
 * @date 2020-04-25
 */
public class ReconciliationLiquidationflow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 清算流水id */
    private Long id;

    /** 银行代码 */
    @Excel(name = "银行代码")
    private String bankCode;

    /** 机构订单号 */
    @Excel(name = "机构订单号")
    private String bizNo;

    /** 金额 */
    @Excel(name = "金额")
    private Double amount;

    /** 清算日期 */
    @Excel(name = "清算日期")
    private String bankDate;

    /** 对账批次号 */
    @Excel(name = "对账批次号")
    private String compareBatchNo;

    /** 对账日期 */
    @Excel(name = "对账日期")
    private String compareDate;

    /** 对账状态。I:初始/S:成功/M:多账/U:金额不等 */
    @Excel(name = "对账状态。I:初始/S:成功/M:多账/U:金额不等")
    private String compareFlag;

    /** 会计凭证号，用于对应挂账凭证，0表示未申请挂账 */
    @Excel(name = "会计凭证号，用于对应挂账凭证，0表示未申请挂账")
    private String voucherNo;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtCreate;

    /** 最后修改时间 */
    @Excel(name = "最后修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtModified;

    /** 操作员 */
    @Excel(name = "操作员")
    private String operator;

    /** 备注 */
    @Excel(name = "备注")
    private String memo;

    /** 资金渠道 */
    @Excel(name = "资金渠道")
    private String fundsChannel;

    /** 业务类型，I:充值，O:提现，B:退款 */
    @Excel(name = "业务类型，I:充值，O:提现，B:退款")
    private String bizType;

    /** 外部订单号 */
    @Excel(name = "外部订单号")
    private String outNo;

    /** 清算文件名 */
    @Excel(name = "清算文件名")
    private String fileName;

    /** 待入历史标记，默认为空，Y:汇总确认待移入历史 */
    @Excel(name = "待入历史标记，默认为空，Y:汇总确认待移入历史")
    private String hisFlag;

    /** 银行卡类型，dr：借记，cr：贷记 */
    @Excel(name = "银行卡类型，dr：借记，cr：贷记")
    private String paymentSeqNo;

    /** 支付流水号 */
    @Excel(name = "支付流水号")
    private String drcr;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBankCode(String bankCode) 
    {
        this.bankCode = bankCode;
    }

    public String getBankCode() 
    {
        return bankCode;
    }
    public void setBizNo(String bizNo) 
    {
        this.bizNo = bizNo;
    }

    public String getBizNo() 
    {
        return bizNo;
    }
    public void setAmount(Double amount) 
    {
        this.amount = amount;
    }

    public Double getAmount() 
    {
        return amount;
    }
    public void setBankDate(String bankDate) 
    {
        this.bankDate = bankDate;
    }

    public String getBankDate() 
    {
        return bankDate;
    }
    public void setCompareBatchNo(String compareBatchNo) 
    {
        this.compareBatchNo = compareBatchNo;
    }

    public String getCompareBatchNo() 
    {
        return compareBatchNo;
    }
    public void setCompareDate(String compareDate) 
    {
        this.compareDate = compareDate;
    }

    public String getCompareDate() 
    {
        return compareDate;
    }
    public void setCompareFlag(String compareFlag) 
    {
        this.compareFlag = compareFlag;
    }

    public String getCompareFlag() 
    {
        return compareFlag;
    }
    public void setVoucherNo(String voucherNo) 
    {
        this.voucherNo = voucherNo;
    }

    public String getVoucherNo() 
    {
        return voucherNo;
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
    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }
    public void setMemo(String memo) 
    {
        this.memo = memo;
    }

    public String getMemo() 
    {
        return memo;
    }
    public void setFundsChannel(String fundsChannel) 
    {
        this.fundsChannel = fundsChannel;
    }

    public String getFundsChannel() 
    {
        return fundsChannel;
    }
    public void setBizType(String bizType) 
    {
        this.bizType = bizType;
    }

    public String getBizType() 
    {
        return bizType;
    }
    public void setOutNo(String outNo) 
    {
        this.outNo = outNo;
    }

    public String getOutNo() 
    {
        return outNo;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setHisFlag(String hisFlag) 
    {
        this.hisFlag = hisFlag;
    }

    public String getHisFlag() 
    {
        return hisFlag;
    }
    public void setPaymentSeqNo(String paymentSeqNo) 
    {
        this.paymentSeqNo = paymentSeqNo;
    }

    public String getPaymentSeqNo() 
    {
        return paymentSeqNo;
    }
    public void setDrcr(String drcr) 
    {
        this.drcr = drcr;
    }

    public String getDrcr() 
    {
        return drcr;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bankCode", getBankCode())
            .append("bizNo", getBizNo())
            .append("amount", getAmount())
            .append("bankDate", getBankDate())
            .append("compareBatchNo", getCompareBatchNo())
            .append("compareDate", getCompareDate())
            .append("compareFlag", getCompareFlag())
            .append("voucherNo", getVoucherNo())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .append("operator", getOperator())
            .append("memo", getMemo())
            .append("fundsChannel", getFundsChannel())
            .append("bizType", getBizType())
            .append("outNo", getOutNo())
            .append("fileName", getFileName())
            .append("hisFlag", getHisFlag())
            .append("paymentSeqNo", getPaymentSeqNo())
            .append("drcr", getDrcr())
            .toString();
    }
}
