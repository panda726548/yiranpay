package com.yiranpay.gateway.exception;

import com.yiranpay.gateway.exception.ErrorCodeException.CommonException;

/**
 *
 * <p>错误码常量类</p>
 */
public class CommonDefinedException {
    public static CommonException SYSTEM_ERROR                      = new ErrorCodeException.CommonException(
                                                                        "SYSTEM_ERROR", "系统内部错误");

    public static CommonException REQUIRED_FIELD_NOT_EXIST          = new ErrorCodeException.CommonException(
                                                                        "REQUIRED_FIELD_NOT_EXIST",
                                                                        "必填字段未填写");
    public static CommonException FIELD_LENGTH_EXCEEDS_LIMIT        = new ErrorCodeException.CommonException(
                                                                        "FIELD_LENGTH_EXCEEDS_LIMIT",
                                                                        "字段长度超过限制");
    public static CommonException FIELD_TYPE_ERROR                  = new ErrorCodeException.CommonException(
                                                                        "FIELD_TYPE_ERROR",
                                                                        "字段类型错误");

    public static CommonException ILLEGAL_SIGN_TYPE                 = new ErrorCodeException.CommonException(
                                                                        "ILLEGAL_SIGN_TYPE",
                                                                        "签名类型不正确");
    public static CommonException ILLEGAL_SIGN                      = new ErrorCodeException.CommonException(
                                                                        "ILLEGAL_SIGN", "验签未通过");
    public static CommonException ILLEGAL_ARGUMENT                  = new ErrorCodeException.CommonException(
                                                                        "ILLEGAL_ARGUMENT",
                                                                        "参数校验未通过");
    public static CommonException ILLEGAL_SERVICE                   = new ErrorCodeException.CommonException(
                                                                        "ILLEGAL_SERVICE",
                                                                        "服务接口不存在");
    public static CommonException ILLEGAL_ARGUMENT_SERVICE_ISNULL                  = new ErrorCodeException.CommonException(
            "ILLEGAL_ARGUMENT_SERVICE_ISNULL",
            "参数校验未通过,必填字段未填写,服务接口名称为空");
    public static CommonException ILLEGAL_ARGUMENT_VERSION_ISNULL                  = new ErrorCodeException.CommonException(
            "ILLEGAL_ARGUMENT_VERSION_ISNULL",
            "参数校验未通过,必填字段未填写,接口版本为空");
    public static CommonException ILLEGAL_ARGUMENT_PARNTER_ID_ISNULL                  = new ErrorCodeException.CommonException(
            "ILLEGAL_ARGUMENT_PARNTER_ID_ISNULL",
            "参数校验未通过,必填字段未填写,商户号为空");
    public static CommonException ILLEGAL_ARGUMENT_INPUT_CHARSET_ISNULL                  = new ErrorCodeException.CommonException(
            "ILLEGAL_ARGUMENT_INPUT_CHARSET_ISNULL",
            "参数校验未通过,必填字段未填写,参数编码字符集为空");
    public static CommonException ILLEGAL_ARGUMENT_SIGN_TYPE_ISNULL                  = new ErrorCodeException.CommonException(
            "ILLEGAL_ARGUMENT_SIGN_TYPE_ISNULL",
            "参数校验未通过,必填字段未填写,签名方式为空");
    public static CommonException ILLEGAL_ARGUMENT_SIGN_ISNULL                  = new ErrorCodeException.CommonException(
            "ILLEGAL_ARGUMENT_SIGN_ISNULL",
            "参数校验未通过,必填字段未填写,签名为空");
    public static CommonException ILLEGAL_ID_TYPE                   = new ErrorCodeException.CommonException(
                                                                        "ILLEGAL_ID_TYPE",
                                                                        "ID类型不存在");
    public static CommonException USER_ACCOUNT_NOT_EXIST            = new ErrorCodeException.CommonException(
                                                                        "USER_ACCOUNT_NOT_EXIST",
                                                                        "用户账号不存在");
    public static CommonException MEMBER_ID_NOT_EXIST               = new ErrorCodeException.CommonException(
                                                                        "MEMBER_ID_NOT_EXIST",
                                                                        "用户MemberId不存在");
    public static CommonException PARTNER_ID_NOT_EXIST              = new ErrorCodeException.CommonException(
                                                                        "PARTNER_ID_NOT_EXIST",
                                                                        "合作方Id不存在");
    public static CommonException DUPLICATE_REQUEST_NO              = new ErrorCodeException.CommonException(
                                                                        "DUPLICATE_REQUEST_NO",
                                                                        "重复的请求号");
    public static CommonException ILLEGAL_OUTER_TRADE_NO            = new ErrorCodeException.CommonException(
                                                                        "ILLEGAL_OUTER_TRADE_NO",
                                                                        "交易订单号不存在");
    public static CommonException ILLEGAL_DATE_FORMAT               = new ErrorCodeException.CommonException(
                                                                        "ILLEGAL_DATE_FORMAT",
                                                                        "日期格式错误");
    public static CommonException PAY_METHOD_ERROR                  = new ErrorCodeException.CommonException(
                                                                        "ILLEGAL_PAY_ERROR",
                                                                        "支付方式错误");
    public static CommonException ILLEGAL_PAY_METHOD                = new ErrorCodeException.CommonException(
                                                                        "ILLEGAL_PAY_METHOD",
                                                                        "支付方式未授权");
    public static CommonException OPERATOR_ID_NOT_EXIST             = new ErrorCodeException.CommonException(
                                                                        "OPERATOR_ID_NOT_EXIST",
                                                                        "操作员Id不存在");
    public static CommonException TRADE_NO_MATCH_ERROR              = new ErrorCodeException.CommonException(
                                                                        "TRADE_NO_MATCH_ERROR",
                                                                        "交易号信息有误");
    public static CommonException TRADE_DATA_MATCH_ERROR            = new ErrorCodeException.CommonException(
                                                                        "TRADE_DATA_MATCH_ERROR",
                                                                        "交易信息有误");
    public static CommonException PREPAY_DATA_MATCH_ERROR           = new ErrorCodeException.CommonException(
                                                                        "PREPAY_DATA_MATCH_ERROR",
                                                                        "订金下订信息有误");

    public static CommonException ILLEGAL_ROYALTY_PARAMETERS        = new ErrorCodeException.CommonException(
                                                                        "ILLEGAL_ROYALTY_PARAMETERS",
                                                                        "分润账号集错误");
    public static CommonException MOBILE_NOT_EXIST                  = new ErrorCodeException.CommonException(
                                                                        "MOBILE_NOT_EXIST",
                                                                        "用户手机号不存在");
    public static CommonException TRADE_AMOUNT_MATCH_ERROR          = new ErrorCodeException.CommonException(
                                                                        "TRADE_AMOUNT_MATCH_ERROR",
                                                                        "交易内金额不匹配");
    public static CommonException TRADE_PAY_MATCH_ERROR             = new ErrorCodeException.CommonException(
                                                                        "TRADE_PAY_MATCH_ERROR",
                                                                        "交易与支付金额不匹配");
    public static CommonException ILLEGAL_PREPAY_NO                 = new ErrorCodeException.CommonException(
                                                                        "ILLEGAL_PREPAY_NO",
                                                                        "订金下订单号错误");
    public static CommonException ILLEGAL_ACCESS_SWITCH_SYSTEM      = new ErrorCodeException.CommonException(
                                                                        "ILLEGAL_ACCESS_SWITCH_SYSTEM",
                                                                        "商户不允许访问该类型的接口");
    public static CommonException ILLEGAL_REFUND_AMOUNT             = new ErrorCodeException.CommonException(
                                                                        "ILLEGAL_REFUND_AMOUNT",
                                                                        "退款金额信息错误");
    public static CommonException ILLEGAL_REQUEST                   = new ErrorCodeException.CommonException(
                                                                        "ILLEGAL_REQUEST", "风控未通过");
    public static CommonException ILLEGAL_AMOUNT_FORMAT             = new ErrorCodeException.CommonException(
                                                                        "ILLEGAL_AMOUNT_FORMAT",
                                                                        "金额格式错误");
    public static CommonException ILLEGAL_ENSURE_AMOUNT             = new ErrorCodeException.CommonException(
                                                                        "ILLEGAL_ENSURE_AMOUNT",
                                                                        "担保金额信息错误");
    public static CommonException ILLEGAL_TIME_INTERVAL             = new ErrorCodeException.CommonException(
                                                                        "ILLEGAL_TIME_INTERVAL",
                                                                        "时间区间错误");
    public static CommonException ACCOUNT_TYPE_NOT_SUPPORTED        = new ErrorCodeException.CommonException(
                                                                        "ACCOUNT_TYPE_NOT_SUPPORTED",
                                                                        "账户类型不支持");
    public static CommonException CARD_TYPE_NOT_SUPPORTED           = new ErrorCodeException.CommonException(
                                                                        "CARD_TYPE_NOT_SUPPORTED",
                                                                        "卡类型不支持");
    public static CommonException TRADE_NO_NOT_EXIST                = new ErrorCodeException.CommonException(
                                                                        "TRADE_NO_NOT_EXIST",
                                                                        "商户订单号和钱包交易号不能都为空");
    public static CommonException TRADE_LIST_ERROR                  = new ErrorCodeException.CommonException(
                                                                        "TRADE_LIST_ERROR",
                                                                        "转账列表参数错误");
    public static CommonException REFUND_LIST_ERROR                 = new ErrorCodeException.CommonException(
                                                                        "REFUND_LIST_ERROR",
                                                                        "退款列表参数错误");
    public static CommonException TOTAL_COUNT_ERROR                 = new ErrorCodeException.CommonException(
                                                                        "TOTAL_COUNT_ERROR",
                                                                        "总笔数与实际总笔数不符");
    public static CommonException TOTAL_AMOUNT_ERROR                = new ErrorCodeException.CommonException(
                                                                        "TOTAL_AMOUNT_ERROR",
                                                                        "总金额与实际总金额不符");
    public static CommonException USER_BANKCARD_NOT_EXIST           = new ErrorCodeException.CommonException(
                                                                        "USER_BANKCARD_NOT_EXIST",
                                                                        "用户不存在绑定银行卡");
    public static CommonException SEND_MESSAGE_NOT_SUPPORTED        = new ErrorCodeException.CommonException(
                                                                        "SEND_MESSAGE_NOT_SUPPORTED",
                                                                        "发送消息只支持 Y发送，N不发送");
    public static CommonException Fund_Out_Grade_NOT_SUPPORTED      = new ErrorCodeException.CommonException(
                                                                        "Fund_Out_Grade_NOT_SUPPORTED",
                                                                        "到账级别只支持 0普通，1快速");
    public static CommonException Company_Or_Personal_NOT_SUPPORTED = new ErrorCodeException.CommonException(
                                                                        "Company_Or_Personal_NOT_SUPPORTED",
                                                                        "对公对私只支持 B对公，C对私");
    public static CommonException IDENTITY_NO_ERROR                 = new ErrorCodeException.CommonException(
                                                                        "IDENTITY_NO_ERROR",
                                                                        "会员标识与证书持有者不匹配");
    public static CommonException ACCOUNT_NAME_DECRYPT_ERROR        = new ErrorCodeException.CommonException(
                                                                        "ACCOUNT_NAME_DECRYPT_ERROR",
                                                                        "银行卡账户名解密失败");
    public static CommonException CARD_NO_DECRYPT_ERROR             = new ErrorCodeException.CommonException(
                                                                        "CARD_NO_DECRYPT_ERROR",
                                                                        "银行卡号解密失败");
    public static CommonException CERT_NO_DECRYPT_ERROR             = new ErrorCodeException.CommonException(
															    		"CERT_NO_DECRYPT_ERROR",
															            "证件号解密失败");
    public static CommonException TRANSFER_LIST_DECRYPT_ERROR       = new ErrorCodeException.CommonException(
                                                                        "TRANSFER_LIST_DECRYPT_ERROR",
                                                                        "转账列表解密失败");
    public static CommonException ANONYMOUS_PAY_ERROR         = new ErrorCodeException.CommonException(
                                                                        "ANONYMOUS_PAY_ERROR",
                                                                        "匿名支付时支付方式不能为空");
    public static CommonException NO_VERIFY_ERROR         = new ErrorCodeException.CommonException(
            "NO_VERIFY_ERROR",
            "未实名认证");
    public static CommonException PASSWORD_LOCKED_ERROR         = new ErrorCodeException.CommonException(
            "PASSWORD_LOCKED_ERROR",
            "支付密码被锁定了");
    public static CommonException PASSWORD_ERROR         = new ErrorCodeException.CommonException(
            "PASSWORD_ERROR",
            "支付密码错误");
    public static CommonException IS_ACCREDITED_ERROR         = new ErrorCodeException.CommonException(
            "IS_ACCREDITED_ERROR",
            "快捷通账户已被绑定，请输入其他快捷通账户进行绑定");
    public static CommonException ID_LENGHT_ERROR         = new ErrorCodeException.CommonException(
            "ID_LENGHT_ERROR",
            "请输入长度为15或18位身份证号");
    public static CommonException CARDNO_CODE_ERROR                 = new ErrorCodeException.CommonException(
            "CARDNO_CODE_ERROR",
            "银行卡号与机构编码不匹配不匹配");
    public static CommonException MEMBER_NO_DATE_ERROR                 = new ErrorCodeException.CommonException(
            "MEMBER_NO_DATE_ERROR",
            "未找到配置数据记录");
    
    public static CommonException CHANNEL_NO_DATE_ERROR                 = new ErrorCodeException.CommonException(
            "CHANNEL_NO_DATE_ERROR",
            "未找到可用渠道数据");
    
    public static CommonException CVV2_NO_NULL                 = new ErrorCodeException.CommonException(
            "CVV2_NO_NULL",
            "CVV2不能为空");
    public static CommonException VALIDATE_NO_NULL                 = new ErrorCodeException.CommonException(
            "VALIDATE_NO_NULL",
            "有效期不能为空");
    public static CommonException BIDDING_FAIL                 = new ErrorCodeException.CommonException(
            "BIDDING_FAIL",
            "调用签约失败");
    
    public static CommonException BAR_CODE_ALREADY_EXIST                 = new ErrorCodeException.CommonException(
            "BAR_CODE_ALREADY_EXIST",
            "获取用户账户信息失败，请用户刷新二维码");
    public static CommonException BAR_CODE_NO_YONGDA                 = new ErrorCodeException.CommonException(
            "BAR_CODE_NO_YONGDA",
            "请使用永达钱包二维码扫描");
    public static CommonException BAR_CODE_NETWORK_ERROR                 = new ErrorCodeException.CommonException(
            "BAR_CODE_NETWORK_ERROR",
            "网络异常，请检查网络环境后再试！");
    
    public static CommonException PAY_CHANNEL_ERROR                 = new ErrorCodeException.CommonException(
            "PAY_CHANNEL_ERROR",
            "支付渠道错误");
    public static CommonException PAY_CARDTYPE_ERROR                 = new ErrorCodeException.CommonException(
            "PAY_CARDTYPE_ERROR",
            "支付卡类型错误");
    public static CommonException UPDATE_FILE_ERROR                 = new ErrorCodeException.CommonException(
            "UPDATE_FILE_ERROR",
            "上传文件错误或文件未找到");
    public static CommonException ILLEGAL_PRODUCT_AUTH                 = new ErrorCodeException.CommonException(
    		"ILLEGAL_PRODUCT_AUTH",
    		"商户还没有该产品权限，请先申请产品权限");
    
    //UES
    public static CommonException UES_TYPE_IS_NULL                 = new ErrorCodeException.CommonException(
            "UES_TYPE_IS_NULL",
            "UES加密解密参数校验未通过，type为空");
    public static CommonException UES_ENCRYPTTYPE_IS_NULL                 = new ErrorCodeException.CommonException(
            "UES_ENCRYPTTYPE_IS_NULL",
            "UES加密解密参数校验未通过，加密类型 encryptType为空");
    
    public static CommonException UES_ORIGINALTEXT_IS_NULL                 = new ErrorCodeException.CommonException(
            "UES_ORIGINALTEXT_IS_NULL",
            "UES加密解密参数校验未通过，加密原文originalText为空");
    public static CommonException UES_TICKETNO_IS_NULL                 = new ErrorCodeException.CommonException(
            "UES_TICKETNO_IS_NULL",
            "UES加密解密参数校验未通过，解密票据号 ticketNo为空");
}
