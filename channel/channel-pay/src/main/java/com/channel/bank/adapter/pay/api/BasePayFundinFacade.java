package com.channel.bank.adapter.pay.api;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.channel.bank.adapter.pay.domain.ChannelFundRequest;
import com.channel.bank.adapter.pay.domain.ChannelFundResult;
import com.channel.bank.adapter.pay.enums.FundChannelApiType;


public class BasePayFundinFacade {

	private static final String EMPTY_STRING ="";
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
    
    /**
     * 获取支付宝客户端
     *
     * @return AlipayClient
     */
    public AlipayClient getAlipayClient(String gateway,
    		String appId,
    		String privateKey,
    		String publicKey,
    		String charset,
    		String format,
    		String signType) {
        DefaultAlipayClient client = DefaultAlipayClient.builder(gateway, appId, privateKey)
                .alipayPublicKey(publicKey)
                .charset(charset)
                .format(format)
                .signType(signType)
                .build();
        return client;
    }
}
