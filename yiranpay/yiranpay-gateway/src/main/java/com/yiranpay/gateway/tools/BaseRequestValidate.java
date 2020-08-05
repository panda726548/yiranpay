package com.yiranpay.gateway.tools;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yiranpay.gateway.constant.ReqConstant;
import com.yiranpay.gateway.exception.CommonDefinedException;
import com.yiranpay.gateway.response.ProcessResult;

/**
 * <p>基础数据验证</p>
 */
public class BaseRequestValidate {
    /**
     * 验证请求基本参数
     * @param request
     * @return
     */
	public static ProcessResult validatBasePara(Map<String, String> paraMap) {
		ProcessResult result = new ProcessResult();
		if(StringUtils.isBlank(paraMap.get(ReqConstant.SERVICE))){
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT_SERVICE_ISNULL);
            return result;
		}
		if(StringUtils.isBlank(paraMap.get(ReqConstant.VERSION))){
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT_VERSION_ISNULL);
            return result;
		}
		if(StringUtils.isBlank(paraMap.get(ReqConstant.PARNTER_ID))){
			result.setResult(false);
            result.setException(CommonDefinedException.ILLEGAL_ARGUMENT_PARNTER_ID_ISNULL);
            return result;
		}
		
		if(StringUtils.isBlank(paraMap.get(ReqConstant.INPUT_CHARSET))){
			result.setResult(false);
			result.setException(CommonDefinedException.ILLEGAL_ARGUMENT_INPUT_CHARSET_ISNULL);
			return result;
		}
		
		if(StringUtils.isBlank(paraMap.get(ReqConstant.SIGN_TYPE))){
			result.setResult(false);
			result.setException(CommonDefinedException.ILLEGAL_ARGUMENT_SIGN_TYPE_ISNULL);
			return result;
		}
		
		if(StringUtils.isBlank(paraMap.get(ReqConstant.SIGN))){
			result.setResult(false);
			result.setException(CommonDefinedException.ILLEGAL_ARGUMENT_SIGN_ISNULL);
			return result;
		}
		return null;
	}

}
