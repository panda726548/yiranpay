package com.yiran.project.merchant.product.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yiran.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 产品审批明细对象 gateway_approval_leave
 * 
 * @author panda
 * @date 2020-04-12
 */
public class ApprovalLeave extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 审批ID */
    private Long auditid;

    /** 申请ID */
    private Long approvalId;

    /** 审批人 */
    private String userid;

    /** 审批结果：0不通过，1通过 */
    private Integer auditresult;

    /** 审批意见 */
    private String comment;

    /** 审批时间 */
    private Date audittime;

    public void setAuditid(Long auditid) 
    {
        this.auditid = auditid;
    }

    public Long getAuditid() 
    {
        return auditid;
    }
    public void setApprovalId(Long approvalId) 
    {
        this.approvalId = approvalId;
    }

    public Long getApprovalId() 
    {
        return approvalId;
    }
    public void setUserid(String userid) 
    {
        this.userid = userid;
    }

    public String getUserid() 
    {
        return userid;
    }
    public void setAuditresult(Integer auditresult) 
    {
        this.auditresult = auditresult;
    }

    public Integer getAuditresult() 
    {
        return auditresult;
    }
    public void setComment(String comment) 
    {
        this.comment = comment;
    }

    public String getComment() 
    {
        return comment;
    }
    public void setAudittime(Date audittime) 
    {
        this.audittime = audittime;
    }

    public Date getAudittime() 
    {
        return audittime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("auditid", getAuditid())
            .append("approvalId", getApprovalId())
            .append("userid", getUserid())
            .append("auditresult", getAuditresult())
            .append("comment", getComment())
            .append("audittime", getAudittime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
