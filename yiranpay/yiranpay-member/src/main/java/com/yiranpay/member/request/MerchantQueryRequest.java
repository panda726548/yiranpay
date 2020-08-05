package com.yiranpay.member.request;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiranpay.member.base.Request;

/**
 * <p>商户query请求参数</p>
 */
public class MerchantQueryRequest extends Request {

    /**
     *
     */
    private static final long serialVersionUID = 504512945373327823L;

    /**
     * 会员号
     */
    private String            memberId;

    public MerchantQueryRequest() {

    }

    public MerchantQueryRequest(String memberId) {
        this.memberId = memberId;

    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
