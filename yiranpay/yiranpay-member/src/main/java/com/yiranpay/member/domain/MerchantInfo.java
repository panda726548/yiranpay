package com.yiranpay.member.domain;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <p>商户信息</p>
 */
public class MerchantInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2827398981011186885L;
    /**
     * 商户号
     */
    private String            merchantId;
    /**
     * 会员号
     */
    private String            memberId;
    /**
     * 商户类型
     */
    private Long              merchantType;
    /**
     * 商户名
     */
    private String            merchantName;
    /**
     * 商户状态
     */
    private Long              merchantStatus;
    /**
     * 扩展字段
     */
    private String            extention;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Long getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(Long merchantType) {
        this.merchantType = merchantType;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Long getMerchantStatus() {
        return merchantStatus;
    }

    public void setMerchantStatus(Long merchantStatus) {
        this.merchantStatus = merchantStatus;
    }

    public String getExtention() {
        return extention;
    }

    public void setExtention(String extention) {
        this.extention = extention;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
