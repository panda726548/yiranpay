package com.yiranpay.web.api.interceptor;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.alibaba.fastjson.JSON;
import com.yiranpay.common.core.domain.ResultWrapper;
import com.yiranpay.common.utils.IpUtils;
import com.yiranpay.gateway.request.AuthorizationRequest;
import com.yiranpay.gateway.response.AuthorizationResult;
import com.yiranpay.gateway.response.ProcessResult;
import com.yiranpay.gateway.service.IAuthorizationService;
import com.yiranpay.gateway.tools.BaseRequestValidate;
import com.yiranpay.paychannel.utils.MapUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 接口权限拦截器
 * @author pandaa
 *
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
   
	protected static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);
	@Autowired
	private IAuthorizationService authorizationService;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	try {
    		String ip = IpUtils.getIpAddr(request);
        	logger.info("接口权限拦截器-请求IP地址："+ip);
            RequestWrapper requestWrapper = new RequestWrapper(request);
            String body = requestWrapper.getBody();
            logger.info("接口权限拦截器-参数："+body);
            Map<String, String> paramMap = MapUtil.jsonToMap(body);
            //基本参数校验
          	ProcessResult result = BaseRequestValidate.validatBasePara(paramMap);
          	if(result !=null){//基础数据校验不通过
          		if(!result.isResult()){
              		String jsondata = JSON.toJSONString(ResultWrapper.error().newInstance(result.getException().getErrorCode(), result.getException().getErrorMsg()));
              		returnJson(response, jsondata);
              		return false;
              	}
          	}
            AuthorizationRequest auth = new AuthorizationRequest();
            auth.setIpAddress(ip);
            String merchantId = paramMap.get("partner_id");
            auth.setMerchantId(merchantId);
            String service = paramMap.get("service");
            //获取产品code
            String productCode = paramMap.get("product_code");
            if(!StringUtils.isBlank(productCode)){//如果产品编码不为空就需要验证该商户是否有该产品权限
            	auth.setProductCode(productCode);
            	logger.info("产品权限-参数："+JSON.toJSONString(auth));
            	AuthorizationResult result2 = authorizationService.authorizationProduct(auth);
            	if(!result2.isAuth()){
                	String jsondata = JSON.toJSONString(ResultWrapper.error().newInstance(result2.getErrorCode(), result2.getErrorMsg()));
              		returnJson(response, jsondata);
                	return false;
                }
            }
            auth.setService(service);
            AuthorizationResult authorization = authorizationService.authorization(auth);
            logger.info("接口权限拦截器-商户权限校验结果："+JSON.toJSONString(authorization));
            if(!authorization.isAuth()){
            	String jsondata = JSON.toJSONString(ResultWrapper.error().newInstance(authorization.getErrorCode(), authorization.getErrorMsg()));
          		returnJson(response, jsondata);
            	return false;
            }
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            logger.info("执行控制器类名称：{}", handlerMethod.getBean().getClass().getName());
            logger.info("执行控制器方法： {}", handlerMethod.getMethod());
            return true;
        }catch (Exception e){
            logger.error("权限判断出错",e);
        }
		return false;
    }
    
    private void returnJson(HttpServletResponse response,String jsondata){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(jsondata);
        } catch (IOException e){
            
        } finally {
            if(writer != null){
                writer.close();
            }
        }
    }
    
}
