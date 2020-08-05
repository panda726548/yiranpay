package com.yiranpay.gateway.request;

import java.io.Serializable;

/**
 * 查询商户接口权限请求
 * @author pandaa
 *
 */
public class AuthorizationRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3638549044263212527L;
	/** 商户ID */
    private String merchantId;
    /**
     * 请求Ip地址
     */
    private String ipAddress;
    
    /**
	 * 接口名称。	不可空
	 */
	private String service;
	/**
	 * 产品code
	 */
	private String productCode;

	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}
	
	
}
