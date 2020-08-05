package com.yiranpay.gateway.service.impl;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yiranpay.gateway.constant.ReqConstant;
import com.yiranpay.gateway.domain.GatewayCardBin;
import com.yiranpay.gateway.exception.CommonDefinedException;
import com.yiranpay.gateway.request.UESRequest;
import com.yiranpay.gateway.response.ProcessResult;
import com.yiranpay.gateway.response.UESResponse;
import com.yiranpay.gateway.service.IEncryptionDecryptionService;
import com.yiranpay.gateway.service.base.GateWayServiceBase;
import com.yiranpay.gateway.tools.BaseRequestValidate;
import com.yiranpay.gateway.tools.RSAUtils;
import com.yiranpay.gateway.tools.RequestHelper;
import com.yiranpay.gateway.tools.SignUtils;
import com.yiranpay.paychannel.domain.TrFcTargetInstRelation;
import com.yiranpay.paychannel.utils.MapUtil;
import com.yiranpay.payorder.enums.EncryptType;
import com.yiranpay.payorder.service.IUesServiceClient;

/**
 * 加密解密
 * @author pandaa
 *
 */
@Service
public class EncryptionDecryptionService extends GateWayServiceBase implements IEncryptionDecryptionService{
	protected static final Logger logger = LoggerFactory.getLogger(EncryptionDecryptionService.class);
	private final static String ENCRYPTION ="encryption";//加密
	private final static String DECRYPTION ="decryption";//解密
	@Autowired
	private IUesServiceClient uesServiceClient;
	public UESResponse process(Map<String, String> paraMap) throws Exception {
		logger.debug("UES加密解密服务......");
		return parse(paraMap);
	}

	private UESResponse parse(Map<String, String> paraMap) throws Exception {
		//业务参数校验
		UESResponse result = validatPara(paraMap);
		if (result != null) {
			return result;
		}
		//请求参数入库MQ处理
		saveLogs(paraMap.get("partner_id"),JSON.toJSONString(paraMap),1L);
		// 转换为对象
		UESRequest uesRequestBin = (UESRequest) RequestHelper.convertFromMap(paraMap);
		logger.debug("参数转换为UESRequest:" + uesRequestBin.toString());
		//根据商户ID获取公钥
		String publicKeyByMerchantId = getPublicKeyByMerchantId(uesRequestBin.getPartnerId()).trim();
		logger.info("商户号【{}】公钥：{}",uesRequestBin.getPartnerId(),publicKeyByMerchantId);
		//获取签名原串
		String sign_src = SignUtils.genSignData(JSON.parseObject(MapUtil.mapToJson(paraMap)));
		logger.info("【UES加密解密服务】--签名原串:"+sign_src);
		logger.info("【UES加密解密服务】-----签名:"+uesRequestBin.getSign().trim());
		//签名
		boolean sign_flag = RSAUtils.verify(sign_src.getBytes(), publicKeyByMerchantId, uesRequestBin.getSign().trim());
		if(sign_flag){//验签通过
			logger.info("【UES加密解密服务】--签名验签通过");
			result = new UESResponse();
			String ticket ="";
			//加密或
			if(ENCRYPTION.equals(uesRequestBin.getType())){
				if("AES".equals(uesRequestBin.getEncryptType()) ){
					ticket = uesServiceClient.encryptData(uesRequestBin.getOriginalText(),EncryptType.AES);
				}else if("DES".equals(uesRequestBin.getEncryptType()) ){
					ticket = uesServiceClient.encryptData(uesRequestBin.getOriginalText(),EncryptType.DES);
				}
				result.setTicketNo(ticket);
			}else if(DECRYPTION.equals(uesRequestBin.getType())){
				String originalText = "";
				if("AES".equals(uesRequestBin.getEncryptType()) ){
					originalText = uesServiceClient.getDataByTicket(uesRequestBin.getTicketNo(), EncryptType.AES);
				}else if("DES".equals(uesRequestBin.getEncryptType()) ){
					originalText = uesServiceClient.getDataByTicket(uesRequestBin.getTicketNo(), EncryptType.DES);
				}
				result.setOriginalText(originalText);		
			}
			result.setResult(true);
			//系统私钥：
			String privateKey = getPrivateKey();
			//加签
			sign_src = SignUtils.genSignData(JSON.parseObject(JSON.toJSONString(result)));
			logger.info("【UES加密解密服务】返回结果签名原串:"+sign_src);
			String sign = RSAUtils.sign(sign_src.getBytes(),privateKey);
			result.setSign(sign);
		}else{
			logger.info("【UES加密解密服务】签名验签失败:"+sign_src);
			result = new UESResponse();
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_SIGN);
		}
		//返回结果入库MQ处理
		saveLogs(paraMap.get("partner_id"),JSON.toJSONString(result),2L);
		return result;
	}

	/**
	 * 参数校验
	 * @param queryCardBin
	 * @return
	 */
	private UESResponse validatPara(Map<String, String> paraMap) {
		logger.info("【UES加密解密服务】验证业务参数开始....");
		UESResponse result = new UESResponse();
		if(StringUtils.isBlank(paraMap.get(ReqConstant.UES_TYPE))){
			logger.info("【UES加密解密服务】验证业务参数-请求类型为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.UES_TYPE_IS_NULL);
            return result;
		}
		if(StringUtils.isBlank(paraMap.get(ReqConstant.UES_ENCRYPTTYPE))){
			logger.info("【UES加密解密服务】验证业务参数-加密类型 encryptType为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.UES_ENCRYPTTYPE_IS_NULL);
            return result;
		}

		if(ENCRYPTION.equals(paraMap.get(ReqConstant.UES_TYPE))){//加密  原文不能为空
			if(StringUtils.isBlank(paraMap.get(ReqConstant.UES_ORIGINALTEXT))){
				logger.info("【UES加密解密服务】验证业务参数-加密原文 originalText为空!");
				result.setResult(false);
	            result.setException(CommonDefinedException.UES_ORIGINALTEXT_IS_NULL);
	            return result;
			}
			
		}else if(DECRYPTION.equals(paraMap.get(ReqConstant.UES_TYPE))){//解密  票据不能为空
			if(StringUtils.isBlank(paraMap.get(ReqConstant.UES_TICKETNO))){
				logger.info("【UES加密解密服务】验证业务参数-解密票据号 ticketNo为空!");
				result.setResult(false);
	            result.setException(CommonDefinedException.UES_TICKETNO_IS_NULL);
	            return result;
			}
		}
		logger.info("【UES加密解密服务】验证业务参数结束....");
		return null;
	}
	

}
