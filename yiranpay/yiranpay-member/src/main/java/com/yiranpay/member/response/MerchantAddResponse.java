package com.yiranpay.member.response;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiranpay.member.base.Response;

/**
 * <p>添加商户响应信息</p>
 */
public class MerchantAddResponse extends Response {

    /**
     * 
     */
    private static final long serialVersionUID = 1471632835339148950L;

    /**
     * 商户号
     */
    private String            merchantId;
    /**
     * 开立商户时间
     */
    private Date              createTime;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
