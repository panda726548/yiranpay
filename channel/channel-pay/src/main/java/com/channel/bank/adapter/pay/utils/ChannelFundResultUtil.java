package com.channel.bank.adapter.pay.utils;

import java.math.BigDecimal;
import java.util.Date;

import com.channel.bank.adapter.pay.constants.ReturnCode;
import com.channel.bank.adapter.pay.domain.ChannelFundRequest;
import com.channel.bank.adapter.pay.domain.ChannelFundResult;
import com.channel.bank.adapter.pay.enums.FundChannelApiType;


public class ChannelFundResultUtil {
    /**
     * 构造查询错误的返回响应
     * 
     * @param apiResultMessage  api结果码说明
     * @param apiResultCode  api结果码
     * @param apiType   api类型
     * @return
     */
    public static ChannelFundResult buildFaildChannelFundResult(String apiResultMessage, String apiResultCode,
                                                                FundChannelApiType apiType) {
        ChannelFundResult response = new ChannelFundResult();
        response.setApiType(apiType);
        response.setApiResultCode(apiResultCode);
        response.setApiResultMessage(apiResultMessage);
        response.setProcessTime(new Date());
        response.setSuccess(false);
        return response;
    }

    /**
     * 返回查询错误时的响应
     * 
     * @param apiResultMessage  api结果码说明
     * @return  fundChannelApiType
     */
    public static ChannelFundResult buildFaildChannelFundResult(String apiResultMessage,
                                                                FundChannelApiType fundChannelApiType) {
        return buildFaildChannelFundResult(apiResultMessage, ReturnCode.FAILED, fundChannelApiType);
    }

   

    
    /**
	 * 返回支付结果对象
	 * 
	 * @param instOrderNo
	 * @param sAmount
	 * @param apiResultCode
	 * @param apiResultMessage
	 * @return
	 */
	protected ChannelFundResult buildFundinResult(String instOrderNo,
			String sAmount, String apiResultCode, String apiResultMessage,
			FundChannelApiType apiType) {
		ChannelFundResult result = new ChannelFundResult();
		result.setApiType(apiType);
		result.setInstOrderNo(instOrderNo);
		result.setInstReturnOrderNo(StringUtils.EMPTY_STRING);
		result.setApiResultCode(apiResultCode);
		result.setApiResultMessage(apiResultMessage);
		result.setRealAmount(new BigDecimal(sAmount));
		result.setSuccess(true);
		return result;
	}
    /**
	 * 返回支付异常时的响应
	 * 
	 * @param request
	 * @param resultMessage
	 * @param resultCode
	 * @param apiResultCode
	 * @param apiResultMessage
	 * @return
	 */
	public static ChannelFundResult builFalidFundinResponse(
			ChannelFundRequest request, String resultMessage, String resultCode,
			String apiResultCode, String apiResultMessage) {
		ChannelFundResult instOrderResult = new ChannelFundResult();
		instOrderResult.setApiType(request.getApiType());
		instOrderResult.setInstOrderNo(request.getInstOrderNo());
		instOrderResult.setApiResultCode(apiResultCode);
		instOrderResult.setApiResultMessage(apiResultMessage);
		instOrderResult.setResultCode(resultCode);
		instOrderResult.setResultMessage(resultMessage);
		instOrderResult.setProcessTime(new Date());
		instOrderResult.setRealAmount(request.getAmount());
		instOrderResult.setSuccess(false);
		return instOrderResult;
	}
}
