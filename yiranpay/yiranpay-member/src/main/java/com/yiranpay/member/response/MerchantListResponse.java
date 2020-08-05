package com.yiranpay.member.response;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiranpay.member.base.Response;
import com.yiranpay.member.domain.MerchantInfo;

/**
 * <p>商户集合响应信息</p>
 */
public class MerchantListResponse extends Response {

    /**
     * 
     */
    private static final long  serialVersionUID = 6861980584730233037L;

    /**
     * 商户集合信息
     */
    private List<MerchantInfo> merchantInfos;

    public List<MerchantInfo> getMerchantInfos() {
        return merchantInfos;
    }

    public void setMerchantInfos(List<MerchantInfo> merchantInfos) {
        this.merchantInfos = merchantInfos;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
