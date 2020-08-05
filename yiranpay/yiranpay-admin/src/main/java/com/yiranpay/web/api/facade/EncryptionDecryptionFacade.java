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
import com.yiranpay.gateway.response.UESResponse;
import com.yiranpay.gateway.service.IEncryptionDecryptionService;
import com.yiranpay.gateway.service.IQueryCardBinService;
import com.yiranpay.gateway.service.impl.QueryCardBinService;
import com.yiranpay.paychannel.utils.MapUtil;

@RestController
@RequestMapping("/api/yiranpay/gateway/ues")
public class EncryptionDecryptionFacade {
	private static Logger logger = LoggerFactory.getLogger(EncryptionDecryptionFacade.class);
	@Autowired
	private IEncryptionDecryptionService encryptionDecryptionService;
	/**
	 * 加密解密
	 * @param param
	 * @return
	 */
	@PostMapping("/encryptionDecryption")
	public ResultWrapper encryptionDecryption(@RequestBody(required=false) String param){
		logger.info("【encryptionDecryption】请求参数:{}",param);
		Map<String, String> paramMap = MapUtil.jsonToMap(param);
		try {
			UESResponse process = encryptionDecryptionService.process(paramMap);
			if (logger.isInfoEnabled()) {
				logger.info("UES加密解密返回对象 : " + process);
			}
			if(process.getException()!=null){
				return ResultWrapper.error().newInstance(process.getException().getErrorCode(), process.getException().getErrorMsg());
			}else{
				return ResultWrapper.ok().putData(process);
			}
		} catch (Exception e) {
			logger.error("UES加密解密异常 : ", e);
			throw new RuntimeException("调用encryptionDecryption接口异常", e);
		}
		
	}
}
