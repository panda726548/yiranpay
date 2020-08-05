package com.yiranpay.cashier.support.request;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 基本参数
 * @author pandaa
 *
 */
public class RequestBase  {

	/**
	 * 接口名称。	不可空
	 */
	private String service;
	/**
	 * 接口版本 不可空 接口版本，目前只有固定值1.0
	 */
	private String version;
	/**
	 * 商户号  不可空
	 */
	private String partnerId;
	/**
	 * 参数编码字符集  不可空  商户网站使用的编码格式，如utf-8、gbk、gb2312等。
	 */
	private String inputCharset;

	/**
	 * 签名方式 RSA或 MD5  不可空
	 */
	private String signType;
	/**
	 * 签名  不可空
	 */
	private String sign;
	/**
	 * 页面跳转同步返回页面路径.可空  处理完请求后，当前页面自动跳转到商户网站里指定页面的http路径。
	 */
	private String returnUrl;
	/**
	 * 备注 说明信息 可空
	 */
	private String memo;
	/**
	 * 扩展参数
	 */
	private String          extension;  
	
	private String          userAgent;
	
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getInputCharset() {
		return inputCharset;
	}
	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE);
    }
	
}
