package com.yiranpay.cashier.tool;
/**
 * 字符编码
 * @author pandaa
 *
 */
public enum HttpEncoding {
    
    UTF8("UTF-8"),GB2312("GB2312"), GBK("GBK"),GB18030("GB18030");
    
    private String code;
    
    private HttpEncoding(String encode){
        this.code = encode;
    }
    
    public String getCode(){
        return this.code;
    }
}
