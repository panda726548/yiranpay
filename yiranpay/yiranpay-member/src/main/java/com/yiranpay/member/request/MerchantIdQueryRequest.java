package com.yiranpay.member.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiranpay.member.base.Request;


/**
 * <p>商户query请求参数</p>
 */
public class MerchantIdQueryRequest extends Request {

    /**
     * 
     */
    private static final long serialVersionUID = -3926842009521576543L;

    /**
     * 商户号
     */
    private String            merchantId;

    public MerchantIdQueryRequest() {

    }

    public MerchantIdQueryRequest(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
