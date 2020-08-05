package com.yiranpay.gateway.response;

import java.io.Serializable;

/**
 * 查询授权返回参数
 * @author pandaa
 *
 */
public class AuthorizationResult implements Serializable{

	private static final long serialVersionUID = -1534851249246801888L;
	/**
	 * 是否有权限 true 有权限  false没有权限
	 */
	private boolean isAuth;
	/**
	 * 错误编码
	 */
	private String errorCode;
	/**
	 * 错误信息
	 */
	private String errorMsg;
	
	public boolean isAuth() {
		return isAuth;
	}
	public void setAuth(boolean isAuth) {
		this.isAuth = isAuth;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
