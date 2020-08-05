package com.yiranpay.gateway.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiranpay.common.annotation.Excel;
import com.yiranpay.common.core.domain.BaseEntity;

/**
 * 产品对象 gateway_product_info
 * 
 * @author panda
 * @date 2020-04-12
 */
public class GatewayProductInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品id */
    private Long productId;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 接口父ID */
    @Excel(name = "接口父ID")
    private Long parentId;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNum;

    /** api菜单类型（M目录 C菜单） */
    @Excel(name = "菜单类型", readConverterExp = "M=目录,C=菜单")
    private String parentMenuType;

    /** 状态（0禁用，1启用） */
    @Excel(name = "状态", readConverterExp = "0=禁用，1启用")
    private Integer status;

    /** 扩展1 */
    @Excel(name = "扩展1")
    private String extend1;

    /** 扩展2 */
    @Excel(name = "扩展2")
    private String extend2;

    /** 扩展3 */
    @Excel(name = "扩展3")
    private String extend3;
    
    /** 父菜单名称 */
    private String parentName;
    
    

    public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
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
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setOrderNum(Integer orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() 
    {
        return orderNum;
    }
    public void setParentMenuType(String parentMenuType) 
    {
        this.parentMenuType = parentMenuType;
    }

    public String getParentMenuType() 
    {
        return parentMenuType;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setExtend1(String extend1) 
    {
        this.extend1 = extend1;
    }

    public String getExtend1() 
    {
        return extend1;
    }
    public void setExtend2(String extend2) 
    {
        this.extend2 = extend2;
    }

    public String getExtend2() 
    {
        return extend2;
    }
    public void setExtend3(String extend3) 
    {
        this.extend3 = extend3;
    }

    public String getExtend3() 
    {
        return extend3;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productId", getProductId())
            .append("productCode", getProductCode())
            .append("productName", getProductName())
            .append("parentId", getParentId())
            .append("orderNum", getOrderNum())
            .append("parentMenuType", getParentMenuType())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("extend1", getExtend1())
            .append("extend2", getExtend2())
            .append("extend3", getExtend3())
            .toString();
    }
}
