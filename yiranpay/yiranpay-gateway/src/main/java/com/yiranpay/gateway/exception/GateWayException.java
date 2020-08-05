package com.yiranpay.gateway.exception;

/**
 * <p>
 * 服务层异常
 * </p>
 *
 */
public class GateWayException extends RuntimeException {

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

	public GateWayException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public GateWayException(String code) {
		super();
		this.code = code;
	}

	public GateWayException(String msg, String code, Throwable thr) {
		super(msg, thr);
		this.code = code;
	}

	public GateWayException(String msg, String code) {
		super(msg);
		this.code = code;
	}

	public GateWayException(Throwable thr, String code) {
		super(thr);
		this.code = code;
	}
}
