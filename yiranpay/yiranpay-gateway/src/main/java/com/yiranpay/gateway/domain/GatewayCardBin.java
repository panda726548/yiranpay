package com.yiranpay.gateway.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiranpay.common.annotation.Excel;
import com.yiranpay.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 卡Bin对象 gateway_card_bin
 * 
 * @author panda
 * @date 2020-01-06
 */
public class GatewayCardBin extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** BIN系统Id */
    private Long binId;

    /** 银行编码 */
    @Excel(name = "银行编码")
    private String bankCode;

    /** 卡类型：CC=贷记卡，DC=借记卡，SCC=准贷记卡，PC=预付卡 */
    @Excel(name = "卡类型：CC=贷记卡，DC=借记卡，SCC=准贷记卡，PC=预付卡")
    private String cardType;

    /** 卡种名称 */
    @Excel(name = "卡种名称")
    private String cardName;

    /** BIN号 */
    @Excel(name = "BIN号")
    private String binNo;

    /** 卡号长度 */
    @Excel(name = "卡号长度")
    private Integer cardLength;

    /** 发卡行代码 */
    @Excel(name = "发卡行代码")
    private String bankNo;

    /** 扩展信息 */
    @Excel(name = "扩展信息")
    private String extensions;

    /** 启用标识：Y启用，N停用 */
    @Excel(name = "启用标识：Y启用，N停用")
    private String enableFlag;

    /** 备注 */
    @Excel(name = "备注")
    private String memo;

    /** 版本 */
    @Excel(name = "版本")
    private Long version;

    /** 建立时间 */
    @Excel(name = "建立时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtCreate;

    /** 更新时间 */
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtModify;

    public void setBinId(Long binId) 
    {
        this.binId = binId;
    }

    public Long getBinId() 
    {
        return binId;
    }
    public void setBankCode(String bankCode) 
    {
        this.bankCode = bankCode;
    }

    public String getBankCode() 
    {
        return bankCode;
    }
    public void setCardType(String cardType) 
    {
        this.cardType = cardType;
    }

    public String getCardType() 
    {
        return cardType;
    }
    public void setCardName(String cardName) 
    {
        this.cardName = cardName;
    }

    public String getCardName() 
    {
        return cardName;
    }
    public void setBinNo(String binNo) 
    {
        this.binNo = binNo;
    }

    public String getBinNo() 
    {
        return binNo;
    }
    public void setCardLength(Integer cardLength) 
    {
        this.cardLength = cardLength;
    }

    public Integer getCardLength() 
    {
        return cardLength;
    }
    public void setBankNo(String bankNo) 
    {
        this.bankNo = bankNo;
    }

    public String getBankNo() 
    {
        return bankNo;
    }
    public void setExtensions(String extensions) 
    {
        this.extensions = extensions;
    }

    public String getExtensions() 
    {
        return extensions;
    }
    public void setEnableFlag(String enableFlag) 
    {
        this.enableFlag = enableFlag;
    }

    public String getEnableFlag() 
    {
        return enableFlag;
    }
    public void setMemo(String memo) 
    {
        this.memo = memo;
    }

    public String getMemo() 
    {
        return memo;
    }
    public void setVersion(Long version) 
    {
        this.version = version;
    }

    public Long getVersion() 
    {
        return version;
    }
    public void setGmtCreate(Date gmtCreate) 
    {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtCreate() 
    {
        return gmtCreate;
    }
    public void setGmtModify(Date gmtModify) 
    {
        this.gmtModify = gmtModify;
    }

    public Date getGmtModify() 
    {
        return gmtModify;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("binId", getBinId())
            .append("bankCode", getBankCode())
            .append("cardType", getCardType())
            .append("cardName", getCardName())
            .append("binNo", getBinNo())
            .append("cardLength", getCardLength())
            .append("bankNo", getBankNo())
            .append("extensions", getExtensions())
            .append("enableFlag", getEnableFlag())
            .append("memo", getMemo())
            .append("version", getVersion())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModify", getGmtModify())
            .toString();
    }
}
