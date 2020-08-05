package com.yiranpay.message.domain;

import com.yiranpay.common.annotation.Excel;
import com.yiranpay.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Email邮件对象 msg_email
 * 
 * @author glb
 * @date 2020-04-06
 */
public class MsgEmail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 邮箱编号 */
    private Long emailId;

    /** 0：站内信；1：站外信 */
    @Excel(name = "0：站内信；1：站外信")
    private Integer emailSite;

    /** 用户编号，发送方 */
    @Excel(name = "用户编号，发送方")
    private Long fromUser;

    /** 用户编号，接收方 */
    @Excel(name = "用户编号，接收方")
    private Long toUser;

    /** 接收方邮件地址 */
    @Excel(name = "接收方邮件地址")
    private String toUserEmail;

    /** 邮件主题 */
    @Excel(name = "邮件主题")
    private String emailSubject;

    /** 邮件内容 */
    @Excel(name = "邮件内容")
    private String emailContent;

    /** 邮件状态(0：正常；1：已删除） */
    @Excel(name = "邮件状态(0：正常；1：已删除）")
    private Integer emailStatus;

    /** 邮件类型(例：工作，广告，文档等，可在字典中配置) */
    @Excel(name = "邮件类型(例：工作，广告，文档等，可在字典中配置)")
    private String emailType;

    /** 邮件形式(例：收件箱，重要，草稿，垃圾箱等，可在字典中配置)) */
    @Excel(name = "邮件形式(例：收件箱，重要，草稿，垃圾箱等，可在字典中配置))")
    private String emailFolder;

    /** 邮件标签(例：朋友；音乐，家庭，自定义标签，可在字典中配置) */
    @Excel(name = "邮件标签(例：朋友；音乐，家庭，自定义标签，可在字典中配置)")
    private String emailLabel;

    /** 发送邮件状态(0：成功；1：失败) */
    @Excel(name = "发送邮件状态(0：成功；1：失败)")
    private Integer sendStatus;

    /** 发信人姓名*/
    private String userName;

    /** 传递 */
    private String toUserIds;
    private String toUserEmails;

    public void setEmailId(Long emailId) 
    {
        this.emailId = emailId;
    }

    public Long getEmailId() 
    {
        return emailId;
    }
    public void setEmailSite(Integer emailSite) 
    {
        this.emailSite = emailSite;
    }

    public Integer getEmailSite() 
    {
        return emailSite;
    }
    public void setFromUser(Long fromUser) 
    {
        this.fromUser = fromUser;
    }

    public Long getFromUser() 
    {
        return fromUser;
    }
    public void setToUser(Long toUser) 
    {
        this.toUser = toUser;
    }

    public Long getToUser() 
    {
        return toUser;
    }
    public void setToUserEmail(String toUserEmail) 
    {
        this.toUserEmail = toUserEmail;
    }

    public String getToUserEmail() 
    {
        return toUserEmail;
    }
    public void setEmailSubject(String emailSubject) 
    {
        this.emailSubject = emailSubject;
    }

    public String getEmailSubject() 
    {
        return emailSubject;
    }
    public void setEmailContent(String emailContent) 
    {
        this.emailContent = emailContent;
    }

    public String getEmailContent() 
    {
        return emailContent;
    }
    public void setEmailStatus(Integer emailStatus) 
    {
        this.emailStatus = emailStatus;
    }

    public Integer getEmailStatus() 
    {
        return emailStatus;
    }
    public void setEmailType(String emailType) 
    {
        this.emailType = emailType;
    }

    public String getEmailType() 
    {
        return emailType;
    }
    public void setEmailFolder(String emailFolder) 
    {
        this.emailFolder = emailFolder;
    }

    public String getEmailFolder() 
    {
        return emailFolder;
    }
    public void setEmailLabel(String emailLabel) 
    {
        this.emailLabel = emailLabel;
    }

    public String getEmailLabel() 
    {
        return emailLabel;
    }
    public void setSendStatus(Integer sendStatus) 
    {
        this.sendStatus = sendStatus;
    }

    public Integer getSendStatus() 
    {
        return sendStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToUserIds() {
        return toUserIds;
    }

    public void setToUserIds(String toUserIds) {
        this.toUserIds = toUserIds;
    }

    public String getToUserEmails() {
        return toUserEmails;
    }

    public void setToUserEmails(String toUserEmails) {
        this.toUserEmails = toUserEmails;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("emailId", getEmailId())
            .append("emailSite", getEmailSite())
            .append("fromUser", getFromUser())
            .append("toUser", getToUser())
            .append("toUserEmail", getToUserEmail())
            .append("emailSubject", getEmailSubject())
            .append("emailContent", getEmailContent())
            .append("emailStatus", getEmailStatus())
            .append("emailType", getEmailType())
            .append("emailFolder", getEmailFolder())
            .append("emailLabel", getEmailLabel())
            .append("sendStatus", getSendStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
