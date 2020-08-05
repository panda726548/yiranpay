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
import com.yiranpay.gateway.request.ChinaH5PayQueryRequest;
import com.yiranpay.gateway.request.ChinaH5PayRequest;
import com.yiranpay.gateway.response.ChinaH5PayQueryResponse;
import com.yiranpay.gateway.response.ChinaH5PayResponse;
import com.yiranpay.gateway.service.IH5PayService;
import com.yiranpay.gateway.service.base.GateWayServiceBase;
import com.yiranpay.gateway.tools.RSAUtils;
import com.yiranpay.gateway.tools.RequestHelper;
import com.yiranpay.gateway.tools.SignUtils;
import com.yiranpay.paychannel.enums.BizType;
import com.yiranpay.paychannel.enums.PayMode;
import com.yiranpay.paychannel.utils.MapUtil;
import com.yiranpay.paychannel.vo.Extension;
import com.yiranpay.payorder.domaindo.ChannelPayOrderDO;
import com.yiranpay.payorder.domaindo.PayInstOrderDO;
import com.yiranpay.payorder.domaindo.PayInstOrderResultDO;
import com.yiranpay.payorder.request.PayOrderRequest;
import com.yiranpay.payorder.response.PayFundResult;
import com.yiranpay.payorder.service.IChannelPayOrderService;
import com.yiranpay.payorder.service.IFundRequestService;
import com.yiranpay.payorder.service.IPayInstOrderResultService;
import com.yiranpay.payorder.service.IPayInstOrderService;
@Service
public class H5PayServiceImpl extends GateWayServiceBase implements IH5PayService {
	
	@Autowired
	private IFundRequestService fundRequestService;
	@Autowired
	private IChannelPayOrderService channelPayOrderService;
	
	@Autowired
	private IPayInstOrderService payInstOrderService;
	
	@Autowired
	private IPayInstOrderResultService payInstOrderResultService;
	
	 protected static final Logger logger = LoggerFactory.getLogger(H5PayServiceImpl.class);
	@Override
	public ChinaH5PayResponse process(Map<String, String> paraMap) throws Exception {
		logger.debug("H5支付服务->创建支付订单......");
		return parse(paraMap);
	}
	
	private ChinaH5PayResponse parse(Map<String, String> paraMap) throws Exception{
		
		//业务参数校验
		ChinaH5PayResponse result = validatPara(paraMap);
		if (result != null) {
			return result;
		}
		//请求参数入库MQ处理
		saveLogs(paraMap.get("partner_id"),JSON.toJSONString(paraMap),1L);
		// 转换为对象
		ChinaH5PayRequest h5PayReq = (ChinaH5PayRequest) RequestHelper.convertFromMap(paraMap);
		logger.debug("参数转换为ChinaH5PayRequest:" + h5PayReq);
		//根据商户ID获取公钥
		String publicKeyByMerchantId = getPublicKeyByMerchantId(h5PayReq.getPartnerId()).trim();
		//获取签名原串
		String sign_src = SignUtils.genSignData(JSON.parseObject(MapUtil.mapToJson(paraMap)));
		logger.info("【H5支付服务】签名原串:"+sign_src);
		//签名
		boolean sign_flag = RSAUtils.verify(sign_src.getBytes(), publicKeyByMerchantId, h5PayReq.getSign().trim());
		if(sign_flag){//验签通过
			logger.info("【H5支付服务】--签名验签通过");
			PayFundResult sendPay = sendPay(h5PayReq);
			result = new ChinaH5PayResponse();
			result.setInputCharset(h5PayReq.getInputCharset());
			result.setSignType(h5PayReq.getSignType());
			result.setPartnerId(h5PayReq.getPartnerId());
			result.setUserId(h5PayReq.getUserId());
			result.setPayStatus(sendPay.getResultCode().getCode());
			result.setHtmlForm(splitJointForm(sendPay.getExtension().getValue("PAGE_URL")));
			result.setResult(true);
			//系统私钥：
			String privateKey = getPrivateKey();
			//加签
			sign_src = SignUtils.genSignData(JSON.parseObject(JSON.toJSONString(result)));
			logger.info("【H5支付服务】返回结果签名原串:"+sign_src);
			String sign = RSAUtils.sign(sign_src.getBytes(),privateKey);
			result.setSign(sign);
		}else{
			logger.info("【H5支付服务】签名验签失败:"+sign_src);
			result = new ChinaH5PayResponse();
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_SIGN);
		}
		
		return result;
	}
	private String splitJointForm(String formhtml) {
		StringBuffer bf = new StringBuffer();
		//bf.append("<html><head></head><body>");
		bf.append(formhtml);
		bf.append("<script language='javascript'>document.getElementById('frmBankID').submit();</script>");
		//bf.append("</body></html>");
		return bf.toString();
	}

	/**
	 * 创建订单
	 * @param paramMap
	 * @return
	 */
	private PayFundResult sendPay(ChinaH5PayRequest req) {
		PayOrderRequest request = new PayOrderRequest();
		double amount = Double.valueOf(req.getAmount());
		BigDecimal money = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_DOWN);
		logger.info("BigDecimal对象值："+money.toString());
		request.setAmount(money);
		request.setBizTime(new Date());
		request.setBizType(BizType.FUNDIN);
		request.setCurrencyCode("CNY");
		request.setInstCode(req.getInstcode());
		//会员ID 目前写死（会员系统还未完成）
		request.setMemberId(req.getUserId());
		//支付方式
		request.setPayMode(PayMode.NETBANK);
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
		ext.add("partnerId", req.getPartnerId());
		ext.add("clientId", "payment");
		ext.add("RETURN_URL", req.getReturnUrl());
		ext.add("PAGE_URL", req.getNotifyUrl());
		request.setExtension(ext);
		PayFundResult result = fundRequestService.apply(request);
		//返回结果入库MQ处理
		saveLogs(req.getPartnerId(),JSON.toJSONString(result),2L);
		logger.info("【H5支付服务】渠道返回结果:"+JSON.toJSONString(result));
		return result;
	}

	/**
	 * 业务参数校验
	 * @param paraMap
	 * @return
	 */
	private ChinaH5PayResponse validatPara(Map<String, String> paraMap) {
		logger.info("【H5支付服务】验证业务参数开始....");
		ChinaH5PayResponse result = new ChinaH5PayResponse();
		if(StringUtils.isBlank(paraMap.get(ReqConstant.REQUEST_NO))){
			logger.info("【H5支付服务】验证业务参数-请求号request_no为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT);
            return result;
		}
		if(StringUtils.isBlank(paraMap.get(ReqConstant.AMOUNT))){
			logger.info("【H5支付服务】验证业务参数-请求号amount为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT);
            return result;
		}
		if(StringUtils.isBlank(paraMap.get(ReqConstant.PRODUCT_NAME))){
			logger.info("【H5支付服务】验证业务参数-银行卡号product_name为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT);
            return result;
		}
		if(StringUtils.isBlank(paraMap.get(ReqConstant.PRODUCT_CODE))){
			logger.info("【H5支付服务】验证业务参数-银行卡号product_code为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT);
            return result;
		}
		
		if(StringUtils.isBlank(paraMap.get(ReqConstant.ORDERID))){
			logger.info("【H5支付服务】验证业务参数-银行卡号orderid为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT);
            return result;
		}
		
		if(StringUtils.isBlank(paraMap.get(ReqConstant.USER_ID))){
			logger.info("【H5支付服务】验证业务参数-银行卡号user_id为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT);
            return result;
		}
		if(StringUtils.isBlank(paraMap.get(ReqConstant.INSTCODE))){
			logger.info("【H5支付服务】验证业务参数-银行卡号instcode为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT);
            return result;
		}
		if(StringUtils.isBlank(paraMap.get(ReqConstant.PAYMENT_CODE))){
			logger.info("【H5支付服务】验证业务参数-银行卡号payment_code为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT);
            return result;
		}
		logger.info("【H5支付服务】验证业务参数结束....");
		return null;
	}

	@Override
	public ChinaH5PayQueryResponse queryPayprocess(Map<String, String> paramMap) throws Exception {
		logger.debug("H5支付服务->结果查询服务......");
		return queryParse(paramMap);
	}

	private ChinaH5PayQueryResponse queryParse(Map<String, String> paraMap) throws Exception{
		
		//业务参数校验
		ChinaH5PayQueryResponse result = validatQueryPara(paraMap);
		if (result != null) {
			return result;
		}
		//请求参数入库MQ处理
		saveLogs(paraMap.get("partner_id"),JSON.toJSONString(paraMap),1L);
		// 转换为对象
		ChinaH5PayQueryRequest h5PayReq = (ChinaH5PayQueryRequest) RequestHelper.convertFromMap(paraMap);
		logger.debug("参数转换为ChinaH5PayRequest:" + h5PayReq);
		//根据商户ID获取公钥
		String publicKeyByMerchantId = getPublicKeyByMerchantId(h5PayReq.getPartnerId()).trim();
		//获取签名原串
		String sign_src = SignUtils.genSignData(JSON.parseObject(MapUtil.mapToJson(paraMap)));
		logger.info("【H5支付服务-查询支付结果】签名原串:"+sign_src);
		//签名
		boolean sign_flag = RSAUtils.verify(sign_src.getBytes(), publicKeyByMerchantId, h5PayReq.getSign().trim());
		if(sign_flag){//验签通过
			logger.info("【H5支付服务-查询支付结果】--签名验签通过");
			//查询支付结果
			//1.根据商户订单号到channel_pay_order查询支付结果 --->成功返回结果.失败---->查询失败原因，返回失败
			ChannelPayOrderDO channelPayOrder = new ChannelPayOrderDO();
			channelPayOrder.setPaymentSeqNo(h5PayReq.getRequestNo());//商户订单号
			List<ChannelPayOrderDO> payOrderList = channelPayOrderService.selectChannelPayOrderList(channelPayOrder);
			if(payOrderList.size() == 0){
				result = new ChinaH5PayQueryResponse();
				result.setResult(false);
	            result.setException(CommonDefinedException.MEMBER_NO_DATE_ERROR);
	            return result;
			}
			result = new ChinaH5PayQueryResponse();
			channelPayOrder  = payOrderList.get(0);
				
			PayInstOrderResultDO or = new PayInstOrderResultDO();
			or.setInstOrderId(channelPayOrder.getInstOrderId());
			List<PayInstOrderResultDO> instOrderResultList = payInstOrderResultService.selectPayInstOrderResultList(or);
			or = instOrderResultList.get(0);
			result.setPayStatus(channelPayOrder.getStatus());
			if("F".equals(or.getInstStatus())){
				result.setErrorCode(or.getInstResultCode());
				result.setErrorMessage(or.getMemo());
			}
			result.setOuterTradeNo(channelPayOrder.getPaymentSeqNo());
			result.setInnerTradeNo(channelPayOrder.getPaySeqNo());
			result.setInputCharset(h5PayReq.getInputCharset());
			result.setSignType(h5PayReq.getSignType());
			result.setPartnerId(h5PayReq.getPartnerId());
			result.setResult(true);
			//系统私钥：
			String privateKey = getPrivateKey();
			//加签
			sign_src = SignUtils.genSignData(JSON.parseObject(JSON.toJSONString(result)));
			logger.info("【H5支付服务-查询支付结果】返回结果签名原串:"+sign_src);
			String sign = RSAUtils.sign(sign_src.getBytes(),privateKey);
			result.setSign(sign);
		}else{
			logger.info("【H5支付服务-查询支付结果】签名验签失败:"+sign_src);
			result = new ChinaH5PayQueryResponse();
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_SIGN);
		}
		//返回结果入库MQ处理
		saveLogs(paraMap.get("partner_id"),JSON.toJSONString(result),2L);
		return result;
	}

	private ChinaH5PayQueryResponse validatQueryPara(Map<String, String> paraMap) {
		ChinaH5PayQueryResponse result = new ChinaH5PayQueryResponse();
		if(StringUtils.isBlank(paraMap.get(ReqConstant.REQUEST_NO))){
			logger.info("【H5支付服务-查询支付结果】验证业务参数-请求号request_no为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT);
            return result;
		}
		return null;
	}
}
