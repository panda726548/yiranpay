package com.yiranpay.gateway.constant;


/**
 *
 * <p>定义请求的参数名称</p>
 */
public class ReqConstant {

    //基础参数
    public static final String SERVICE              = "service";
    public static final String PARNTER_ID           = "partner_id";
    public static final String INPUT_CHARSET        = "_input_charset";
    public static final String SIGN                 = "sign";
    public static final String SIGN_TYPE            = "sign_type";
    public static final String RETURN_URL           = "return_url";
    public static final String MEMO                 = "memo";
    public static final String VERSION              = "version";

    //业务参数
    public static final String OUTER_TRADE_NO       = "outer_trade_no";
    public static final String SUBJECT              = "subject";
    public static final String PRICE                = "price";
    public static final String QUANTITY             = "quantity";
    public static final String TOTAL_AMOUNT         = "total_amount";
    public static final String SELLER_ID            = "seller_id";
    public static final String SELLER_ID_TYPE       = "seller_id_type";
    public static final String ROYALTY_PARAMETERS   = "royalty_parameters";
    public static final String BODY                 = "body";
    public static final String SHOW_URL             = "show_url";
    public static final String GOLD_COIN            = "gold_coin";
    public static final String DEPOSIT_AMOUNT       = "deposit_amount";
    public static final String DEPOSIT_NO           = "deposit_no";
    public static final String SUBMIT_TIME          = "submit_time";
    public static final String NOTIFY_URL           = "notify_url";
    public static final String OPERATOR_ID          = "operator_id";
    public static final String BUYER_ID             = "buyer_id";
    public static final String BUYER_ID_TYPE        = "buyer_id_type";
    public static final String BUYER_IP             = "buyer_ip";
    public static final String PAY_METHOD           = "pay_method";
    public static final String DEFAULT_LOGIN        = "default_login";

    public static final String IDENTITY_NO          = "identity_no";
    public static final String IDENTITY_TYPE        = "identity_type";
    public static final String WITHDRAWAL_AMOUNT    = "withdrawal_amount";
    public static final String PAYABLE_AMOUNT       = "payable_amount";
    public static final String ACCOUNT_TYPE         = "account_type";
    public static final String FUNDOUT_GRADE        = "fundout_grade";
    public static final String PURPOSE              = "purpose";
    public static final String CARD_NO              = "card_no";
    public static final String ACCOUNT_NAME         = "account_name";
    public static final String BANK_CODE            = "bank_code";
    public static final String BANK_NAME            = "bank_name";
    public static final String BRANCH_NAME          = "branch_name";
    public static final String BANK_LINE_NO         = "bank_line_no";
    public static final String BANK_PROV            = "bank_prov";
    public static final String BANK_CITY            = "bank_city";
    public static final String COMPANY_OR_PERSONAL  = "company_or_personal";
    public static final String TRADE_TYPE           = "trade_type";
    public static final String TRANSFER_AMOUNT      = "transfer_amount";
    public static final String PAYEE_IDENTITY_NO    = "payee_identity_no";
    public static final String PAYEE_NAME    		= "payee_name";
    public static final String PAYEE_IDENTITY_TYPE  = "payee_identity_type";
    public static final String SEND_MESSAGE         = "send_message";
    public static final String BATCH_NO             = "batch_no";
    public static final String TRANSFER_NUM         = "transfer_num";
    public static final String TRANSFER_LIST        = "transfer_list";
    public static final String REFUND_NUM         = "refund_num";
    public static final String REFUND_LIST        = "refund_list";
    public static final String TRANSFER_DESC        = "transfer_desc";
    public static final String PLAT_USER_ID          ="plat_user_id";
    public static final String PLAT_USR             ="plat_user";
    public static final String MEMBER_ID            ="member_id";
    public static final String TOKEN_NO             ="token";
    public static final String COMPAY_NAME          ="compay_name";
    public static final String CHECK_FLAG           ="check_flag";
    public static final String ACCOUNT_ID           ="account_id";
    public static final String ACCREDIT_TYPE        ="accredit_type";
    public static final String PASSWORD             ="password";
    public static final String PASSWORD2             ="password2";
    
	public static final String MANAGEUSER           ="manageUser";
    public static final String OPERATORID           ="operator";
    public static final String OPERATOR_LOGINNAME   ="operator_loginName";
    public static final String MCRYPT_KEY           ="mcryptkey";
    public static final String MCRYPT_KEY2           ="mcryptkey2";
	public static final String ENSURE_AMOUNT        = "ensure_amount";
    //验证身份证信息请求参数
	public static final String REAL_NAME            ="real_name";
	
	public static final String IDENTITYCARD         ="identitycard";
	
    public static final String OUTER_PAY_NO         = "outer_pay_no";

    public static final String ORIG_OUTER_TRADE_NO  = "orig_outer_trade_no";

    public static final String OUTER_TRADE_NO_LIST  = "outer_trade_no_list";

    public static final String AMOUNT               = "amount";
    //银行代扣请求参数
    public static final String USER_NAME            = "user_name";
    public static final String CERT_TYPE            = "certificates_type";
    public static final String CERT_NUM             = "certificates_number";
    public static final String PAY_AMOUNT           = "payable_amount";
    public static final String WITHHOLDING_NUM      = "withholding_num";
    public static final String WITHHOLDING_AMOUNT   = "withholding_amount";
    public static final String WITHHOLDING_LIST     = "withholding_list";
    
    //查询账户余额
    public static final String FUN_ACT_TYPE         ="fun_act_type";
    public static final String QUERY_TYPE           ="query_type";
    //登帐请求参数
    public static final String ORDER_NO             ="order_no";
    public static final String ORDER_AMT            ="order_amt";
    //批次结果查询请求参数
    public static final String BATCHNO              ="batchno";
    public static final String START_DT             ="start_dt";
    public static final String END_DT               ="end_dt";
    //充值记录查询请求参数
    public static final String RECHARGE_DATE        ="recharge_date";
    public static final String PAYEE_ID             = "payee_id";
    public static final String PAYEE_ID_TYPE        = "payee_id_type";
    public static final String CALLBACK_URL         = "callback_url";
    public static final String REFUND_AMOUNT        = "refund_amount";
    public static final String REFUND_ENSURE_AMOUNT = "refund_ensure_amount";
    public static final String PREPAY_LIST          = "prepay_list";
    public static final String START_TIME           = "start_time";
    public static final String END_TIME             = "end_time";
    public static final String PAGE_NO              = "page_no";
    public static final String PAGE_SIZE            = "page_size";
    public static final String INNRE_TRADE_NO       = "inner_trade_no";
    public static final String REASON               = "reason";
    public static final String SEP                  = "\\^";
    public static final String REFERER              = "referer";
    public static final String USER_AGENT           = "USER-AGENT";
    public static final String USERAGENT            = "userAgent";
    public static final String REQUEST_NO           = "request_no";
    public static final String TRADE_LIST           = "trade_list";
    public static final String ACCESS_CHANNEL_WEB   = "WEB";
    public static final String ACCESS_CHANNEL_WAP  = "WAP";

    public static final String EXT1                 = "ext1";
    public static final String EXT2                 = "ext2";
    public static final String GO_CASHIER           = "go_cashier";
    public static final String STATUS               = "status";

    // 风控固定值
    public static final String SARS_CP              = "CP101";
    public static final String GATE_WAY             = "GATEWAY";

    // 支付扩展信息
    public static final String PAYER_IP             = "payerIp";
    public static final String WEB_DEMAIN           = "webDomain";

    public static final String MERCHANT_SHOP_NAME   = "merchant_shop_name";
    // 默认的日期格式
    public static final String DEFAULT_DATE_FORMATE = new String("yyyyMMddHHmmss");

    // 截至到天的日期格式
    public static final String DATE_FORMATE_AS_DAY = new String("yyyyMMdd");

    /** 真:标记 */
    public static final String TRUE_STRING          = "Y";
    /** 假:标记 */
    public static final String FALSE_STRING         = "N";

	public static final Object SETTLE_DATE_DESC = "settle_date";
	public static final Object TRADE_TYPE_DESC = "trade_type";
	
	public static final String START_DATE = "start_date";
	public static final String END_DATE = "end_date";
	
	/** 银行表单编码 */
	public static final Object EBANK_CHARSET = "ebankCharset";
	
	//快捷网关
	public static final String BUSS_TYPE              = "buss_type";
	public static final String CARD_TYPE              = "card_type";
	public static final String USER_ID              = "user_id";
	public static final String INSTCODE              = "instcode";
	public static final String CERTTYPE              = "certtype";
	public static final String CERTNO              = "certno";
	public static final String NAME              = "name";
	public static final String MOBILE              = "mobile";
	public static final String CVV2              = "cvv2";
	public static final String VALIDATEDATE              = "validatedate";
	public static final String USER_IP              = "user_ip";
	public static final String TERMINAL              = "terminal";
	public static final String DEVICE_ID              = "device_id";
	public static final String RISK_ITEM              = "risk_item";
	public static final String VALIDATECODE              = "validatecode";
	public static final String PAYBILL              = "paybill";
	public static final String SIGN_CODE              = "sign_code";
	public static final String PRODUCTNAME              = "productname";
	public static final String PRODUCTDESC              = "productdesc";
	public static final String PAYMENT_CODE              = "payment_code";
	public static final String ORDERID              = "orderid";
	public static final String REPAYMENT_PLAN       ="repayment_plan";
	public static final String REPAYMENT_STATE       ="repayment_state";
	public static final String REPAYMENT_NO       ="repayment_no";
	public static final String SMS_PARAM       ="sms_param";
	
	public static final String BATCHNUM       ="batchnum";
	public static final String MD5KEY       ="md5key";
	public static final String TRADENOTIFYSUBMITADD       ="tradenotifysubmitadd";
	
	public static final String ACCESSCHANNEL       ="access_channel";
	public static final String CHANNELNAME       ="channel_name";
	public static final String PRODUCT_NAME       ="product_name";
	public static final String PRODUCT_DESC       ="product_code";
	public static final String PAY_CHANNEL        ="pay_channel";
	public static final String IS_REFUND        ="is_refund";
	
	public static final String CHANNEL_TYPE    ="channel_type";
	
	public static final String FILENAME = "filename";
	public static final String NOTIFYURL = "notifyurl";
	public static final String FILETYPE = "filetype";
	
	

	//同步
	public static final String CONTRACT="contract";
	public static final String PRODUCT_CODE="product_code";
	public static final String SOURCE="source";
	public static final String CERTIFICATES_TYPE="certificates_type";
	public static final String CERTIFICATES_NUMBER="certificates_number";
	public static final String PLAN="plan";
	public static final String LOAN_NUMBER="loan_number";
	public static final String CONTRACT_START_TIME="contract_start_time";
	public static final String CONTRACT_END_TIME="contract_end_time";
	public static final String PERIODS_NUMBER="periods_number";
	public static final String LESSEE="lessee";
	public static final String GUARANTOR="guarantor";
	public static final String SURPLUS_PERIODS="surplus_periods";
	public static final String SURPLUS_AMOUNT="surplus_amount";
	public static final String UNION_LESSEE1="union_lessee1";
	public static final String UNION_LESSEE2="union_lessee2";
	public static final String GUARANTOR1="guarantor1";
	public static final String GUARANTOR2="guarantor2";
	public static final String IF_WITHHOLDING="if_withholding";
	public static final String LOAN_STATUS="loan_status";
	public static final String IF_UNIONLOAN="if_unionloan";
	
	public static final String BUSSTYPE="busstype";
	public static final String EXT_DK="ext_dk";
	
	
	//UES 加密解密接口
	public static final String UES_TYPE="type";
	public static final String UES_ENCRYPTTYPE="encryptType";
	public static final String UES_ORIGINALTEXT="originalText";
	public static final String UES_TICKETNO="ticketNo";
	public static final String OPEN_ID = "open_id";
	public static final String PAY_TYPE = "pay_type";
	public static final String CHANNEL_NAME = "channel_name";

}
