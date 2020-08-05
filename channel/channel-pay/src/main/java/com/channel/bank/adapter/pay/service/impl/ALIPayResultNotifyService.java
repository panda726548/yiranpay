package com.channel.bank.adapter.pay.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.channel.bank.adapter.pay.config.WxPayH5Config;
import com.channel.bank.adapter.pay.constants.ReturnCode;
import com.channel.bank.adapter.pay.constants.WXPAYFundChannelKey;
import com.channel.bank.adapter.pay.domain.ChannelFundResult;
import com.channel.bank.adapter.pay.domain.ChannelRequest;
import com.channel.bank.adapter.pay.enums.FundChannelApiType;
import com.channel.bank.adapter.pay.enums.ResponseType;
import com.channel.bank.adapter.pay.property.PropertyHelper;
import com.channel.bank.adapter.pay.utils.AmountUtils;
import com.channel.bank.adapter.pay.utils.ChannelHelper;
import com.channel.bank.adapter.pay.utils.EBankVerifySignUtil;
import com.channel.bank.adapter.pay.utils.MapUtils;

/**
 * 支付宝支付异步通知接口
 * @author xupan
 *
 */
@Service
public class ALIPayResultNotifyService {
	 private Logger logger = LoggerFactory.getLogger(ALIPayResultNotifyService.class);
	 /**
	  * 支付宝异步通知
	  * @param channelRequest
	  * @return
	  */
	 public ChannelFundResult aliNotify(ChannelRequest channelRequest) {
	        String operInfo = getInfo(channelRequest);
	        try {
	            Map<String, String> respData = channelRequest.getExtension();
	            logger.info(operInfo + "【通知数据】:" + respData);
	            Map<String, String> fundResponse =MapUtils.jsonToMap(respData.get("notifyMsg"));
	            ChannelFundResult channelResult = new ChannelFundResult(); 	
	            channelResult.setFundChannelCode(channelRequest.getFundChannelCode());
	            //支付异步通知
				if("TRADE_SUCCESS".equals(fundResponse.get("trade_status"))) {
          		String apiResultCode = "success";
          		channelResult = buildVerifySignResult(
          				respData, 
          				fundResponse.get("out_trade_no"), 
          				fundResponse.get("total_amount"),
          				"0000", 
          				fundResponse.get("trade_no"), 
          				null,
          				ResponseType.RETURN_SERVER,
          				apiResultCode ,
          				null,
          				FundChannelApiType.NOTIFY.getCode());
          	} else {
          		return EBankVerifySignUtil.buildFaildVerifyResult(ReturnCode.EXCEPTION, "系统异常！");
          	}
				
     	//}
			logger.info("【异步通知结果】："+JSON.toJSONString(channelResult));
			return channelResult;
	         } catch (Exception e) {
	            e.printStackTrace();
	            String instRetMsg="异步通知处理出错：" + e.getMessage();
	            logger.error(operInfo + instRetMsg);
	            return EBankVerifySignUtil.buildFaildVerifyResult(ReturnCode.EXCEPTION, instRetMsg);
	        }
	  
	        
	    }
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
	     * @param apiType
	     * @return
	     * @throws Exception
	     */
	    public static ChannelFundResult buildVerifySignResult(Map<String, String> requestMap,
	                                                          String instOrderNo, 
	                                                          String amount,
	                                                          String apiResultCode,
	                                                          String instReturnOrderNo, 
	                                                          String userPayIP,
	                                                          ResponseType type, 
	                                                          String responseData,
	                                                          String domain,
	                                                          String apiType) throws Exception {
	        Assert.notNull(amount, "金额不能为空");
	        Assert.notNull(instOrderNo, "订单号不能为空");
	        Assert.notNull(apiResultCode, "返回码不能为空");
	        ChannelFundResult fundResult = new ChannelFundResult();
	        fundResult.setInstOrderNo(instOrderNo);
	        fundResult.setInstReturnOrderNo(instReturnOrderNo == null ? StringUtils.EMPTY
	            : instReturnOrderNo);
	        fundResult.setApiResultCode(apiResultCode);
	        if("SP".equals(apiType)){
	        	fundResult.setApiResultMessage("支付成功");
	        	fundResult.setApiType(FundChannelApiType.SINGLE_PAY);
	        }else if("SR".equals(apiType)){
	        	fundResult.setApiResultMessage("退款成功");
	        	fundResult.setApiType(FundChannelApiType.SINGLE_REFUND);
	        }else if("NT".equals(apiType)){
	        	fundResult.setApiResultMessage("异步通知支付成功");
	        	fundResult.setApiType(FundChannelApiType.NOTIFY);
	        }
	        fundResult.setApiResultSubCode("SUCCESS");
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
	     * 返回接口操作信息
	     * @param request
	     * @return
	     */
	    protected String getInfo(ChannelRequest request){
	    	StringBuffer sb = new StringBuffer();
	    	sb.append("FundChannelApi=").append(request.getFundChannelCode())
	    	.append("-").append(request.getApiType().getCode())
	    	.append(",InstOrderNo=").append(request.getInstOrderNo());
	    	return sb.toString();
		}
	    
	   
}
