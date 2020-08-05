package com.yiranpay.gateway.request;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * UES加密解密请求参数
 * @author pandaa
 *
 */
public class UESRequest extends RequestBase{
	/**
	 * encryption-加密，decryption-解密
	 */
	private String type;
	/**
	 * 加密类型 AES DES
	 */
	private String encryptType;
	/**
	 * 加密原文
	 */
	private String originalText;
	/**
	 * 加密票据
	 */
	private String ticketNo;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEncryptType() {
		return encryptType;
	}
	public void setEncryptType(String encryptType) {
		this.encryptType = encryptType;
	}
	public String getOriginalText() {
		return originalText;
	}
	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	
	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE);
    }

}
