package com.yiranpay.web.api.facade;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.yiranpay.common.core.domain.ResultWrapper;
import com.yiranpay.gateway.response.ChinaH5PayQueryResponse;
import com.yiranpay.gateway.response.ChinaH5PayResponse;
import com.yiranpay.gateway.service.impl.H5PayServiceImpl;
import com.yiranpay.paychannel.utils.MapUtil;

@RestController
@RequestMapping("/api/yiranpay/gateway/chinaH5Pay")
public class ChinaH5PayFacade {
	
	private static Logger logger = LoggerFactory.getLogger(ChinaH5PayFacade.class);
	
	@Autowired
	private H5PayServiceImpl h5PayServiceImpl;
	
	@PostMapping("/createPay")
	public ResultWrapper createPay(@RequestBody(required=false) String param){
		logger.info("【ChinaH5PayFacade--->createPay】请求参数:{}",param);
		Map<String, String> paramMap = MapUtil.jsonToMap(param);
		try {
			ChinaH5PayResponse result = h5PayServiceImpl.process(paramMap);
			logger.info("【ChinaH5PayFacade--->createPay】返回结果:{}",JSON.toJSONString(result));
			if(result.getException()!=null){
				return ResultWrapper.error().newInstance(result.getException().getErrorCode(), result.getException().getErrorMsg());
			}else{
				return ResultWrapper.ok().putData(result);
			}
		} catch (Exception e) {
			logger.error("创建支付异常 : ", e);
			throw new RuntimeException("调用createPay接口异常", e);
		}
	}

	@PostMapping("/queryPay")
	public ResultWrapper queryPay(@RequestBody(required=false) String param){
		logger.info("【ChinaH5PayFacade--->queryPay】请求参数:{}",param);
		Map<String, String> paramMap = MapUtil.jsonToMap(param);
		try {
			ChinaH5PayQueryResponse result = h5PayServiceImpl.queryPayprocess(paramMap);
			logger.info("【ChinaH5PayFacade--->queryPay】返回结果:{}",JSON.toJSONString(result));
			if(result.getException()!=null){
				return ResultWrapper.error().newInstance(result.getException().getErrorCode(), result.getException().getErrorMsg());
			}else{
				return ResultWrapper.ok().putData(result);
			}
		} catch (Exception e) {
			logger.error("创建支付异常 : ", e);
			throw new RuntimeException("调用queryPay接口异常", e);
		}
	}
}
