package com.yiranpay.gateway.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiranpay.common.annotation.Excel;
import com.yiranpay.common.core.domain.BaseEntity;

/**
 * 商户和权限关联对象 gateway_api_auth
 * 
 * @author panda
 * @date 2020-01-06
 */
public class GatewayApiAuth extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 接口ID */
    private Long apiId;

    /** 商户ID */
    private String merchantId;

    public void setApiId(Long apiId) 
    {
        this.apiId = apiId;
    }

    public Long getApiId() 
    {
        return apiId;
    }
    public void setMerchantId(String merchantId) 
    {
        this.merchantId = merchantId;
    }

    public String getMerchantId() 
    {
        return merchantId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("apiId", getApiId())
            .append("merchantId", getMerchantId())
            .toString();
    }
}
