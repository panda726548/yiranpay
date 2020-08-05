package com.yiranpay.message.service;

import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.message.vo.SendAuthCodeRequest;

/**
 * 短息发送和验证服务
 *
 * @author glb
 * @date 2020-04-06
 */
public interface IMsgSmsSendService {
	/**
	 * 发送验证码
	 * @param request
	 * @return
	 */
	public AjaxResult sendAuthCode(SendAuthCodeRequest request);

	/**
	 * 验证手机号和验证码
	 * @param request
	 * @return
	 */
	public AjaxResult verifyMobileAuthCode(SendAuthCodeRequest request);

}
