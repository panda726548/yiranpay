package com.yiranpay.gateway.exception;

/**
 *
 * <p>错误码异常类</p>
 */
public class ErrorCodeException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7303557124137874374L;
	private String            errorCode;
    private String            errorMsg;
    private String            memo;
    private boolean    		  result = false;

    public ErrorCodeException(String errorCode, String errorMsg) {
        super();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
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







	public static class CommonException extends ErrorCodeException {

        private static final long serialVersionUID = -3004891288021568474L;

        public CommonException(String errorCode, String errorMsg) {
            super(errorCode, errorMsg);
        }

    }

}
