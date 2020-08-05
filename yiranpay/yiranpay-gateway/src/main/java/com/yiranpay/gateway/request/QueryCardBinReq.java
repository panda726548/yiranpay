package com.yiranpay.gateway.request;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 *
 * <p>卡Bin查询</p>
 */
  
public class QueryCardBinReq extends RequestBase {


	/**
	 * 商户网站请求号   不可空    确保是唯一，建议用时间戳
	 */
	private String requestNo;
	 /**
	  * 银行卡号  不可空
	  */
    private String cardNo;

    /**
     * 业务类型  不可空  签约：AUTH 支付：FUND_IN
     */
    private String bussType;

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getBussType() {
		return bussType;
	}


	public void setBussType(String bussType) {
		this.bussType = bussType;
	}

	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
