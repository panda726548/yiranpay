package com.yiranpay.cashier.constants;

public class CashierConstants {
	
	/**
	 * 创建订单请求地址
	 */
	public static final String CRATE_ORDER_API ="http://127.0.0.1:8088/api/yiranpay/gateway/chinaH5Pay/createPay";
	public static final String QUERY_ORDER_API ="http://127.0.0.1:8088/api/yiranpay/gateway/chinaH5Pay/queryPay";
	/**
	 * 原生微信支付
	 */
	public static final String CRATE_WEIXIN_ORDER_API ="http://127.0.0.1:8088/api/yiranpay/gateway/weixinPay/createPay";
	
	public static final String CRATE_ALI_ORDER_API = "http://127.0.0.1:8088/api/yiranpay/gateway/alipay/createPay";
	/**
	 * 异步通知地址
	 */
	public static final String NOTIFY_URL ="http://api.yiranpay.xyz/api/yiranpay/channelpay/notify/resultNotify";
	/**
	 * 商户号
	 */
	public static final String PARTNER_ID = "200000000003";
	/**
	 * 接口版本
	 */
	public static final String VERSION = "1.0";
	/**
	 * 编码
	 */
	public static final String INPUT_CHARSET = "UTF-8";
	/**
	 * 接口名称
	 */
	public static final String SERVICE = "china_wx_ali_pay";
	public static final String WEIXIN_SERVICE = "weixin_pay";
	public static final String QUERY_SERVICE = "china_wx_ali_query_pay";
	public static final String ALI_SERVICE = "ali_pay";
	/**
	 * 平台公钥
	 */
	public static final String YIRANPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCxBJutehp6I9/0fRrvCYz8ITLToYDo6bt70mIUaZ3NRj76ftivkV+juvCYV9LoJmaGtucI63LSYpHi1PsW3KDAt0j6WAqoTSfIctJO43A/LfQhsYgbcbetx42AzcHj6Vfc9j9PHA6P8XrB9+272W6XRQ5eZBfZrjfOzqJr+lDkOQIDAQAB";
	/**
	 * 私钥
	 */
	public static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJcgRTViViTLJSFqu2Kv1zwcWJHCh3G5VSatob2peose5RKwJvc/UFkNWeenkjIuHlu2w48KV1EwRvbbsVH0JjwLKHLmk5C+lLNjk22RPOyZ3LVanpj4KnXns/UKQss68p8eDVbWona6GorGoyBYCjLABPzBzMjqixYQNrcKo0ELAgMBAAECgYBv+3eNESrn5KfE1UO44rdIUXDHwS9/82bHXE3HLjghsmjnqiOVfhLZie0enKrWCBpyt0C9U1pUTMOo1C3WnmVdYODOtNJdernkRjWVsVkjXaHroEK1hYUZEO8JG3CwZDjg1CliPBdY1JYCvy1OkWWPyGTQ0Zg6AeauWEmLNTBxwQJBAMzuiGWVcnMIJvinXrs5gmJNFidaBmc8gEJZuJpLxcnYjVLSljAD4eQVgCl5YvwH1o7LeBB4ZByobVXeoj1w0LECQQC8yT4n4CyLNlo11nSVboUOVz2ZxyFiKBIaFiXR1ATF1L4FwPnp82AsR6g1L3yxsXzFZV8qzZx+LMw4yh6SPbx7AkEApZXPTBV3Ro89vgiuuKT9PyR+VAmfxubR08rfKH01MaTyfAuAIwfgDAV7aweT/YTvzq2FM/mzGpzUy196xB5V4QJBALO8OUQHpwEHR024lphqd51Wq9xLfNoR4mIZsfoajEy5Jg0eVBFL18Vwl5HDW0ll/K+GuttqlFKAXhK3Q/Hhex8CQC4WxGSC6f/UdI1+C+8Xdz7T/h1v6WkioO6qtdpfqxwuSrtFSiy6wIvRYO4BbFzZUCRG5bH1MzMYvq9klYXAkGw=";
	/**
	 * 加密方式
	 */
	public static final String SIGN_TYPE = "RSA";
	/**
	 * 产品编码
	 */
	public static final String PRODUCT_CODE = "10001";
	/**
	 * 支付编码
	 */
	public static final String PAYMENT_CODE = "10001";
	
	
}
