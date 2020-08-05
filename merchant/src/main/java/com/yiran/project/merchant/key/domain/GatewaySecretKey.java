package com.yiran.project.merchant.key.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yiran.framework.web.domain.BaseEntity;

/**
 * 商户秘钥管理对象 gateway_secret_key
 * 
 * @author panda
 * @date 2020-01-15
 */
public class GatewaySecretKey extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键Id */
    private Long secretkeyId;

    /** 商户号 */
    private String merchantId;

    /** 公钥 */
    private String publicKey;

    /** 签约类型(RSA,MD5,AES,DES) */
    private String signType;

    public void setSecretkeyId(Long secretkeyId) 
    {
        this.secretkeyId = secretkeyId;
    }

    public Long getSecretkeyId() 
    {
        return secretkeyId;
    }
    public void setMerchantId(String merchantId) 
    {
        this.merchantId = merchantId;
    }

    public String getMerchantId() 
    {
        return merchantId;
    }
    public void setPublicKey(String publicKey) 
    {
        this.publicKey = publicKey;
    }

    public String getPublicKey() 
    {
        return publicKey;
    }
    public void setSignType(String signType) 
    {
        this.signType = signType;
    }

    public String getSignType() 
    {
        return signType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("secretkeyId", getSecretkeyId())
            .append("merchantId", getMerchantId())
            .append("publicKey", getPublicKey())
            .append("signType", getSignType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
