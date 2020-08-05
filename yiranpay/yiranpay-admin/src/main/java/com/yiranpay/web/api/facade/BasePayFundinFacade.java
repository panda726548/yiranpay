package com.yiranpay.web.api.facade;

import java.math.BigDecimal;
import java.util.Date;

import com.yiranpay.paychannel.enums.FundChannelApiType;
import com.yiranpay.payorder.domain.ChannelFundResult;
import com.yiranpay.payorder.request.ChannelFundRequest;


public class BasePayFundinFacade {

	public static final java.lang.String EMPTY_STRING = "";
	/**
     * 返回支付结果对象
     * 
     * @param instOrderNo
     * @param amount
     * @param apiResultCode
     * @param apiResultMessage
     * @return
     */
    protected ChannelFundResult buildFundinResult(String instOrderNo, String amount,
                                                  String apiResultCode, String apiResultMessage) {
        ChannelFundResult result = new ChannelFundResult();
        result.setApiType(FundChannelApiType.DEBIT);
        result.setInstOrderNo(instOrderNo);
        result.setInstReturnOrderNo(EMPTY_STRING);
        result.setApiResultCode(apiResultCode);
        result.setApiResultMessage(apiResultMessage);
        result.setRealAmount(new BigDecimal(amount).setScale(2, BigDecimal.ROUND_DOWN));
        result.setSuccess(true);
        return result;
    }

   /**
    * 
    * @param instOrderNo
    * @param sAmount
    * @param apiResultCode
    * @param apiResultMessage
    * @param instReturnOrderNo
    * @return
    */
    protected ChannelFundResult buildFundinResult(String instOrderNo, String sAmount,
                                                  String apiResultCode, String apiResultMessage,
                                                  String instReturnOrderNo) {
        ChannelFundResult result = new ChannelFundResult();
        result.setApiType(FundChannelApiType.DEBIT);
        result.setInstOrderNo(instOrderNo);
        result.setInstReturnOrderNo(instReturnOrderNo);
        result.setApiResultCode(apiResultCode);
        result.setApiResultMessage(apiResultMessage);
        result.setRealAmount(new BigDecimal(sAmount).setScale(2, BigDecimal.ROUND_DOWN));
        result.setSuccess(true);
        return result;
    }

    /**
     * 返回支付异常时的响应
     * 
     * @param channelFundRequest
     * @param resultMessage
     * @param resultCode
     * @param apiResultCode
     * @param apiResultMessage
     * @return
     */
    protected ChannelFundResult builFalidFundinResponse(ChannelFundRequest channelFundRequest, String resultMessage,
                                                        String resultCode, String apiResultCode,
                                                        String apiResultMessage) {

        ChannelFundResult instOrderResult = new ChannelFundResult();
        instOrderResult.setApiType(channelFundRequest.getApiType());
        instOrderResult.setInstOrderNo(channelFundRequest.getInstOrderNo());
        instOrderResult.setApiResultCode(apiResultCode);
        instOrderResult.setApiResultMessage(apiResultMessage);
        instOrderResult.setResultCode(resultCode);
        instOrderResult.setResultMessage(resultMessage);
        instOrderResult.setProcessTime(new Date());
        instOrderResult.setRealAmount(channelFundRequest.getAmount());
        instOrderResult.setSuccess(false);
        return instOrderResult;
    }
}
