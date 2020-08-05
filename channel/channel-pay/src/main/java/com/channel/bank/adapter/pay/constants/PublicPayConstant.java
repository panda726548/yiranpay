package com.channel.bank.adapter.pay.constants;
/**
 * 常量
 * @author xu pan
 *
 */
public class PublicPayConstant {

	//签约mock开关
	public static final String MOCK_SWITCH = "MOCK_SWITCH";	
	//消息来源
	public static final String MSG_SRC = "MSG_SRC";	
	//消息类型 WXPay.jsPay :微信公众号支付 trade.jsPay :支付宝  acp.jsPay :银联云闪付
	public static final String MSG_TYPE = "MSG_TYPE";
	//商户号
	public static final String MID = "MID";	
	//终端号
	public static final String TID = "TID";	
	//业务类型 
	public static final String INSTMID = "INSTMID";	
	//签名类型
	public static final String SIGN_TYPE = "SIGN_TYPE";	
	//下单URL
	public static final String APIURL_MAKEORDER = "APIURL_MAKEORDER";	
	//来源编号
	public static final String MSG_SRC_ID = "MSG_SRC_ID";
	//通知地址
	public static final String REFUND_NOTIFY_URL = "REFUND_NOTIFY_URL";
	public static final String PAY_NOTIFY_URL = "PAY_NOTIFY_URL";
	//网页跳转地址
	public static final String RETURNURL = "RETURNURL";
	//MD5加密秘钥
	public static final String MD5KEY = "MD5KEY";
	//查询交易接口 退款接口  退款查询
	public static final String APIURL = "APIURL";
	
	public static final String PAY_RESULT_SCCUSS = "TRADE_SUCCESS";	
	public static final String RFPAY_RESULT_SCCUSS = "TRADE_REFUND";
	public static final String RETURN_URL = "returnUrl";	
	
}
