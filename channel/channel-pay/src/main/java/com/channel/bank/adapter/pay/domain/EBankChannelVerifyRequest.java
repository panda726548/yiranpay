package com.channel.bank.adapter.pay.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.channel.bank.adapter.pay.enums.CallBackType;

/**
 * 
 * <p>验签请求对象</p>
 */
public class EBankChannelVerifyRequest extends ChannelFundRequest {

    private static final long   serialVersionUID = 2973414315388583858L;

    private CallBackType     callBackType;

    private String              pageUrl;

    private String              serverUrl;

    private Map<String, String> responseData     = new ConcurrentHashMap<String, String>();

    private String              responseQueryString;

    public CallBackType getCallBackType() {
        return callBackType;
    }

    public void setCallBackType(CallBackType callBackType) {
        this.callBackType = callBackType;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public Map<String, String> getResponseData() {
        return responseData;
    }

    public void setResponseData(Map<String, String> responseData) {
        this.responseData = responseData;
    }

    public String getResponseQueryString() {
        return responseQueryString;
    }

    public void setResponseQueryString(String responseQueryString) {
        this.responseQueryString = responseQueryString;
    }

}
