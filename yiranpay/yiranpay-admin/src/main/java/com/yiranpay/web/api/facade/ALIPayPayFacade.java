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
import com.yiranpay.gateway.response.ALIPayResponse;
import com.yiranpay.gateway.service.impl.ALIPayServiceImpl;
import com.yiranpay.paychannel.utils.MapUtil;

@RestController
@RequestMapping("/api/yiranpay/gateway/alipay")
public class ALIPayPayFacade {
	
	private static Logger logger = LoggerFactory.getLogger(ALIPayPayFacade.class);
	
	@Autowired
	private ALIPayServiceImpl aliPayServiceImpl;
	
	@PostMapping("/createPay")
	public ResultWrapper createPay(@RequestBody(required=false) String param){
		logger.info("【支付宝支付--->createPay】请求参数:{}",param);
		Map<String, String> paramMap = MapUtil.jsonToMap(param);
		try {
			ALIPayResponse result = aliPayServiceImpl.process(paramMap);
			logger.info("【支付宝支付--->createPay】返回结果:{}",JSON.toJSONString(result));
			if(result.getException()!=null){
				return ResultWrapper.error().newInstance(result.getException().getErrorCode(), result.getException().getErrorMsg());
			}else{
				return ResultWrapper.ok().putData(result);
			}
		} catch (Exception e) {
			logger.error("创建支付异常 : ", e);
			throw new RuntimeException("调用支付宝支付接口异常", e);
		}
	}

}
