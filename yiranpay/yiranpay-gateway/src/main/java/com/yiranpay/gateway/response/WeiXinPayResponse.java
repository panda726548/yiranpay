package com.yiranpay.gateway.response;

public class WeiXinPayResponse extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2628864244514123271L;
	/**
	 * 会员ID:会员在商户的唯一编号
	 */
	private String userId;
	/**
	 * 返回错误码
	 */
	private String errorCode;
	/**
	 * 返回错误原因
	 */
	private String errorMessage;
	/**
	 * 是否跳转收银台
	 */
	private String isForwardCashier;
	/**
	 * 公众账号ID
	 */
	private String appId;
	
	/**
	 * 微信返回的随机字符串
	 */
	private String nonceStr;
	/**
	 * packAge
	 */
	private String packAge;
	/**
	 * 微信签名
	 */
	private String paySign;
	/**
	 * 业务结果
	 */
	private String resultCode;
	/**
	 * 当return_code为FAIL时返回信息为错误原因 ，例如 签名失败
	 */
	private String returnMsg;
	/**
	 * SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
	 */
	private String returnCode;
	/**
	 * 微信签名类型
	 */
	private String paySignType;
	/**
	 * 二维码链接
	 */
	private String codeUrl;
	/**
	 * 时间戳
	 */
	private String timeStamp;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getIsForwardCashier() {
		return isForwardCashier;
	}
	public void setIsForwardCashier(String isForwardCashier) {
		this.isForwardCashier = isForwardCashier;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getPackAge() {
		return packAge;
	}
	public void setPackAge(String packAge) {
		this.packAge = packAge;
	}
	public String getPaySign() {
		return paySign;
	}
	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getPaySignType() {
		return paySignType;
	}
	public void setPaySignType(String paySignType) {
		this.paySignType = paySignType;
	}
	public String getCodeUrl() {
		return codeUrl;
	}
	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "WeiXinPayResponse [userId=" + userId + ", errorCode=" + errorCode + ", errorMessage=" + errorMessage
				+ ", isForwardCashier=" + isForwardCashier + ", appId=" + appId + ", nonceStr=" + nonceStr
				+ ", packAge=" + packAge + ", paySign=" + paySign + ", resultCode=" + resultCode + ", returnMsg="
				+ returnMsg + ", returnCode=" + returnCode + ", paySignType=" + paySignType + ", codeUrl=" + codeUrl
				+ ", timeStamp=" + timeStamp + "]";
	}
	
	
}
