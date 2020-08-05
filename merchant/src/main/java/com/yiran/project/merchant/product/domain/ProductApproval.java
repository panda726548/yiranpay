package com.yiran.project.merchant.product.domain;
import com.yiran.framework.web.domain.BaseEntity;

/**
 * 产品申请对象 gateway_product_approval
 * 
 * @author panda
 * @date 2020-04-12
 */
public class ProductApproval extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编码 */
    private Long id;

    /** 商户号 */
    private String memberNo;

    /** 产品编码 */
    private String productCode;

    /** 产品名称 */
    private String productName;

    /** 申请原因 */
    private String reason;

    /** 流程状态：0申请，1审批中，2已完成 */
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

}
