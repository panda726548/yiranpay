package com.yiranpay.web.api.facade;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yiranpay.common.core.domain.ResultWrapper;
import com.yiranpay.gateway.response.CarBinResponse;
import com.yiranpay.gateway.response.ProcessResult;
import com.yiranpay.gateway.service.IQueryCardBinService;
import com.yiranpay.gateway.service.impl.QueryCardBinService;
import com.yiranpay.paychannel.utils.MapUtil;

@RestController
@RequestMapping("/api/yiranpay/gateway/cardBin")
public class QueryCardBinFacade {
	private static Logger logger = LoggerFactory.getLogger(QueryCardBinFacade.class);
	
	@Autowired
	private IQueryCardBinService queryCardBinService;
	
	/**
	 * 查询卡Bin信息
	 * @param param
	 * @return
	 */
	@PostMapping("/queryCardBin")
	public ResultWrapper queryCardBin(@RequestBody(required=false) String param){
		logger.info("【queryCardBin】请求参数:{}",param);
		Map<String, String> paramMap = MapUtil.jsonToMap(param);
		try {
			CarBinResponse process = queryCardBinService.process(paramMap);
			if (logger.isInfoEnabled()) {
				logger.info("卡Bin查询返回对象 : " + process);
			}
			if(process.getException()!=null){
				return ResultWrapper.error().newInstance(process.getException().getErrorCode(), process.getException().getErrorMsg());
			}else{
				return ResultWrapper.ok().putData(process);
			}
		} catch (Exception e) {
			logger.error("卡Bin查询异常 : ", e);
			throw new RuntimeException("调用queryCardBin接口异常", e);
		}
		
	}
}
