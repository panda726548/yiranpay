package com.channel.bank.adapter.pay.domain;

import com.channel.bank.adapter.pay.enums.ResponseType;

/**
 * 
 * <p>验签返回对象</p>
 */
public class EBankChannelVerifyResult extends ChannelFundResult {

    private static final long serialVersionUID = 5141347839629073979L;

    /** 页面跳转类型 */
    private ResponseType      responseType;

    /** 返回银行信息 */
    private String            returnToInstData;

    private String            responseData;

    /**
     * 验签是否成功
     */
    private boolean           isSuccess;

    private String            instRetPayDomain;

    private String            instRetPayIP;

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public String getReturnToInstData() {
        return returnToInstData;
    }

    public void setReturnToInstData(String returnToInstData) {
        this.returnToInstData = returnToInstData;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getInstRetPayDomain() {
        return instRetPayDomain;
    }

    public void setInstRetPayDomain(String instRetPayDomain) {
        this.instRetPayDomain = instRetPayDomain;
    }

    public String getInstRetPayIP() {
        return instRetPayIP;
    }

    public void setInstRetPayIP(String instRetPayIP) {
        this.instRetPayIP = instRetPayIP;
    }

}
