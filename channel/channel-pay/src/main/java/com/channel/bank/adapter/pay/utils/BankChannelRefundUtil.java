package com.channel.bank.adapter.pay.utils;

import java.math.BigDecimal;
import java.util.Date;

import com.channel.bank.adapter.pay.domain.ChannelFundResult;
import com.channel.bank.adapter.pay.enums.FundChannelApiType;
import com.channel.bank.adapter.pay.mode.RefundRequest;


/**
 * <p>退款工具类</p>
 */
public class BankChannelRefundUtil {


    /**
     * 返回退款异常时的响应
     * 
     * @param refundRequest
     * @param resultMsg	
     * @param resultCode
     * @param apiResultCode
     * @param apiResultMessage
     * @return
     */
    public static ChannelFundResult builFalidRefundResponse(RefundRequest refundRequest, String resultMsg,
                                                            String resultCode, String apiResultCode,
                                                            String apiResultMessage) {

        ChannelFundResult instOrderResult = new ChannelFundResult();
        instOrderResult.setInstOrderNo(refundRequest.getInstOrderNo());
        instOrderResult.setApiResultCode(apiResultCode);
        instOrderResult.setApiResultMessage(apiResultMessage);
        instOrderResult.setResultCode(resultCode);
        instOrderResult.setResultMessage(resultMsg);
        instOrderResult.setProcessTime(new Date());
        instOrderResult.setApiType(refundRequest.getApiType());
        instOrderResult.setRealAmount(refundRequest.getAmount());
        instOrderResult.setSuccess(false);
        return instOrderResult;
    }

    /**
     * 返回退款结果对象
     * 
     * @param refundNo 机构退款订单号
     * @param refundAmount	退款金额
     * @param instReturnOrderNo	银行返回流水号
     * @param apiResultCode api返回的结果码
     * @param apiMsg  api返回的结果描述
     * @return
     */
    public static ChannelFundResult buildRefundResult(String refundNo, String refundAmount,
    		String instReturnOrderNo,String apiResultCode, String apiMsg) {
        ChannelFundResult result = new ChannelFundResult();
        result.setInstOrderNo(refundNo);
        result.setInstReturnOrderNo(instReturnOrderNo);
        result.setApiResultCode(apiResultCode);
        result.setApiResultMessage(apiMsg);
        result.setRealAmount(new BigDecimal(refundAmount));
        result.setApiType(FundChannelApiType.SINGLE_REFUND);
        result.setSuccess(true);
        return result;
    }
}
