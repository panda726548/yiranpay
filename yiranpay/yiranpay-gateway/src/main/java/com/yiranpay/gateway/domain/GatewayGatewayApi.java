package com.yiranpay.gateway.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiranpay.common.annotation.Excel;
import com.yiranpay.common.core.domain.BaseEntity;

/**
 * 接口权限对象 gateway_gateway_api
 * 
 * @author panda
 * @date 2020-01-06
 */
public class GatewayGatewayApi extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** apiID */
    private Long apiId;

    /** 接口 */
    @Excel(name = "接口")
    private String apiCode;

    /** 接口名称 */
    @Excel(name = "接口名称")
    private String apiName;

    /** 接口父ID */
    @Excel(name = "接口父ID")
    private Long parentId;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNum;

    /** 请求地址 */
    @Excel(name = "请求地址")
    private String url;

    /** 扩展1 */
    @Excel(name = "扩展1")
    private String extend1;

    /** 扩展2 */
    @Excel(name = "扩展2")
    private String extend2;

    /** 扩展3 */
    @Excel(name = "扩展3")
    private String extend3;
    
    @Excel(name="状态（0禁用，1启用）")
    private int status;

    private String apiMenuType;
    
    /** 父菜单名称 */
    private String parentName;
    
    
    public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getApiMenuType() {
		return apiMenuType;
	}

	public void setApiMenuType(String apiMenuType) {
		this.apiMenuType = apiMenuType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setApiId(Long apiId) 
    {
        this.apiId = apiId;
    }

    public Long getApiId() 
    {
        return apiId;
    }
    public void setApiCode(String apiCode) 
    {
        this.apiCode = apiCode;
    }

    public String getApiCode() 
    {
        return apiCode;
    }
    public void setApiName(String apiName) 
    {
        this.apiName = apiName;
    }

    public String getApiName() 
    {
        return apiName;
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
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
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
            .append("apiId", getApiId())
            .append("apiCode", getApiCode())
            .append("apiName", getApiName())
            .append("parentId", getParentId())
            .append("orderNum", getOrderNum())
            .append("url", getUrl())
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
