package com.yiranpay.gateway.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.yiranpay.gateway.constant.ReqConstant;
import com.yiranpay.gateway.exception.CommonDefinedException;
import com.yiranpay.gateway.request.WeiXinPayRequest;
import com.yiranpay.gateway.response.WeiXinPayResponse;
import com.yiranpay.gateway.service.IWeiXinPayService;
import com.yiranpay.gateway.service.base.GateWayServiceBase;
import com.yiranpay.gateway.tools.RSAUtils;
import com.yiranpay.gateway.tools.RequestHelper;
import com.yiranpay.gateway.tools.SignUtils;
import com.yiranpay.paychannel.enums.BizType;
import com.yiranpay.paychannel.enums.PayMode;
import com.yiranpay.paychannel.utils.MapUtil;
import com.yiranpay.paychannel.vo.Extension;
import com.yiranpay.payorder.request.PayOrderRequest;
import com.yiranpay.payorder.response.PayFundResult;
import com.yiranpay.payorder.service.IChannelPayOrderService;
import com.yiranpay.payorder.service.IFundRequestService;
import com.yiranpay.payorder.service.IPayInstOrderResultService;
import com.yiranpay.payorder.service.IPayInstOrderService;
@Service
public class WeiXinPayServiceImpl extends GateWayServiceBase implements IWeiXinPayService {
	
	@Autowired
	private IFundRequestService fundRequestService;
	@Autowired
	private IChannelPayOrderService channelPayOrderService;
	
	@Autowired
	private IPayInstOrderService payInstOrderService;
	
	@Autowired
	private IPayInstOrderResultService payInstOrderResultService;
	
	 protected static final Logger logger = LoggerFactory.getLogger(WeiXinPayServiceImpl.class);
	@Override
	public WeiXinPayResponse process(Map<String, String> paraMap) throws Exception {
		logger.debug("微信支付服务->创建支付订单......");
		return parse(paraMap);
	}
	
	private WeiXinPayResponse parse(Map<String, String> paraMap) throws Exception{
		
		//业务参数校验
		WeiXinPayResponse result = validatPara(paraMap);
		if (result != null) {
			return result;
		}
		//请求参数入库处理
		saveLogs(paraMap.get("partner_id"),JSON.toJSONString(paraMap),1l);
		// 转换为对象
		WeiXinPayRequest payReq = (WeiXinPayRequest) RequestHelper.convertFromMap(paraMap);
		logger.debug("参数转换为WeiXinPayRequest:" + payReq);
		//根据商户ID获取公钥
		String publicKeyByMerchantId = getPublicKeyByMerchantId(payReq.getPartnerId()).trim();
		//获取签名原串
		String sign_src = SignUtils.genSignData(JSON.parseObject(MapUtil.mapToJson(paraMap)));
		logger.info("【微信支付服务】签名原串:"+sign_src);
		//签名
		boolean sign_flag = RSAUtils.verify(sign_src.getBytes(), publicKeyByMerchantId, payReq.getSign().trim());
		if(sign_flag){//验签通过
			logger.info("【微信支付服务】--签名验签通过");
			PayFundResult sendPay = sendPay(payReq);
			result = new WeiXinPayResponse();
			result.setInputCharset(payReq.getInputCharset());
			result.setSignType("RSA");
			result.setPartnerId(payReq.getPartnerId());
			result.setUserId(payReq.getUserId());
			converterPayResponse(sendPay.getExtension(), result);
			result.setPaySignType(payReq.getSignType());
			
			
			//系统私钥：
			String privateKey = getPrivateKey();
			//加签
			sign_src = SignUtils.genSignData(JSON.parseObject(JSON.toJSONString(result)));
			logger.info("【微信支付服务】返回结果签名原串:"+sign_src);
			String sign = RSAUtils.sign(sign_src.getBytes(),privateKey);
			result.setSign(sign);
		}else{
			logger.info("【微信支付服务】签名验签失败:"+sign_src);
			result = new WeiXinPayResponse();
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_SIGN);
		}
		
		return result;
	}
	

	/**
	 * 创建订单
	 * @param paramMap
	 * @return
	 */
	private PayFundResult sendPay(WeiXinPayRequest req) {
		PayOrderRequest request = new PayOrderRequest();
		double amount = Double.valueOf(req.getAmount());
		BigDecimal money = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_DOWN);
		logger.info("BigDecimal对象值："+money.toString());
		request.setAmount(money);
		request.setBizTime(new Date());
		request.setBizType(BizType.FUNDIN);
		request.setCurrencyCode("CNY");
		request.setInstCode(req.getInstcode());
		//会员ID 
		request.setMemberId(req.getUserId());
		//支付方式
		request.setPayMode(PayMode.TRUSTCOLLECT);
		//支付编码
		request.setPaymentCode(req.getPaymentCode());
		//支付订单号
		request.setPaymentSeqNo(req.getOrderId());
		//产品编码
		request.setProductCode(req.getProductCode());
		Extension ext = new Extension();
		ext.add("subject", req.getProductName());
		ext.add("product_desc", req.getProductDesc());
		ext.add("COMPANY_OR_PERSONAL", "C");
		ext.add("DBCR", "DC");
		ext.add("ACCESS_CHANNEL", "WEB");
		ext.add("smsSender", "PLATFORM");
		ext.add("payeeId", "innerMember");
		ext.add("openId", req.getOpenId());
		ext.add("partnerId", req.getPartnerId());
		ext.add("payType", req.getPayType());
		ext.add("channelName", req.getChannelName());
		ext.add("clientId", "payment");
		ext.add("RETURN_URL", req.getReturnUrl());
		ext.add("PAGE_URL", req.getNotifyUrl());
		request.setExtension(ext);
		PayFundResult result = fundRequestService.apply(request);
		//返回结果入库MQ处理
		saveLogs(req.getPartnerId(),JSON.toJSONString(result),2L);
		logger.info("【微信支付服务】渠道返回结果:"+JSON.toJSONString(result));
		return result;
	}

	private void converterPayResponse(Extension ext,WeiXinPayResponse result) {
		result.setAppId(ext.getValue("appId"));
		result.setNonceStr(ext.getValue("nonceStr"));
		result.setPackAge(ext.getValue("packAge"));
		result.setPaySign(ext.getValue("paySign"));
		result.setReturnCode(ext.getValue("returnCode"));
		result.setReturnMsg(ext.getValue("returnMsg"));
		result.setSignType(ext.getValue("signType"));
		result.setTimeStamp(ext.getValue("timeStamp"));
		result.setCodeUrl(ext.getValue("codeUrl"));
		result.setResultCode(ext.getValue("resultCode"));
	}
	/**
	 * 业务参数校验
	 * @param paraMap
	 * @return
	 */
	private WeiXinPayResponse validatPara(Map<String, String> paraMap) {
		logger.info("【微信支付服务】验证业务参数开始....");
		WeiXinPayResponse result = new WeiXinPayResponse();
		if(StringUtils.isBlank(paraMap.get(ReqConstant.REQUEST_NO))){
			logger.info("【微信支付服务】验证业务参数-请求号request_no为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT);
            return result;
		}
		if(StringUtils.isBlank(paraMap.get(ReqConstant.AMOUNT))){
			logger.info("【微信支付服务】验证业务参数-支付金额amount为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT);
            return result;
		}
		if(StringUtils.isBlank(paraMap.get(ReqConstant.PRODUCT_NAME))){
			logger.info("【微信支付服务】验证业务参数-产品名称product_name为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT);
            return result;
		}
		if(StringUtils.isBlank(paraMap.get(ReqConstant.PRODUCT_CODE))){
			logger.info("【微信支付服务】验证业务参数-产品编码product_code为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT);
            return result;
		}
		
		if(StringUtils.isBlank(paraMap.get(ReqConstant.ORDERID))){
			logger.info("【微信支付服务】验证业务参数-订单号orderid为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT);
            return result;
		}
		
		if(StringUtils.isBlank(paraMap.get(ReqConstant.USER_ID))){
			logger.info("【微信支付服务】验证业务参数-会员号user_id为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT);
            return result;
		}
		if(StringUtils.isBlank(paraMap.get(ReqConstant.INSTCODE))){
			logger.info("【微信支付服务】验证业务参数-银行编码instcode为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT);
            return result;
		}
		if(StringUtils.isBlank(paraMap.get(ReqConstant.PAYMENT_CODE))){
			logger.info("【微信支付服务】验证业务参数-支付编码payment_code为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT);
            return result;
		}
		
		if(StringUtils.isBlank(paraMap.get(ReqConstant.PAY_TYPE))){
			logger.info("【微信支付服务】验证业务参数-支付类型pay_type为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT);
            return result;
		}
		logger.info("【微信支付服务】验证业务参数结束....");
		return null;
	}

}
