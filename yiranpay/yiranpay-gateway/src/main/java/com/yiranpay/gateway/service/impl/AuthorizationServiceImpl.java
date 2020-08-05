package com.yiranpay.gateway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yiranpay.common.config.Global;
import com.yiranpay.gateway.domain.GatewayApiAuth;
import com.yiranpay.gateway.domain.GatewayGatewayApi;
import com.yiranpay.gateway.domain.GatewayIpWhite;
import com.yiranpay.gateway.domain.GatewayProductMember;
import com.yiranpay.gateway.request.AuthorizationRequest;
import com.yiranpay.gateway.response.AuthorizationResult;
import com.yiranpay.gateway.service.IAuthorizationService;
import com.yiranpay.gateway.service.IGatewayApiAuthService;
import com.yiranpay.gateway.service.IGatewayGatewayApiService;
import com.yiranpay.gateway.service.IGatewayIpWhiteService;
import com.yiranpay.gateway.service.IGatewayProductMemberService;

/**
 * 权限
 * @author pandaa
 *
 */
@Service
public class AuthorizationServiceImpl implements IAuthorizationService{

	public static final String IPWHITECODE = "ILLEGAL_NO_IP_WHITE";
	public static final String AUTHCODE = "ILLEGAL_NO_INTEFACE_AUTH";
	public static final String PRODUCT_AUTH_CODE = "ILLEGAL_PRODUCT_AUTH";
	@Autowired
	private IGatewayIpWhiteService gatewayIpWhiteService;
	
	@Autowired
	private IGatewayApiAuthService gatewayApiAuthService;
	@Autowired
	private IGatewayGatewayApiService gatewayGatewayApiService;
	
	@Autowired
	private IGatewayProductMemberService gatewayProductMemberService;
	
	@Override
	public AuthorizationResult authorization(AuthorizationRequest auth) {
		AuthorizationResult authResult = new AuthorizationResult();
		//1.判断ip是否在白名单
		if("true".equals(Global.getIsOpenWhiteIp())){
			GatewayIpWhite gatewayIpWhite = gatewayIpWhiteService.selectGatewayIpWhiteByMerchantId(auth.getMerchantId());
			if(StringUtils.isEmpty(gatewayIpWhite)){//根据商户号没有查询到ip设置
				authResult.setAuth(false);
				authResult.setErrorMsg("IP不在白名单，请联系管理员添加白名单");
				authResult.setErrorCode(IPWHITECODE);
				return authResult;
			}
			if(!gatewayIpWhite.getIpAddress().contains(auth.getIpAddress())){//IP不在白名单
				authResult.setAuth(false);
				authResult.setErrorMsg("IP不在白名单，请联系管理员添加白名单");
				authResult.setErrorCode(IPWHITECODE);
				return authResult;
			}
		}
		
		//2.查询接口权限
		GatewayApiAuth gatewayApiAuth = new GatewayApiAuth();
		gatewayApiAuth.setMerchantId(auth.getMerchantId());
		List<GatewayApiAuth> apiAuthList = gatewayApiAuthService.selectGatewayApiAuthList(gatewayApiAuth);
		if(apiAuthList.size()>0){//
			StringBuffer apiNames = new StringBuffer();
			for (GatewayApiAuth api : apiAuthList) {
				GatewayGatewayApi gatewayApi = gatewayGatewayApiService.selectApiMenuById(api.getApiId());
				apiNames.append(gatewayApi.getApiCode());
				apiNames.append(",");
			}
			String apiServices = "";
			if(apiNames.length()>0){
				apiServices = apiNames.substring(0, apiNames.length()-1);
	    	}
			
			if(!apiServices.contains(auth.getService())){
				authResult.setAuth(false);
				authResult.setErrorMsg("该商户没有权限访问【"+auth.getService()+"】接口，请联系管理员添加权限");
				authResult.setErrorCode(AUTHCODE);
				return authResult;
			}
			
		}else{
			authResult.setAuth(false);
			authResult.setErrorMsg("该商户没有权限访问【"+auth.getService()+"】接口，请联系管理员添加权限");
			authResult.setErrorCode(AUTHCODE);
			return authResult;
		}
		authResult.setAuth(true);
		return authResult;
	}

	@Override
	public AuthorizationResult authorizationProduct(AuthorizationRequest auth) {
		AuthorizationResult authResult = new AuthorizationResult();
		GatewayProductMember productMember = new GatewayProductMember();
		productMember.setMemberNo(auth.getMerchantId());
		productMember.setProductCode(auth.getProductCode());
		List<GatewayProductMember> list = gatewayProductMemberService.selectGatewayProductMemberList(productMember);
		if(list.size() == 0){
			authResult.setAuth(false);
			authResult.setErrorMsg("该商户没有该产品权限，请先申请产品权限");
			authResult.setErrorCode(PRODUCT_AUTH_CODE);
			return authResult;
		}
		authResult.setAuth(true);
		return authResult;
	}

}
