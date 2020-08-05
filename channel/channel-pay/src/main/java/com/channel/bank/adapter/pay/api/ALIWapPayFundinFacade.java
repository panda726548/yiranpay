package com.channel.bank.adapter.pay.api;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.channel.bank.adapter.pay.constants.ALIPayConstant;
import com.channel.bank.adapter.pay.constants.ReturnCode;
import com.channel.bank.adapter.pay.constants.WXPAYFundChannelKey;
import com.channel.bank.adapter.pay.domain.AmqoRequrst;
import com.channel.bank.adapter.pay.domain.ChannelFundRequest;
import com.channel.bank.adapter.pay.domain.ChannelFundResult;
import com.channel.bank.adapter.pay.domain.ChannelRequest;
import com.channel.bank.adapter.pay.domain.QueryRequest;
import com.channel.bank.adapter.pay.domain.ResultWrapper;
import com.channel.bank.adapter.pay.enums.FundChannelApiType;
import com.channel.bank.adapter.pay.mock.MockResultData;
import com.channel.bank.adapter.pay.property.PropertyHelper;
import com.channel.bank.adapter.pay.service.IAmqpService;
import com.channel.bank.adapter.pay.service.impl.ALIPayResultNotifyService;
import com.channel.bank.adapter.pay.utils.ChannelFundResultUtil;
import com.channel.bank.adapter.pay.utils.MapUtils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
/**
 * 支付宝支付
 * @author pandaa
 *
 */
@RestController
@RequestMapping("/api/yiranpay/channelpay/aliwappay")
public class ALIWapPayFundinFacade extends BasePayFundinFacade {

	private Logger logger = LoggerFactory.getLogger(ALIWapPayFundinFacade.class);
	
	@Autowired
	private PropertyHelper propertyHelper;
	
	@Autowired
	private IAmqpService amqpService;
	
	@Autowired
	private ALIPayResultNotifyService aliPayResultNotifyService;
	/**
	 * 支付下单
	 * @param request
	 * @return
	 */
	@PostMapping("/pay")
	public ResultWrapper<Map<String,Object>>  fundin(@RequestBody String request) {
		logger.info("PayChannelOrder->Channel支付宝支付渠道请求参数："+request);
		ChannelFundResult result = new ChannelFundResult();
		ChannelFundRequest req = JSON.parseObject(request, ChannelFundRequest.class);
		logger.info("PayChannelOrder->Channel支付宝支付渠道请求参数转换对象："+req);
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
            String subject = extension.get("subject");
    		BigDecimal amount = req.getAmount();
    		String totalAmount = amount.toString();
    		String orderNo = req.getInstOrderNo();
            String notifyUrl = properties.getProperty(ALIPayConstant.PAY_NOTIFY_URL);
            String returnUrl = extension.get(ALIPayConstant.RETURN_URL);
            //获取网关地址
            String gateway = properties.getProperty(ALIPayConstant.GATEWAY);
            //商户应用ID
            String appId = properties.getProperty(ALIPayConstant.APPID);
            //商户私钥
            String privateKey = properties.getProperty(ALIPayConstant.PRIVATE_KEY);
            //支付宝公钥
            String publicKey = properties.getProperty(ALIPayConstant.PUBLIC_KEY);
            //字符编码
            String charset = properties.getProperty(ALIPayConstant.CHARSET);
            //报文格式
            String format = properties.getProperty(ALIPayConstant.FORMAT);
            //签名方式
            String signType = properties.getProperty(ALIPayConstant.SIGNTYPE);
            AlipayClient client = getAlipayClient(gateway,appId,privateKey,publicKey,charset,format,signType);
            
            AlipayTradeWapPayRequest alipayTradeWapPayRequest = new AlipayTradeWapPayRequest();
            alipayTradeWapPayRequest.setNotifyUrl(notifyUrl);
            alipayTradeWapPayRequest.setReturnUrl(returnUrl);

            AlipayTradeWapPayModel alipayTradeWapPayModel = new AlipayTradeWapPayModel();
            alipayTradeWapPayModel.setOutTradeNo(orderNo);
            alipayTradeWapPayModel.setSubject(subject);
            alipayTradeWapPayModel.setTotalAmount(totalAmount);
            
            alipayTradeWapPayModel.setProductCode("QUICK_WAP_WAY");

            alipayTradeWapPayRequest.setBizModel(alipayTradeWapPayModel);

            AlipayTradeWapPayResponse alipayTradeWapPayResponse = client.pageExecute(alipayTradeWapPayRequest);
            logger.info("支付宝支付响应参数：{}", JSONUtil.toJsonStr(alipayTradeWapPayResponse));
            if (alipayTradeWapPayResponse.isSuccess()) {
            	logger.info("body:"+alipayTradeWapPayResponse.getBody());
            	result.setApiResultMessage("订单创建成功，等待买家付款");
    			result.setResultMessage("订单创建成功，等待买家付款");
    			result.setApiResultCode("0");
    			result.setInstOrderNo(req.getInstOrderNo());
    			result.setRealAmount(req.getAmount());
    			result.setApiType(FundChannelApiType.SIGN);
    			result.setSuccess(true);
    			result.setFromHtml(alipayTradeWapPayResponse.getBody());
    			result.setExtension(JSON.toJSONString(alipayTradeWapPayResponse));
                return ResultWrapper.ok().putData(result);
            } else {
            	result.setApiResultMessage("调用支付宝支付失败");
    			result.setResultMessage("调用支付宝支付失败");
    			result.setApiResultCode("-1");
    			result.setInstOrderNo(req.getInstOrderNo());
    			result.setRealAmount(req.getAmount());
    			result.setApiType(FundChannelApiType.SIGN);
    			result.setSuccess(false);
                return ResultWrapper.ok().putData(result);
            }
			
        } catch (Exception e) {
        	logger.error("构造表单异常：", e);
            result.setResultMessage(e.getMessage());
            result.setSuccess(false);
			result.setApiResultCode(ReturnCode.EXCEPTION);
            result.setApiResultMessage("调用支付宝支付接口异常");
        }
        return ResultWrapper.ok().putData(result);
       
    }
	
	
	/**
	 * 单笔结果查询
	 * @param requestJson
	 * @return
	 */
	@PostMapping("/query")
	public ResultWrapper<Map<String,Object>>  query(@RequestBody String requestJson) {
		
		logger.info("PayChannelOrder->Channel支付宝支付结果查询请求参数："+requestJson);
		ChannelFundResult result = new ChannelFundResult();
		QueryRequest request = JSON.parseObject(requestJson, QueryRequest.class);
		result.setApiType(request.getApiType());
		logger.info("PayChannelOrder->Channel支付宝支付结果查询请求参数转换对象："+request);
		Properties properties = propertyHelper.getProperties(request.getFundChannelCode());
        result.setSuccess(false);
        result.setFundChannelCode(result.getFundChannelCode());
        String operInfo = getInfo(request);
        try {
        	//获取网关地址
            String gateway = properties.getProperty(ALIPayConstant.GATEWAY);
            //商户应用ID
            String appId = properties.getProperty(ALIPayConstant.APPID);
            //商户私钥
            String privateKey = properties.getProperty(ALIPayConstant.PRIVATE_KEY);
            //支付宝公钥
            String publicKey = properties.getProperty(ALIPayConstant.PUBLIC_KEY);
            //字符编码
            String charset = properties.getProperty(ALIPayConstant.CHARSET);
            //报文格式
            String format = properties.getProperty(ALIPayConstant.FORMAT);
            //签名方式
            String signType = properties.getProperty(ALIPayConstant.SIGNTYPE);
            AlipayClient client = getAlipayClient(gateway,appId,privateKey,publicKey,charset,format,signType);

            AlipayTradeQueryRequest alipayTradeQueryRequest = new AlipayTradeQueryRequest();

            AlipayTradeQueryModel alipayTradeQueryModel = new AlipayTradeQueryModel();
            alipayTradeQueryModel.setOutTradeNo(request.getInstOrderNo());
            //alipayTradeQueryModel.setTradeNo(tradeNo);

            alipayTradeQueryRequest.setBizModel(alipayTradeQueryModel);

            AlipayTradeQueryResponse alipayTradeQueryResponse = client.execute(alipayTradeQueryRequest);
            logger.info("支付宝交易查询响应参数：{}", JSONUtil.toJsonStr(alipayTradeQueryResponse));
            if (alipayTradeQueryResponse.isSuccess()) {
                return ResultWrapper.ok().putData((JSONUtil.formatJsonStr(alipayTradeQueryResponse.getBody())));
            }
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(operInfo+"查询异常", e);
            return ResultWrapper.error().putData(buildFaildQueryResponse("查询异常",ReturnCode.EXCEPTION));
		}
		return ResultWrapper.ok().putData(result);
	}
	
	/**
	 * 支付异步通知
	 * @param fundChannelCode
	 * @param data
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws AlipayApiException 
	 */
	@PostMapping("/notify/{fundChannelCode}")
	public Object  notify(@PathVariable("fundChannelCode") String fundChannelCode,@RequestBody String data) throws UnsupportedEncodingException, AlipayApiException {
    	logger.info("支付宝异步通知回调参数："+data);
    	if (StringUtils.isBlank(data)) {
            return "fail";
        }
    	Properties properties = propertyHelper.getProperties(fundChannelCode);
    	data = URLDecoder.decode(data, "UTF-8"); 
    	Map<String, String> dataToMap = MapUtils.getMapforUrl(data);
    	logger.info("支付宝异步通知回调参数-转换Map对象："+dataToMap);
    	logger.info("fundChannelCode："+fundChannelCode);
    	//支付宝公钥
        String publicKey = properties.getProperty(ALIPayConstant.PUBLIC_KEY);
        logger.info("支付宝公钥："+publicKey);
        //字符编码
        String charset = properties.getProperty(ALIPayConstant.CHARSET);
        //签名方式
        String signType = properties.getProperty(ALIPayConstant.SIGNTYPE);
    	/*boolean signResult = AlipaySignature.rsaCheckV1(dataToMap, publicKey,charset, signType);
    	if (!signResult) {
    		String sWord = AlipaySignature.getSignCheckContentV2(dataToMap);
            logger.info("验签失败：签名前构建验签字符串为：{}", sWord);
            return sWord;
    	}*/
    	ChannelRequest channelRequest = new ChannelRequest();
    	channelRequest.setFundChannelCode(fundChannelCode);
    	channelRequest.setApiType(FundChannelApiType.VERIFY_SIGN);
    	channelRequest.getExtension().put("notifyMsg", JSON.toJSONString(dataToMap));
    	ChannelFundResult result = aliPayResultNotifyService.aliNotify(channelRequest);
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
		//给响应
		String responseData ="success"; 
        return responseData;
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
     * @param apiResultMessage
     * @param apiResultCode
     * @return
     */
    protected ChannelFundResult buildFaildQueryResponse(String apiResultMessage, String apiResultCode) {
        return ChannelFundResultUtil.buildFaildChannelFundResult(apiResultMessage, apiResultCode, FundChannelApiType.SINGLE_QUERY);
    }
}
