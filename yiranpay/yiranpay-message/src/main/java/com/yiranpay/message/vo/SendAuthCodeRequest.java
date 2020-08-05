package com.yiranpay.message.vo;

import java.io.Serializable;

/**
 * 发送短息请求
 * @author glb
 *
 */
public class SendAuthCodeRequest implements Serializable{
	private static final long serialVersionUID = 8352410887159848214L;
	
	/** 模版编码 */
	private String smsTemplateCode;
	/**
	 * 手机号
	 */
	private String sendRecordPhone;
	/**
	 * 验证码
	 */
	private String sendRecordVerifyCode;
	/**
	 * 短信发送流水号
	 */
	private String msgOrderNo;

	/**
	 * 短信发送类型 0：单条； 1：批量
	 */
	private Integer msgSendType;

	public String getSmsTemplateCode() {
		return smsTemplateCode;
	}

	public void setSmsTemplateCode(String smsTemplateCode) {
		this.smsTemplateCode = smsTemplateCode;
	}

	public String getSendRecordPhone() {
		return sendRecordPhone;
	}

	public void setSendRecordPhone(String sendRecordPhone) {
		this.sendRecordPhone = sendRecordPhone;
	}

	public String getSendRecordVerifyCode() {
		return sendRecordVerifyCode;
	}

	public void setSendRecordVerifyCode(String sendRecordVerifyCode) {
		this.sendRecordVerifyCode = sendRecordVerifyCode;
	}

	public String getMsgOrderNo() {
		return msgOrderNo;
	}

	public void setMsgOrderNo(String msgOrderNo) {
		this.msgOrderNo = msgOrderNo;
	}

	public Integer getMsgSendType() {
		return msgSendType;
	}

	public void setMsgSendType(Integer msgSendType) {
		this.msgSendType = msgSendType;
	}
}
