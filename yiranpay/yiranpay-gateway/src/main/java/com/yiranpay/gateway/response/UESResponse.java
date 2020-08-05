package com.yiranpay.gateway.response;

import com.yiranpay.gateway.exception.ErrorCodeException;

public class UESResponse extends BaseResponse{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6239328517104296853L;
	private ErrorCodeException exception;
	/**
	 * 加密原文
	 */
	private String originalText;
	/**
	 * 加密票据
	 */
	private String ticketNo;
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
	public ErrorCodeException getException() {
		return exception;
	}
	public void setException(ErrorCodeException exception) {
		this.exception = exception;
	}
	
	

}
