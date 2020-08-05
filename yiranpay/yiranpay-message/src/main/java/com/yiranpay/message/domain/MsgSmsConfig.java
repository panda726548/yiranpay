package com.yiranpay.message.domain;

import com.yiranpay.common.annotation.Excel;
import com.yiranpay.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 短息配置对象 msg_sms_config
 * 
 * @author glb
 * @date 2020-04-06
 */
public class MsgSmsConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long smsConfigId;

    /** 名称 */
    @Excel(name = "名称")
    private String smsConfigName;

    /** 键 */
    @Excel(name = "键")
    private String smsConfigKey;

    /** 值 */
    @Excel(name = "值")
    private String smsConfigValue;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setSmsConfigId(Long smsConfigId) 
    {
        this.smsConfigId = smsConfigId;
    }

    public Long getSmsConfigId() 
    {
        return smsConfigId;
    }
    public void setSmsConfigName(String smsConfigName) 
    {
        this.smsConfigName = smsConfigName;
    }

    public String getSmsConfigName() 
    {
        return smsConfigName;
    }
    public void setSmsConfigKey(String smsConfigKey) 
    {
        this.smsConfigKey = smsConfigKey;
    }

    public String getSmsConfigKey() 
    {
        return smsConfigKey;
    }
    public void setSmsConfigValue(String smsConfigValue) 
    {
        this.smsConfigValue = smsConfigValue;
    }

    public String getSmsConfigValue() 
    {
        return smsConfigValue;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("smsConfigId", getSmsConfigId())
            .append("smsConfigName", getSmsConfigName())
            .append("smsConfigKey", getSmsConfigKey())
            .append("smsConfigValue", getSmsConfigValue())
            .append("createBy", getCreateBy())
            .append("delFlag", getDelFlag())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
