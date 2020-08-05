package com.yiran.project.merchant.product.domain;

import java.util.Date;

import com.yiran.framework.web.domain.BaseEntity;

/**
 * 产品商户关联对象 gateway_product_member
 * 
 * @author panda
 * @date 2020-04-12
 */
public class ProductMember extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 产品编码 */
    private String productCode;

    /** 商户号 */
    private String memberNo;

    /** 开始时间 */
    private Date startTime;

    /** 失效时间 */
    private Date endTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProductCode(String productCode) 
    {
        this.productCode = productCode;
    }

    public String getProductCode() 
    {
        return productCode;
    }
    public void setMemberNo(String memberNo) 
    {
        this.memberNo = memberNo;
    }

    public String getMemberNo() 
    {
        return memberNo;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }

}
