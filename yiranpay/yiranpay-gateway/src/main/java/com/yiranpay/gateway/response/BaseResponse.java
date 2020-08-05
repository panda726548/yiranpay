package com.yiranpay.gateway.response;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiranpay.gateway.exception.ErrorCodeException;

/**
 * 基础响应数据
 * @author pandaa
 *
 */
public class BaseResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6929293281283530364L;
	/**
	 * 参数编码字符集 不可空  商户网站使用的编码格式，如utf-8、gbk、gb2312等
	 */
	private String inputCharset;
	/**
	 * 备注 可空
	 */
	private String memo;
	
	/**
	 * 受理状态  表明业务处理结果	不可空	true, false
	 */
	private boolean result;
	
	/**
	 * 签名  不可空
	 */
	private String sign;
	/**
	 * 签名类型
	 */
	private String signType;
	
	/**
	 * 商户号  不可空
	 */
	private String partnerId;
	
	private ErrorCodeException exception;
	
	
	public ErrorCodeException getException() {
		return exception;
	}
	public void setException(ErrorCodeException exception) {
		this.exception = exception;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getInputCharset() {
		return inputCharset;
	}
	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
	
	
	

}
