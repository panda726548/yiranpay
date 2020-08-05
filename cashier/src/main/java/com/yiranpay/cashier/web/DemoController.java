package com.yiranpay.cashier.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.yiranpay.cashier.constants.CashierConstants;
import com.yiranpay.cashier.support.request.ChinaH5PayRequest;
import com.yiranpay.cashier.support.response.ChinaH5PayQueryResponse;
import com.yiranpay.cashier.support.response.OrderInfoResponse;
import com.yiranpay.cashier.support.response.ResponseResult;
import com.yiranpay.cashier.tool.HttpEncoding;
import com.yiranpay.cashier.tool.MapUtil;
import com.yiranpay.cashier.tool.OrderNoTool;
import com.yiranpay.cashier.tool.RSAUtils;
import com.yiranpay.cashier.tool.SignUtils;
import com.yiranpay.cashier.tool.WebHttpClient;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * 首页控制器
 */
@Controller
public class DemoController  {
	
	private static final Log logger = LogFactory.get(DemoController.class);
	
	private static final String api_url ="http://127.0.0.1:8088/yiranpay/order/getOrderInfo";
	
	
	
    @GetMapping("/demo")
    public String demo(ModelMap mmap) {
    	String orderId = OrderNoTool.generate();
    	logger.info("订单号："+orderId);
    	mmap.put("orderId", orderId);
        return "demo";
    }

    @GetMapping("/success")
    public String success2(ModelMap mmap) {
        return "success2";
    }
    
    @GetMapping("/success/{paymentSeqNo}")
    public String success(@PathVariable("paymentSeqNo") String paymentSeqNo,ModelMap mmap) {
    	logger.info("订单号："+paymentSeqNo);
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("paymentSeqNo", paymentSeqNo);
    	String respData =WebHttpClient.postRequest(JSON.toJSONString(params),api_url,HttpEncoding.UTF8, null, null);
    	logger.info("调服务返回结果："+ respData);
    	Map<String, String> dataToMap = MapUtil.jsonToMap(respData);
    	OrderInfoResponse order = JSON.parseObject(dataToMap.get("data"), OrderInfoResponse.class);
    	logger.info("调服务返回结果-转换对象："+ order);
    	mmap.put("order", order);
        return "success";
    }
    
    @PostMapping(value = "/createOrder")
    @ResponseBody
    public ResponseResult createOrder(@RequestBody String dataJson){
    	logger.info("请求参数："+dataJson);
    	Map<String, String> paramMap = MapUtil.jsonToMap(dataJson);
    	logger.info("请求参数转换Map："+paramMap);
    	Map<String, String> map = new HashMap<String,String>(); 
    	//商户号
    	map.put("partner_id", CashierConstants.PARTNER_ID);
    	//接口版本
    	map.put("version", CashierConstants.VERSION);
    	//字符编码
    	map.put("_input_charset", CashierConstants.INPUT_CHARSET);
    	//加密方式
    	map.put("sign_type", CashierConstants.SIGN_TYPE);
    	map.put("request_no", paramMap.get("orderId"));
    	map.put("orderid", paramMap.get("orderId"));
    	map.put("amount", paramMap.get("amount"));
    	
    	map.put("product_name", paramMap.get("produceName"));
    	map.put("product_desc", paramMap.get("produceName"));
    	
    	String api_url = "";
    	if("ALIPAY".equals(paramMap.get("payMode"))){
    		//接口名
        	map.put("service", CashierConstants.SERVICE);
    		map.put("product_code", "100001");
    		map.put("instcode", paramMap.get("payMode"));
    		api_url = CashierConstants.CRATE_ORDER_API;
    	}else if("WXPAY".equals(paramMap.get("payMode"))){
    		//接口名
        	map.put("service", CashierConstants.SERVICE);
    		map.put("product_code", "100002");
    		map.put("instcode", paramMap.get("payMode"));
    		api_url = CashierConstants.CRATE_ORDER_API;
    	}else if("UPOP".equals(paramMap.get("payMode"))){
    		//接口名
        	map.put("service", CashierConstants.SERVICE);
    		map.put("product_code", "100003");
    		map.put("instcode", paramMap.get("payMode"));
    		api_url = CashierConstants.CRATE_ORDER_API;
    	}else if("WX_NATIVE_PAY".equals(paramMap.get("payMode"))){
    		//接口名
        	map.put("service", CashierConstants.WEIXIN_SERVICE);
    		map.put("product_code", "100006");
    		map.put("instcode", "WXPAY");
    		map.put("pay_type", "NATIVE");
    		map.put("channel_name", "WX_NATIVE_PAY");
    		api_url = CashierConstants.CRATE_WEIXIN_ORDER_API;
    	}else if("ALI_WAP_PAY".equals(paramMap.get("payMode"))){
    		//接口名
        	map.put("service", CashierConstants.ALI_SERVICE);
    		map.put("product_code", "100007");
    		map.put("instcode", "ALIPAY");
    		map.put("pay_type", "WAP_WAY");
    		map.put("channel_name", "ALI_WAP_PAY");
    		api_url = CashierConstants.CRATE_ALI_ORDER_API;
    	}else if("ALI_APP_PAY".equals(paramMap.get("payMode"))){
    		//接口名
        	map.put("service", CashierConstants.ALI_SERVICE);
    		map.put("product_code", "100009");
    		map.put("instcode", "ALIPAY");
    		map.put("pay_type", "APP_WAY");
    		map.put("channel_name", "ALI_APP_PAY");
    		api_url = CashierConstants.CRATE_ALI_ORDER_API;
    	}else if("ALI_PAGE_PAY".equals(paramMap.get("payMode"))){
    		//接口名
        	map.put("service", CashierConstants.ALI_SERVICE);
    		map.put("product_code", "100008");
    		map.put("instcode", "ALIPAY");
    		map.put("pay_type", "PAGE_WAY");
    		map.put("channel_name", "ALI_PAGE_PAY");
    		api_url = CashierConstants.CRATE_ALI_ORDER_API;
    	}
    	
    	map.put("payment_code", CashierConstants.PAYMENT_CODE);
    	map.put("user_id", CashierConstants.PARTNER_ID);
    	map.put("notify_url", CashierConstants.NOTIFY_URL);
    	//pageUrl
    	map.put("return_url", "http://cashier.yiranpay.xyz/success/"+paramMap.get("orderId"));
    	//获取签名
    	String sign = getSign(JSON.toJSONString(map));
    	map.put("sign", sign);
    	String request_json = JSON.toJSONString(map);
    	logger.info("调聚合支付平台创建订单请求参数：{}",request_json);
    	//http调服务
    	String response_data = WebHttpClient.postRequest(request_json,api_url,HttpEncoding.UTF8, null, null);
    	logger.info("调聚合支付平台创建订单响应结果：{}",response_data);
    	//将结果转换map
    	Map<String, String> resultMap = MapUtil.jsonToMap(response_data);
    	logger.info("调聚合支付平台创建订单响应结果->转换Map对象：{}",resultMap);
    	if("false".equals(resultMap.get("success")) || "500".equals(resultMap.get("code"))){
    		String errorCode = resultMap.get("code");
			String errorMessage = resultMap.get("msg");
			return ResponseResult.error("创建订单失败,错误码：【"+errorCode+"】,错误信息："+errorMessage);
    	}
    	//获取result
    	String resultData = resultMap.get("data");
    	Map<String, String> resultdatamap = MapUtil.jsonToMap(resultData);
    	//验签
    	//获取签名原串
		String sign_src = SignUtils.genSignData(JSON.parseObject(resultData));
		logger.info("【调聚合支付平台创建订单】验签原串:"+sign_src);
		//签名
		boolean sign_flag;
		try {
			sign_flag = RSAUtils.verify(sign_src.getBytes(), CashierConstants.YIRANPAY_PUBLIC_KEY, resultdatamap.get("sign").trim());
			if(sign_flag){//验签通过
				logger.info("【调聚合支付平台创建订单】验签通过");
				if("WX_NATIVE_PAY".equals(paramMap.get("payMode"))){
					String  result = resultdatamap.get("returnCode");
					String payStatus = resultdatamap.get("returnCode");
					if("SUCCESS".equals(payStatus) && "SUCCESS".equals(result)){
						String codeUrl = resultdatamap.get("codeUrl");
						logger.info("【调聚合支付平台创建订单】->返回二维码：{}",codeUrl);
						return ResponseResult.ok(codeUrl);
					}else{
						String errorCode = resultdatamap.get("errorCode");
						String errorMessage = resultdatamap.get("errorMessage");
						return ResponseResult.error("创建订单失败,错误码：【"+errorCode+"】,错误信息："+errorMessage);
					}
				}else{
					String  result = resultdatamap.get("result");
					String payStatus = resultdatamap.get("payStatus");
					if("true".equals(result) && "0001".equals(payStatus)){
						String htmlForm = resultdatamap.get("htmlForm");
						logger.info("【调聚合支付平台创建订单】->返回表单：{}",htmlForm);
						return ResponseResult.ok(htmlForm);
					}else{
						String errorCode = resultdatamap.get("errorCode");
						String errorMessage = resultdatamap.get("errorMessage");
						return ResponseResult.error("创建订单失败,错误码：【"+errorCode+"】,错误信息："+errorMessage);
					}
				}
				
			}else{
				return ResponseResult.error("验签失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseResult.error();
		}
    	
    }

	private String getSign(String jsonString) {
		//获取签名原串
		String sign_src = SignUtils.genSignData(JSON.parseObject(jsonString));
		String sign = null;
		try {
			sign = RSAUtils.sign(sign_src.getBytes(),CashierConstants.PRIVATE_KEY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sign;
	}
	
	@GetMapping("/query")
    public String query(ModelMap mmap) {
        return "query";
    }
	
	
	@PostMapping(value = "/queryOrder")
    @ResponseBody
    public ResponseResult queryOrder(@RequestBody String dataJson){
		ChinaH5PayQueryResponse response = new ChinaH5PayQueryResponse();
    	logger.info("查询订单支付结果请求参数："+dataJson);
    	Map<String, String> paramMap = MapUtil.jsonToMap(dataJson);
    	logger.info("请求参数转换Map："+paramMap);
    	Map<String, String> map = new HashMap<String,String>(); 
    	//商户号
    	map.put("partner_id", CashierConstants.PARTNER_ID);
    	//接口版本
    	map.put("version", CashierConstants.VERSION);
    	//字符编码
    	map.put("_input_charset", CashierConstants.INPUT_CHARSET);
    	//接口名
    	map.put("service", CashierConstants.QUERY_SERVICE);
    	//加密方式
    	map.put("sign_type", CashierConstants.SIGN_TYPE);
    	map.put("request_no", paramMap.get("orderNo"));
    	
    	//获取签名
    	String sign = getSign(JSON.toJSONString(map));
    	map.put("sign", sign);
    	String request_json = JSON.toJSONString(map);
    	logger.info("调聚合支付平台-查询订单支付结果请求参数：{}",request_json);
    	//http调服务
    	String response_data = WebHttpClient.postRequest(request_json,CashierConstants.QUERY_ORDER_API,HttpEncoding.UTF8, null, null);
    	logger.info("调聚合支付平台-查询订单支付结果响应结果：{}",response_data);
    	//将结果转换map
    	Map<String, String> resultMap = MapUtil.jsonToMap(response_data);
    	logger.info("调聚合支付平台-查询订单支付结果应结果->转换Map对象：{}",resultMap);
    	if("false".equals(resultMap.get("success"))){
    		response.setErrorCode(resultMap.get("code"));
			response.setErrorMessage(resultMap.get("msg"));
			response.setPayStatus("F");
    		return ResponseResult.ok(response);
    	}
    	//获取result
    	String resultData = resultMap.get("data");
    	Map<String, String> resultdatamap = MapUtil.jsonToMap(resultData);
    	//验签
    	//获取签名原串
		String sign_src = SignUtils.genSignData(JSON.parseObject(resultData));
		logger.info("【调聚合支付平-查询订单支付结果】验签原串:"+sign_src);
		//签名
		boolean sign_flag;
		try {
			sign_flag = RSAUtils.verify(sign_src.getBytes(), CashierConstants.YIRANPAY_PUBLIC_KEY, resultdatamap.get("sign").trim());
			if(sign_flag){//验签通过
				logger.info("【调聚合支付平台-查询订单支付结果】验签通过");
				String  result = resultdatamap.get("result");
				String payStatus = resultdatamap.get("payStatus");
				if("true".equals(result)){
					
					response.setErrorCode(resultdatamap.get("errorCode"));
					response.setErrorMessage(resultdatamap.get("errorMessage"));
					response.setOuterTradeNo(resultdatamap.get("outerTradeNo"));
					response.setInnerTradeNo(resultdatamap.get("innerTradeNo"));
					response.setPayStatus(payStatus);
					return ResponseResult.ok(response);
				}else{
					String errorCode = resultdatamap.get("errorCode");
					String errorMessage = resultdatamap.get("errorMessage");
					return ResponseResult.ok("订单结果查询失败,错误码：【"+errorCode+"】,错误信息："+errorMessage);
				}
				
			}else{
				return ResponseResult.error("验签失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseResult.error();
		}
    	
    }
}
