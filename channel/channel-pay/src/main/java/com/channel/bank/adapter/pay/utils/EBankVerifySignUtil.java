package com.channel.bank.adapter.pay.utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.channel.bank.adapter.pay.constants.ReturnCode;
import com.channel.bank.adapter.pay.domain.ChannelFundResult;
import com.channel.bank.adapter.pay.enums.FundChannelApiType;
import com.channel.bank.adapter.pay.enums.ResponseType;


public class EBankVerifySignUtil {


    /**
     * 返回验签结果对象
     * @param requestMap
     * @param instOrderNo
     * @param amount
     * @param apiResultCode
     * @param instReturnOrderNo
     * @param userPayIP
     * @param type
     * @param responseData
     * @param domain
     * @return
     * @throws Exception
     */
    public static ChannelFundResult buildVerifySignResult(Map<String, String> requestMap,
                                                          String instOrderNo, String amount,
                                                          String apiResultCode,
                                                          String instReturnOrderNo, String userPayIP,
                                                          ResponseType type, String responseData,
                                                          String domain) throws Exception {
        Assert.notNull(amount, "金额不能为空");
        Assert.notNull(instOrderNo, "订单号不能为空");
        Assert.notNull(apiResultCode, "返回码不能为空");
        ChannelFundResult fundResult = new ChannelFundResult();
        fundResult.setInstOrderNo(instOrderNo);
        fundResult.setInstReturnOrderNo(instReturnOrderNo == null ? StringUtils.EMPTY
            : instReturnOrderNo);
        fundResult.setApiResultCode(apiResultCode);
        fundResult.setApiResultMessage("验证签名成功");
        fundResult.setApiType(FundChannelApiType.VERIFY_SIGN);
        fundResult.setProcessTime(new Date());
        fundResult.setRealAmount(new BigDecimal(amount).setScale(2, BigDecimal.ROUND_DOWN));

        ChannelHelper helper = new ChannelHelper();
        helper.setUserPayDomain(domain);
        helper.setUserPayIP(userPayIP);
        helper.setInstReturnData((java.util.Map<String, String>) requestMap);
        helper.setResponseType(type == null ? ResponseType.FORWARD : type);
        if (responseData != null)
            helper.setResponseToInstData(responseData);
        fundResult.setExtension(MapUtils.mapToJson(helper.build()));
        fundResult.setSuccess(true);
        return fundResult;
    }

    /**
     * 返回验签结果对象
     * 
     * @param request
     * @param instOrderNo
     * @param amount
     * @param apiResultCode
     * @return
     * @throws Exception
     */
    public static ChannelFundResult buildVerifySignResult(Map<String, String> requestMap,
                                                          String instOrderNo, String amount,
                                                          String apiResultCode) throws Exception {
        return buildVerifySignResult(requestMap, instOrderNo, amount, apiResultCode, null, null, null, null,
            null);
    }

    /**
     * 返回验签异常时的订单结果对象
     * 
     * @param instRetCode
     * @param instRetMsg
     * @return
     */
    public static ChannelFundResult buildFaildVerifyResult(String apiResultCode, String apiResultMessage) {
        ChannelFundResult retInfo = new ChannelFundResult();
        retInfo.setApiResultCode(apiResultCode);
        retInfo.setApiResultMessage(apiResultMessage);
        retInfo.setResultCode(ReturnCode.FAILED);
        retInfo.setResultMessage(StringUtils.EMPTY);
        retInfo.setSuccess(false);
        if (!ReturnCode.FAILED.equals(apiResultCode)) {
            ChannelHelper helper = new ChannelHelper();
            helper.setInstReturnCode(apiResultCode);
            helper.setInstReturnMsg(apiResultMessage);
            retInfo.setExtension(MapUtils.mapToJson(helper.build()));
        } else {
            retInfo.setExtension(MapUtils.mapToJson(ChannelHelper.buildErrorReturn()));
        }

        return retInfo;
    }



}
