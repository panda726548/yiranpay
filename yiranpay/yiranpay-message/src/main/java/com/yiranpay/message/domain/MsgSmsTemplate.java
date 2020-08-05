package com.yiranpay.message.domain;

import com.yiranpay.common.annotation.Excel;
import com.yiranpay.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 短息模板对象 msg_sms_template
 * 
 * @author glb
 * @date 2020-04-06
 */
public class MsgSmsTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 模版id(主键) */
    private Long smsTemplateId;

    /** 模版名称 */
    @Excel(name = "模版名称")
    private String smsTemplateName;

    /** 模版编码 */
    @Excel(name = "模版编码")
    private String smsTemplateCode;

    /** 模版类型 */
    @Excel(name = "模版类型")
    private String smsTemplateBusinessType;

    /** 模版内容 */
    @Excel(name = "模版内容")
    private String smsTemplateContent;

    /** 删除标记（0：正常；1：删除） */
    @Excel(name = "删除标记", readConverterExp = "0=：正常；1：删除")
    private String smsTemplateDelFlag;

    public void setSmsTemplateId(Long smsTemplateId) 
    {
        this.smsTemplateId = smsTemplateId;
    }

    public Long getSmsTemplateId() 
    {
        return smsTemplateId;
    }
    public void setSmsTemplateName(String smsTemplateName) 
    {
        this.smsTemplateName = smsTemplateName;
    }

    public String getSmsTemplateName() 
    {
        return smsTemplateName;
    }
    public void setSmsTemplateCode(String smsTemplateCode) 
    {
        this.smsTemplateCode = smsTemplateCode;
    }

    public String getSmsTemplateCode() 
    {
        return smsTemplateCode;
    }
    public void setSmsTemplateBusinessType(String smsTemplateBusinessType) 
    {
        this.smsTemplateBusinessType = smsTemplateBusinessType;
    }

    public String getSmsTemplateBusinessType() 
    {
        return smsTemplateBusinessType;
    }

    public String getSmsTemplateContent() {
        return smsTemplateContent;
    }

    public void setSmsTemplateContent(String smsTemplateContent) {
        this.smsTemplateContent = smsTemplateContent;
    }

    public void setSmsTemplateDelFlag(String smsTemplateDelFlag)
    {
        this.smsTemplateDelFlag = smsTemplateDelFlag;
    }

    public String getSmsTemplateDelFlag() 
    {
        return smsTemplateDelFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("smsTemplateId", getSmsTemplateId())
            .append("smsTemplateName", getSmsTemplateName())
            .append("smsTemplateCode", getSmsTemplateCode())
            .append("smsTemplateBusinessType", getSmsTemplateBusinessType())
            .append("smsTemplateContent", getSmsTemplateContent())
            .append("smsTemplateDelFlag", getSmsTemplateDelFlag())
            .toString();
    }
}
