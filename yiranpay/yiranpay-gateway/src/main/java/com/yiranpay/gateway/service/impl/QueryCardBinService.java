package com.yiranpay.gateway.service.impl;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yiranpay.gateway.constant.ReqConstant;
import com.yiranpay.gateway.domain.GatewayCardBin;
import com.yiranpay.gateway.exception.CommonDefinedException;
import com.yiranpay.gateway.request.QueryCardBinReq;
import com.yiranpay.gateway.response.CarBinResponse;
import com.yiranpay.gateway.response.ProcessResult;
import com.yiranpay.gateway.service.IQueryCardBinService;
import com.yiranpay.gateway.service.base.GateWayServiceBase;
import com.yiranpay.gateway.tools.BaseRequestValidate;
import com.yiranpay.gateway.tools.RSAUtils;
import com.yiranpay.gateway.tools.RequestHelper;
import com.yiranpay.gateway.tools.SignUtils;
import com.yiranpay.paychannel.domain.TrFcTargetInstRelation;
import com.yiranpay.paychannel.utils.MapUtil;

/**
 * 卡BIN查询务
 * @author pandaa
 *
 */
@Service
public class QueryCardBinService extends GateWayServiceBase implements IQueryCardBinService{
	 protected static final Logger logger = LoggerFactory.getLogger(QueryCardBinService.class);
	 
	public CarBinResponse process(Map<String, String> paraMap) throws Exception {
		logger.debug("卡BIN查询服务......");
		return parse(paraMap);
	}

	private CarBinResponse parse(Map<String, String> paraMap) throws Exception {
		//业务参数校验
		CarBinResponse result = validatPara(paraMap);
		if (result != null) {
			return result;
		}
		//请求参数入库MQ处理
		saveLogs(paraMap.get("partner_id"),JSON.toJSONString(paraMap),1L);
		// 转换为对象
		QueryCardBinReq queryCardBin = (QueryCardBinReq) RequestHelper.convertFromMap(paraMap);
		logger.debug("参数转换为QueryCardBin:" + queryCardBin.toString());
		//根据商户ID获取公钥
		String publicKeyByMerchantId = getPublicKeyByMerchantId(queryCardBin.getPartnerId()).trim();
		//获取签名原串
		String sign_src = SignUtils.genSignData(JSON.parseObject(MapUtil.mapToJson(paraMap)));
		logger.info("【卡BIN查询服务】签名原串:"+sign_src);
		//签名
		boolean sign_flag = RSAUtils.verify(sign_src.getBytes(), publicKeyByMerchantId, queryCardBin.getSign().trim());
		if(sign_flag){//验签通过
			logger.info("【卡BIN查询服务】--签名验签通过");
			result = new CarBinResponse();
			//获取卡号前六位 卡bin号
			String binNo = queryCardBin.getCardNo().substring(0, 6);
			GatewayCardBin gatewayCardBin = new GatewayCardBin();
			gatewayCardBin.setBinNo(binNo);
			gatewayCardBin.setEnableFlag("Y");
			List<GatewayCardBin> cardBinList = gatewayCardBinService.selectGatewayCardBinList(gatewayCardBin);
			if(cardBinList.size()>0){
				result.setInputCharset(queryCardBin.getInputCharset());
				result.setSignType(queryCardBin.getSignType());
				result.setPartnerId(queryCardBin.getPartnerId());
				result.setCard_no(queryCardBin.getCardNo());
				result.setBuss_type(queryCardBin.getBussType());
				result.setInstcode(cardBinList.get(0).getBankCode());
				result.setCard_type(cardBinList.get(0).getCardType());
				result.setCard_name(cardBinList.get(0).getCardName());
				TrFcTargetInstRelation trFcTargetInstRelation = new TrFcTargetInstRelation();
				trFcTargetInstRelation.setTargetInstCode(cardBinList.get(0).getBankCode());
				List<TrFcTargetInstRelation> instRelationList = trFcTargetInstRelationService.selectTrFcTargetInstRelationList(trFcTargetInstRelation);
				if(instRelationList.size()>0){
					result.setIs_access("Y");
				}else{
					result.setIs_access("N");
				}
			}else{
				throw CommonDefinedException.MEMBER_NO_DATE_ERROR;
			}
			result.setResult(true);
			//系统私钥：
			String privateKey = getPrivateKey();
			//加签
			sign_src = SignUtils.genSignData(JSON.parseObject(JSON.toJSONString(result)));
			logger.info("【卡BIN查询服务】返回结果签名原串:"+sign_src);
			String sign = RSAUtils.sign(sign_src.getBytes(),privateKey);
			result.setSign(sign);
		}else{
			logger.info("【卡BIN查询服务】签名验签失败:"+sign_src);
			result = new CarBinResponse();
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
	private CarBinResponse validatPara(Map<String, String> paraMap) {
		logger.info("【卡BIN查询服务】验证业务参数开始....");
		CarBinResponse result = new CarBinResponse();
		if(StringUtils.isBlank(paraMap.get(ReqConstant.REQUEST_NO))){
			logger.info("【卡BIN查询服务】验证业务参数-请求号request_no为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT);
            return result;
		}
		if(StringUtils.isBlank(paraMap.get(ReqConstant.CARD_NO))){
			logger.info("【卡BIN查询服务】验证业务参数-请求号card_no为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT);
            return result;
		}
		if(StringUtils.isBlank(paraMap.get(ReqConstant.BUSS_TYPE))){
			logger.info("【卡BIN查询服务】验证业务参数-银行卡号buss_type为空!");
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT);
            return result;
		}
		logger.info("【卡BIN查询服务】验证业务参数结束....");
		return null;
	}
	

}
