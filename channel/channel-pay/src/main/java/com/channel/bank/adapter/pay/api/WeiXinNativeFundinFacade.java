package com.channel.bank.adapter.pay.api;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.channel.bank.adapter.pay.config.WxPayH5Config;
import com.channel.bank.adapter.pay.constants.ReturnCode;
import com.channel.bank.adapter.pay.constants.WXPAYFundChannelKey;
import com.channel.bank.adapter.pay.domain.AmqoRequrst;
import com.channel.bank.adapter.pay.domain.ChannelFundRequest;
import com.channel.bank.adapter.pay.domain.ChannelFundResult;
import com.channel.bank.adapter.pay.domain.ChannelRequest;
import com.channel.bank.adapter.pay.domain.QueryRequest;
import com.channel.bank.adapter.pay.domain.ResultWrapper;
import com.channel.bank.adapter.pay.enums.BestPayTypeEnum;
import com.channel.bank.adapter.pay.enums.FundChannelApiType;
import com.channel.bank.adapter.pay.filedown.WinXinFileDown;
import com.channel.bank.adapter.pay.mock.MockResultData;
import com.channel.bank.adapter.pay.mode.OrderQueryRequest;
import com.channel.bank.adapter.pay.mode.OrderQueryResponse;
import com.channel.bank.adapter.pay.mode.PayFundRequest;
import com.channel.bank.adapter.pay.mode.PayFundResponse;
import com.channel.bank.adapter.pay.property.PropertyHelper;
import com.channel.bank.adapter.pay.service.IAmqpService;
import com.channel.bank.adapter.pay.service.impl.BestPayServiceImpl;
import com.channel.bank.adapter.pay.service.impl.WXPayResultNotifyService;
import com.channel.bank.adapter.pay.utils.JsonUtil;
import com.channel.bank.adapter.pay.utils.StringUtils;
import com.channel.bank.adapter.pay.utils.WXXMLUtil;

/**
 * 原生微信支付
 * @author pandaa
 *
 */
@RestController
@RequestMapping("/api/yiranpay/channelpay/native/wxpay")
public class WeiXinNativeFundinFacade extends BasePayFundinFacade{

	private Logger logger = LoggerFactory.getLogger(WeiXinNativeFundinFacade.class);
	@Autowired
	private PropertyHelper propertyHelper;
	@Autowired
    private BestPayServiceImpl bestPayService;
	@Autowired
	private WXPayResultNotifyService wxPayResultNotifyService;
	@Autowired
	private IAmqpService amqpService;
	
	@Autowired
	private WinXinFileDown winXinFileDown;
	
	@PostMapping("/pay")
	public ResultWrapper<Map<String,Object>>  fundin(@RequestBody String request) {
		logger.info("PayChannelOrder->Channel微信支付渠道请求参数："+request);
		ChannelFundResult result = new ChannelFundResult();
		ChannelFundRequest req = JSON.parseObject(request, ChannelFundRequest.class);
		logger.info("PayChannelOrder->Channel微信支付渠道请求参数转换对象："+req);
		Properties properties = propertyHelper.getProperties(req.getFundChannelCode());
		//判断mock开关是否打开，是否要返回mock数据
        String mock_switch = properties.getProperty(WXPAYFundChannelKey.MOCK_SWITCH);
        if("true".equals(mock_switch)){//开关开启返回mock数据
        	result.setApiType(req.getApiType());
        	result.setRealAmount(req.getAmount());
 			result.setInstOrderNo(req.getInstOrderNo());
 			result.setProcessTime(new Date());
        	result = MockResultData.mockResule(result);
        	logger.info("注意这是mock数据！");
        	return ResultWrapper.ok().putData(result);
        }
        try {
        	initBestPayService(properties);
        	PayFundRequest payRequest = new PayFundRequest();
        	String openId = req.getExtension().get("openId");
            payRequest.setOpenid(openId);
            payRequest.setOrderAmount(req.getAmount().doubleValue());
            payRequest.setOrderId(req.getInstOrderNo());
            String orderName = req.getExtension().get("subject");
            payRequest.setOrderName(orderName);
            String memberId = req.getExtension().get("memberId");
            String payType = req.getExtension().get("payType");
            if("NATIVE".equals(payType)){
            	//NATIVE支付方式
            	payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_NATIVE);
            }else if("WXPAY_H5".equals(payType)){
            	 payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
            }
            logger.info("【微信支付】发起支付, request={}", JsonUtil.toJson(payRequest));

            PayFundResponse payResponse = bestPayService.pay(payRequest);
            logger.info("【微信支付】发起支付,响应结果, response={}", JsonUtil.toJson(payResponse));
            
            if("FAIL".equals(payResponse.getResultCode())){
            	result.setApiResultCode(payResponse.getErrCode());
                result.setApiResultMessage(payResponse.getErrCodeDes());
                result.setResultMessage(payResponse.getReturnMsg());
                result.setSuccess(false);
    			result.setRealAmount(req.getAmount());
    			result.setProcessTime(new Date());
    	    	result.setFundChannelCode(req.getFundChannelCode());
    	    	result.setApiType(FundChannelApiType.DEBIT);
    	    	result.setExtension(JSON.toJSONString(payResponse));
    	    	result.setInstOrderNo(req.getInstOrderNo());
    	    	logger.info("返回支付平台结果:"+JSON.toJSONString(result));
            	return ResultWrapper.ok().putData(result);
            }else{
            	result.setApiResultCode(payResponse.getReturnCode());
                result.setApiResultMessage(payResponse.getReturnMsg());
                result.setResultMessage(payResponse.getReturnMsg());
                result.setSuccess(true);
    			result.setRealAmount(req.getAmount());
    			result.setProcessTime(new Date());
    	    	result.setFundChannelCode(req.getFundChannelCode());
    	    	result.setApiType(FundChannelApiType.DEBIT);
    	    	result.setExtension(JSON.toJSONString(payResponse));
    	    	result.setInstOrderNo(req.getInstOrderNo());
    	    	result.setInstReturnOrderNo(payResponse.getOutTradeNo());
    	    	logger.info("返回支付平台结果:"+JSON.toJSONString(result));
            	return ResultWrapper.ok().putData(result);
            }
        	
        }catch (Exception e) {
        	logger.error("资金源[" + req.getFundChannelCode() + "]支付异常", e);
        	Map<String, String> map = new HashMap<String,String>();
            map.put("fundsChannel", req.getFundChannelCode());
            result.setExtension(JSON.toJSONString(map));
            result = builFalidFundinResponse(req, "支付异常", ReturnCode.FAILED, ReturnCode.FAILED,
                StringUtils.EMPTY_STRING);
            ResultWrapper.error().putData(result);
        }
		return null;
	}

	private void initBestPayService(Properties properties) {
		WxPayH5Config wxPayH5Config = new WxPayH5Config();
		String appId = properties.getProperty(WXPAYFundChannelKey.KEY_WEIXIN_APPID);
		logger.info("【微信支付配置】->【微信appID】："+appId);
		wxPayH5Config.setAppId(appId);
		String appSecret = properties.getProperty(WXPAYFundChannelKey.KEY_WEIXIN_APPSECRET);
		logger.info("【微信支付配置】->【微信秘钥appSecret】："+appSecret);
		wxPayH5Config.setAppSecret(appSecret);
		String mchId = properties.getProperty(WXPAYFundChannelKey.KEY_WEIXIN_MCHID);
		logger.info("【微信支付配置】->【微信商户ID】："+mchId);
		wxPayH5Config.setMchId(mchId);
		String mchSecretKey = properties.getProperty(WXPAYFundChannelKey.KEY_WEIXIN_MCHSECRETKEY);
		logger.info("【微信支付配置】->【微信商户秘钥】："+mchSecretKey);
		wxPayH5Config.setMchKey(mchSecretKey);
		String mchSecretKeyPath = properties.getProperty(WXPAYFundChannelKey.KEY_WEIXIN_MCHSECRETKEYPATH);
		logger.info("【微信支付配置】->【微信商户证书路径】："+mchSecretKeyPath);
		wxPayH5Config.setKeyPath(mchSecretKeyPath);
		String notifyUrl = properties.getProperty(WXPAYFundChannelKey.KEY_WEIXIN_NOTIFYURL);
		logger.info("【微信支付配置】->【异步通知URL】："+notifyUrl);
		wxPayH5Config.setNotifyUrl(notifyUrl);
		bestPayService.setWxPayH5Config(wxPayH5Config);
	}
	
	@PostMapping("/query")
	public ResultWrapper<Map<String,Object>>  query(@RequestBody String request) {
		
		logger.info("PayChannelOrder->Channel微信支付结果查询请求参数："+request);
		ChannelFundResult result = new ChannelFundResult();
		QueryRequest req = JSON.parseObject(request, QueryRequest.class);
		result.setApiType(req.getApiType());
		logger.info("PayChannelOrder->Channel微信支付结果查询请求参数转换对象："+req);
		Properties properties = propertyHelper.getProperties(req.getFundChannelCode());
        try {
        	String mock_switch = properties.getProperty(WXPAYFundChannelKey.MOCK_SWITCH);
            if("true".equals(mock_switch)){//开关开启返回mock数据
            	result.setFundChannelCode(req.getFundChannelCode());
    			result.setInstOrderNo(req.getInstOrderNo());
    			result.setSuccess(true);
    			result.setApiType(req.getApiType());
    			result.setRealAmount(req.getAmount());
    			result.setInstOrderNo(req.getInstOrderNo());
    			result.setApiResultCode("0000");
    			result.setApiResultSubCode("SUCCESS");
    			result.setApiResultMessage("注意：当前为mock数据！：查询成功");
    			result.setResultMessage("注意：当前为mock数据！：交易成功");
    			result.setApiResultSubMessage("注意：当前为mock数据！：交易成功");
            	logger.info("注意这是mock数据！");
            	return ResultWrapper.ok().putData(result);
            }
        	initBestPayService(properties);
        	OrderQueryRequest queryRequest = new OrderQueryRequest();
        	queryRequest.setOrderId(req.getInstOrderNo());
        	queryRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        	OrderQueryResponse queryResult = bestPayService.query(queryRequest);
        	logger.info("微信平台返回结果对象:"+JSON.toJSONString(queryResult));
        	if("FAIL".equals(queryResult.getResultCode())){
        		result.setFundChannelCode(req.getFundChannelCode());
        		result.setInstOrderNo(req.getInstOrderNo());
        		result.setApiResultCode(queryResult.getErrCode());
            	result.setRealAmount(req.getAmount());
            	result.setApiResultMessage(queryResult.getErrCodeDes());
    			result.setResultMessage(queryResult.getErrCodeDes());
            	result.setSuccess(false);
            	result.setExtension(JSON.toJSONString(queryResult));
            	logger.info("查询响应结果:"+JSON.toJSONString(result));
            	return ResultWrapper.ok().putData(result);
            }else{
            	if("SUCCESS".equals(queryResult.getResultCode()) && "SUCCESS".equals(queryResult.getTradeState())){
                	result.setFundChannelCode(req.getFundChannelCode());
                	result.setInstOrderNo(req.getInstOrderNo());
                	result.setApiResultCode(queryResult.getResultCode());
                	result.setRealAmount(req.getAmount());
                	result.setApiResultSubCode(queryResult.getTradeState());
                	result.setResultMessage(queryResult.getReturnMsg());
                	result.setApiResultMessage(queryResult.getReturnMsg());
                	result.setApiResultSubMessage(queryResult.getTradeStateDesc());
                	result.setSuccess(true);
                	result.setInstReturnOrderNo(queryResult.getTransactionId());
                	result.setExtension(JSON.toJSONString(queryResult));
                	logger.info("查询响应结果:"+JSON.toJSONString(result));
                	return ResultWrapper.ok().putData(result);
                }else{
                	result.setFundChannelCode(req.getFundChannelCode());
                	result.setInstOrderNo(req.getInstOrderNo());
                	result.setApiResultCode(queryResult.getResultCode());
                	result.setRealAmount(req.getAmount());
                	result.setApiResultSubCode(queryResult.getTradeState());
                	result.setResultMessage(queryResult.getReturnMsg());
                	result.setApiResultMessage(queryResult.getReturnMsg());
                	result.setApiResultSubMessage(queryResult.getTradeStateDesc());
                	result.setSuccess(true);
                	result.setInstReturnOrderNo(queryResult.getTransactionId());
                	result.setExtension(JSON.toJSONString(queryResult));
                	logger.info("查询响应结果:"+JSON.toJSONString(result));
                	return ResultWrapper.ok().putData(result);
                }
            }
        }catch (Exception ex) {
            logger.error("查询异常", ex);
            result = buildFaildChannelFundResult("签约支付异常", ReturnCode.FAILED, FundChannelApiType.SINGLE_QUERY);
           return ResultWrapper.error().putData(result);
        }
	}
	
	
	@PostMapping("/downloadBill")
	public ResultWrapper<Map<String,Object>>  downloadBill(@RequestBody String request) {
		logger.info("PayChannelOrder->Channel微信支付账单请求参数："+request);
		ChannelFundResult result = new ChannelFundResult();
		ChannelFundRequest req = JSON.parseObject(request, ChannelFundRequest.class);
		logger.info("PayChannelOrder->Channel微信支付账单渠道请求参数转换对象："+req);
		Properties properties = propertyHelper.getProperties(req.getFundChannelCode());
      //判断mock开关是否打开，是否要返回mock数据
        String mock_switch = properties.getProperty(WXPAYFundChannelKey.MOCK_SWITCH);
        if("true".equals(mock_switch)){//开关开启返回mock数据
        	result.setApiType(req.getApiType());
        	result.setRealAmount(req.getAmount());
 			result.setInstOrderNo(req.getInstOrderNo());
 			result.setProcessTime(new Date());
        	result = MockResultData.mockResule(result);
        	logger.info("注意这是mock数据！");
        	return ResultWrapper.ok().putData(result);
        }
        try {
        	Map<String, String> extension = req.getExtension();
        	/***配置文件中/ ***/
        	String bill_dowload_url = properties.getProperty(WXPAYFundChannelKey.KEY_BILL_DOWLOAD_URL);
        	logger.info("【微信支付配置】->【对账单下载】："+bill_dowload_url);
        	String appId = properties.getProperty(WXPAYFundChannelKey.KEY_WEIXIN_APPID);
     		logger.info("【微信支付配置】->【微信appID】："+appId);
     		String mchId = properties.getProperty(WXPAYFundChannelKey.KEY_WEIXIN_MCHID);
     		logger.info("【微信支付配置】->【微信商户ID】："+mchId);
     		String appSecret = properties.getProperty(WXPAYFundChannelKey.KEY_WEIXIN_MCHSECRETKEY);
     		logger.info("【微信支付配置】->【微信商户秘钥】："+appSecret);
     		String mchSecretKeyPath = properties.getProperty(WXPAYFundChannelKey.KEY_WEIXIN_MCHSECRETKEYPATH);
     		logger.info("【微信支付配置】->【微信商户证书路径】："+mchSecretKeyPath);
     		String billType = properties.getProperty(WXPAYFundChannelKey.KEY_WEIXIN_BILL_TYPE);
     		// 对账类型： ALL，返回当日所有订单信息，默认值 SUCCESS，返回当日成功支付的订单  REFUND，返回当日退款订单
     		logger.info("【微信支付配置】->【微信对账类型】："+billType);
     		String billDirPath = properties.getProperty(WXPAYFundChannelKey.KEY_BILL_DIR_PATH);
     		logger.info("【微信支付配置】->【对账文件路径】："+billDirPath);
     		
     		Map<String,String> map = new HashMap<String,String>();
     		map.put("bill_dowload_url", bill_dowload_url);
     		map.put("billDate", extension.get("billDate"));
     		map.put("billDirPath", billDirPath);
     		map.put("bill_type", billType);
     		map.put("appid", appId);
     		map.put("mch_id", mchId);
     		map.put("appSecret", appSecret);
     		
     		File file = winXinFileDown.fileDown(map);
     		result.setSuccess(true);
     		Map<String, String> extensionMap = new HashMap<String, String>();
     		String bill_file = file.getCanonicalPath();
     		extensionMap.put("bill_file", bill_file);
     		result.setExtension(JSON.toJSONString(extensionMap));
     		result.setFundChannelCode(req.getFundChannelCode());
        	result.setInstOrderNo(req.getInstOrderNo());
        	result.setApiResultCode("0000");
        	result.setRealAmount(req.getAmount());
        	result.setResultMessage("对账文件下载成功");
        	result.setApiResultMessage("对账文件下载成功");
        	result.setSuccess(true);
        	return ResultWrapper.ok().putData(result);
        }catch (Exception e) {
        	logger.error("资金源[" + req.getFundChannelCode() + "]账单下载异常", e);
        	Map<String, String> map = new HashMap<String,String>();
            map.put("fundsChannel", req.getFundChannelCode());
            result.setExtension(JSON.toJSONString(map));
            result = builFalidFundinResponse(req, "账单下载异常", ReturnCode.FAILED, ReturnCode.FAILED,
                StringUtils.EMPTY_STRING);
            ResultWrapper.error().putData(result);
        }
		return null;
	}
	
	

	/**
     * 返回接口操作信息
     * @param request
     * @return
     */
    protected String getInfo(ChannelRequest request){
    	StringBuffer sb = new StringBuffer();
    	sb.append("FundChannelApi=").append(request.getFundChannelCode())
    	.append("-").append(request.getApiType().getCode())
    	.append(",InstOrderNo=").append(request.getInstOrderNo());
    	return sb.toString();
	}
    
    
    /**
     * 构造查询错误的返回响应
     * 
     * @param apiResultMessage  api结果码说明
     * @param apiResultCode  api结果码
     * @param apiType   api类型
     * @return
     */
    public static ChannelFundResult buildFaildChannelFundResult(String apiResultMessage, String apiResultCode,
                                                                FundChannelApiType apiType) {
        ChannelFundResult response = new ChannelFundResult();
        response.setApiType(apiType);
        response.setApiResultCode(apiResultCode);
        response.setApiResultMessage(apiResultMessage);
        response.setProcessTime(new Date());
        response.setSuccess(false);
        return response;
    }
    
    @PostMapping("/notify/{fundChannelCode}")
	public Object  notify(@PathVariable("fundChannelCode") String fundChannelCode,@RequestBody String data) {
    	logger.info("通知数据："+data);
    	Map<String, String> xmlToMap = WXXMLUtil.xmlToMap(data);
    	logger.info("fundChannelCode："+fundChannelCode);
    	ChannelRequest channelRequest = new ChannelRequest();
    	channelRequest.setFundChannelCode(fundChannelCode);
    	channelRequest.setApiType(FundChannelApiType.DEBIT);
    	channelRequest.getExtension().put("notifyMsg", data);
    	ChannelFundResult result = wxPayResultNotifyService.notify(channelRequest);
    	//调用发送MQ消息，更新订单状态
    	Map<String,Object> map = new HashMap<String,Object>();
		map.put("message", result);
		//消息被序列化后发送
		AmqoRequrst requrst = new AmqoRequrst();
    	requrst.setExchange("exchange.payresult.process");
    	requrst.setRoutingKey("key.payresult.process");
    	requrst.setMap(map);
    	logger.info("发送MQ消息:"+JSON.toJSONString(requrst));
		amqpService.sendMessage(requrst);
		logger.info("MQ消息发送完毕");
        //通知业务系统
        //resultNotifyFacade.notifyBiz(instOrderResult.getInstOrderNo(),xmlToMap);
        String return_result = setXml("SUCCESS", "OK");
        return return_result;
    }
    
    /**
     * 通过xml 发给微信消息
     * @param returnCode
     * @param returnMsg
     * @return
     */
  	public static String setXml(String returnCode, String returnMsg) {
  		SortedMap<String, String> parameters = new TreeMap<String, String>();
  		parameters.put("return_code", returnCode);
  		parameters.put("return_msg", returnMsg);
  		return "<xml><return_code><![CDATA[" + returnCode + "]]>" + 
  				"</return_code><return_msg><![CDATA[" + returnMsg + "]]></return_msg></xml>";
  	}
}
