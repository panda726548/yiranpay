package com.yiranpay.gateway.service;

import com.yiranpay.gateway.request.AuthorizationRequest;
import com.yiranpay.gateway.response.AuthorizationResult;

/**
 * 授权查询-商户是否有权限
 * @author pandaa
 *
 */
public interface IAuthorizationService {
	/**
	 * 商户是否有接口权限
	 * @param auth
	 * @return
	 */
	public AuthorizationResult authorization(AuthorizationRequest auth);
	/**
	 * 商户是否有产品权限
	 * @param auth
	 * @return
	 */
	public AuthorizationResult authorizationProduct(AuthorizationRequest auth);

}
