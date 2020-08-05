package com.yiranpay.gateway.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiranpay.common.annotation.Excel;
import com.yiranpay.common.core.domain.BaseEntity;

/**
 * 产品申请对象 gateway_product_approval
 * 
 * @author panda
 * @date 2020-04-12
 */
public class GatewayProductApproval extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编码 */
    private Long id;

    /** 商户号 */
    @Excel(name = "商户号")
    private String memberNo;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 申请原因 */
    @Excel(name = "申请原因")
    private String reason;

    /** 流程状态：0申请，1审批中，2已完成 */
    @Excel(name = "流程状态：0申请，1审批中，2已完成")
    private Integer processstatus;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMemberNo(String memberNo) 
    {
        this.memberNo = memberNo;
    }

    public String getMemberNo() 
    {
        return memberNo;
    }
    public void setProductCode(String productCode) 
    {
        this.productCode = productCode;
    }

    public String getProductCode() 
    {
        return productCode;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
    }
    public void setProcessstatus(Integer processstatus) 
    {
        this.processstatus = processstatus;
    }

    public Integer getProcessstatus() 
    {
        return processstatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("memberNo", getMemberNo())
            .append("productCode", getProductCode())
            .append("productName", getProductName())
            .append("reason", getReason())
            .append("processstatus", getProcessstatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
