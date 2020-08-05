package com.yiranpay.gateway.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yiranpay.gateway.exception.ErrorCodeException;

/**
 * H5支付接口响应
 * @author pandaa
 *
 */
public class ChinaH5PayResponse extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4908664518342816543L;
	
	
	/**
	 * 支付表单
	 */
	private String htmlForm;
	/**
	 * 支付订单状态
	 */
	private String payStatus;
	/**
	 * 会员ID
	 */
	private String userId;
	/**
	 * 是否跳转收银台
	 */
	private String isForwardCashier;
	/**
	 * 返回错误原因
	 */
	private String errorMessage;
	/**
	 * 返回错误码
	 */
	private String errorCode;
	public String getHtmlForm() {
		return htmlForm;
	}
	public void setHtmlForm(String htmlForm) {
		this.htmlForm = htmlForm;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIsForwardCashier() {
		return isForwardCashier;
	}
	public void setIsForwardCashier(String isForwardCashier) {
		this.isForwardCashier = isForwardCashier;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
