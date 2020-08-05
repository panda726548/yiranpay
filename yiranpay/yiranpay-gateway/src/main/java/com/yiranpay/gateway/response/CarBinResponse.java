package com.yiranpay.gateway.response;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiranpay.gateway.exception.ErrorCodeException;

public class CarBinResponse extends BaseResponse{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1395211888874747669L;
	private ErrorCodeException exception;
	//银行卡号
	private String card_no;
	//业务类型
	private String buss_type;
	//机构编码
    private String instcode;
    //卡类型
    private String card_type;
    //是否有可用渠道
    private String is_access;
    //卡名称
    private String card_name;
    
	public ErrorCodeException getException() {
		return exception;
	}
	public void setException(ErrorCodeException exception) {
		this.exception = exception;
	}
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public String getBuss_type() {
		return buss_type;
	}
	public void setBuss_type(String buss_type) {
		this.buss_type = buss_type;
	}
	public String getInstcode() {
		return instcode;
	}
	public void setInstcode(String instcode) {
		this.instcode = instcode;
	}
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	public String getIs_access() {
		return is_access;
	}
	public void setIs_access(String is_access) {
		this.is_access = is_access;
	}
	public String getCard_name() {
		return card_name;
	}
	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}
    
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
