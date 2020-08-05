package com.yiranpay.member.response;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiranpay.member.base.Response;
import com.yiranpay.member.domain.MerchantInfo;

/**
 * <p>商户响应信息</p>
 */
public class MerchantResponse extends Response {

    /**
     * 
     */
    private static final long serialVersionUID = -2942064582828433210L;

    /**
     * 商户信息
     */
    private MerchantInfo      merchantInfo;

    public MerchantInfo getMerchantInfo() {
        return merchantInfo;
    }

    public void setMerchantInfo(MerchantInfo merchantInfo) {
        this.merchantInfo = merchantInfo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
