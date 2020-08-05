/**
 * Copyright 2013 weibopay.com, Inc. All rights reserved.
 */
package com.yiranpay.gateway.exception;

/**
 * <p>
 * 服务层异常
 * </p>
 */
public class GateWayServiceException extends RuntimeException {

	/**
     *
     */
	private static final long serialVersionUID = 1946389612484564472L;

	private String code;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	public GateWayServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public GateWayServiceException(String code) {
		super();
		this.code = code;
	}

	public GateWayServiceException(String msg, String code, Throwable thr) {
		super(msg, thr);
		this.code = code;
	}

	public GateWayServiceException(String msg, String code) {
		super(msg);
		this.code = code;
	}

	public GateWayServiceException(Throwable thr, String code) {
		super(thr);
		this.code = code;
	}
}
