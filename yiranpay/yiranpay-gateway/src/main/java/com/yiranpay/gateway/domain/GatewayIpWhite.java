package com.yiranpay.gateway.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiranpay.common.annotation.Excel;
import com.yiranpay.common.core.domain.BaseEntity;

/**
 * IP白名单对象 gateway_ip_white
 * 
 * @author panda
 * @date 2020-01-09
 */
public class GatewayIpWhite extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 白名单IP */
    @Excel(name = "白名单IP")
    private String ipAddress;

    /** 商户号 */
    @Excel(name = "商户号")
    private String merchantId;

    /** 所属系统 */
    @Excel(name = "所属系统")
    private String system;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setIpAddress(String ipAddress) 
    {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() 
    {
        return ipAddress;
    }
    public void setMerchantId(String merchantId) 
    {
        this.merchantId = merchantId;
    }

    public String getMerchantId() 
    {
        return merchantId;
    }
    public void setSystem(String system) 
    {
        this.system = system;
    }

    public String getSystem() 
    {
        return system;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ipAddress", getIpAddress())
            .append("merchantId", getMerchantId())
            .append("system", getSystem())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
