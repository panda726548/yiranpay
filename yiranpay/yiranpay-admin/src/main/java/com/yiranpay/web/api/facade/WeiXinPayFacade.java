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
import com.yiranpay.gateway.response.WeiXinPayResponse;
import com.yiranpay.gateway.service.impl.WeiXinPayServiceImpl;
import com.yiranpay.paychannel.utils.MapUtil;

@RestController
@RequestMapping("/api/yiranpay/gateway/weixinPay")
public class WeiXinPayFacade {
	
	private static Logger logger = LoggerFactory.getLogger(WeiXinPayFacade.class);
	
	@Autowired
	private WeiXinPayServiceImpl weiXinPayServiceImpl;
	
	@PostMapping("/createPay")
	public ResultWrapper createPay(@RequestBody(required=false) String param){
		logger.info("【微信支付--->createPay】请求参数:{}",param);
		Map<String, String> paramMap = MapUtil.jsonToMap(param);
		try {
			WeiXinPayResponse result = weiXinPayServiceImpl.process(paramMap);
			logger.info("【微信支付--->createPay】返回结果:{}",JSON.toJSONString(result));
			if(result.getException()!=null){
				return ResultWrapper.error().newInstance(result.getException().getErrorCode(), result.getException().getErrorMsg());
			}else{
				return ResultWrapper.ok().putData(result);
			}
		} catch (Exception e) {
			logger.error("创建支付异常 : ", e);
			throw new RuntimeException("调用微信支付接口异常", e);
		}
	}

}
