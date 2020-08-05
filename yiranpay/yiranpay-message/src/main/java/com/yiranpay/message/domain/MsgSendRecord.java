package com.yiranpay.message.domain;

import com.yiranpay.common.annotation.Excel;
import com.yiranpay.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 短信发送记录对象 msg_send_record
 * 
 * @author glb
 * @date 2020-04-06
 */
public class MsgSendRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 短信发送记录id */
    private Long sendRecordId;

    /** 短信发送流水号 */
    @Excel(name = "短信发送流水号")
    private String msgOrderNo;

    /** 短信验证码 */
    @Excel(name = "短信验证码")
    private String sendRecordVerifyCode;

    /** 模版类型 */
    @Excel(name = "模版类型")
    private String sendRecordBusinessType;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String sendRecordPhone;

    /** 模版编码 */
    @Excel(name = "模版编码")
    private String sendRecordTemplateCode;

    /** 发送数据 */
    @Excel(name = "发送数据")
    private String sendRecordTemplateContent;

    /** 发送时间 */
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sendRecordTime;

    /** 发送状态 */
    @Excel(name = "发送状态")
    private String sendRecordStatus;

    /** 发送响应消息ID */
    @Excel(name = "发送响应消息ID")
    private String sendRecordSmsId;

    /** 返回码 */
    @Excel(name = "返回码")
    private String sendRecordCode;

    /** 返回消息 */
    @Excel(name = "返回消息")
    private String sendRecordMsg;

    /** 删除标记（0：正常；1：删除） */
    @Excel(name = "删除标记", readConverterExp = "0=：正常；1：删除")
    private String sendRecordDelFlag;

    /** 响应时间 */
    @Excel(name = "响应时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sendRecordResponseTime;

    public void setSendRecordId(Long sendRecordId) 
    {
        this.sendRecordId = sendRecordId;
    }

    public Long getSendRecordId() 
    {
        return sendRecordId;
    }
    public void setMsgOrderNo(String msgOrderNo) 
    {
        this.msgOrderNo = msgOrderNo;
    }

    public String getMsgOrderNo() 
    {
        return msgOrderNo;
    }
    public void setSendRecordVerifyCode(String sendRecordVerifyCode) 
    {
        this.sendRecordVerifyCode = sendRecordVerifyCode;
    }

    public String getSendRecordVerifyCode() 
    {
        return sendRecordVerifyCode;
    }
    public void setSendRecordBusinessType(String sendRecordBusinessType) 
    {
        this.sendRecordBusinessType = sendRecordBusinessType;
    }

    public String getSendRecordBusinessType() 
    {
        return sendRecordBusinessType;
    }
    public void setSendRecordPhone(String sendRecordPhone) 
    {
        this.sendRecordPhone = sendRecordPhone;
    }

    public String getSendRecordPhone() 
    {
        return sendRecordPhone;
    }

    public String getSendRecordTemplateCode() {
        return sendRecordTemplateCode;
    }

    public void setSendRecordTemplateCode(String sendRecordTemplateCode) {
        this.sendRecordTemplateCode = sendRecordTemplateCode;
    }

    public void setSendRecordTemplateContent(String sendRecordTemplateContent)
    {
        this.sendRecordTemplateContent = sendRecordTemplateContent;
    }

    public String getSendRecordTemplateContent() 
    {
        return sendRecordTemplateContent;
    }
    public void setSendRecordTime(Date sendRecordTime) 
    {
        this.sendRecordTime = sendRecordTime;
    }

    public Date getSendRecordTime() 
    {
        return sendRecordTime;
    }
    public void setSendRecordStatus(String sendRecordStatus) 
    {
        this.sendRecordStatus = sendRecordStatus;
    }

    public String getSendRecordStatus() 
    {
        return sendRecordStatus;
    }
    public void setSendRecordSmsId(String sendRecordSmsId) 
    {
        this.sendRecordSmsId = sendRecordSmsId;
    }

    public String getSendRecordSmsId() 
    {
        return sendRecordSmsId;
    }
    public void setSendRecordCode(String sendRecordCode) 
    {
        this.sendRecordCode = sendRecordCode;
    }

    public String getSendRecordCode() 
    {
        return sendRecordCode;
    }
    public void setSendRecordMsg(String sendRecordMsg) 
    {
        this.sendRecordMsg = sendRecordMsg;
    }

    public String getSendRecordMsg() 
    {
        return sendRecordMsg;
    }
    public void setSendRecordDelFlag(String sendRecordDelFlag) 
    {
        this.sendRecordDelFlag = sendRecordDelFlag;
    }

    public String getSendRecordDelFlag() 
    {
        return sendRecordDelFlag;
    }
    public void setSendRecordResponseTime(Date sendRecordResponseTime) 
    {
        this.sendRecordResponseTime = sendRecordResponseTime;
    }

    public Date getSendRecordResponseTime() 
    {
        return sendRecordResponseTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sendRecordId", getSendRecordId())
            .append("msgOrderNo", getMsgOrderNo())
            .append("sendRecordVerifyCode", getSendRecordVerifyCode())
            .append("sendRecordBusinessType", getSendRecordBusinessType())
            .append("sendRecordPhone", getSendRecordPhone())
            .append("sendRecordTemplateCode", getSendRecordTemplateCode())
            .append("sendRecordTemplateContent", getSendRecordTemplateContent())
            .append("sendRecordTime", getSendRecordTime())
            .append("sendRecordStatus", getSendRecordStatus())
            .append("sendRecordSmsId", getSendRecordSmsId())
            .append("sendRecordCode", getSendRecordCode())
            .append("sendRecordMsg", getSendRecordMsg())
            .append("sendRecordDelFlag", getSendRecordDelFlag())
            .append("sendRecordResponseTime", getSendRecordResponseTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
