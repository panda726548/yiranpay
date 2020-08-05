package com.yiranpay.member.validator;

import com.yiranpay.member.enums.ValidateLoginPwdModeEnum;
import com.yiranpay.member.exception.MaIllegalArgumentException;
import com.yiranpay.member.request.LoginPwdRequest;
import com.yiranpay.member.request.OperatorLoginPwdByIdRequest;
import com.yiranpay.member.request.OperatorLoginPwdLockRequest;
import com.yiranpay.member.request.OperatorLoginPwdRequest;
import com.yiranpay.member.request.PersonalLoginPwdLockRequest;
import com.yiranpay.member.request.PersonalLoginPwdRequest;
import com.yiranpay.member.request.ValidateLoginPwdRequest;

/**
 * <p>登陆密码类接口入参验证</p>
 */
public class LoginPwdFacadeValidator {

    /**
     * 验证设置登陆密码接口
     * @param request
     */
    public static void validator(LoginPwdRequest request){
        CommonFacadeValidator.checkOperatorId(request.getOperatorId(), true);
        CommonFacadeValidator.checkLoginPwd(request.getPassword(), true);
        CommonFacadeValidator.checkPwdStatus(request.getStatus(),true);
    }

    /**
     * 验证操作员登陆密码请求信息
     * @param request
     */
    public  static void validator(OperatorLoginPwdRequest request){
        CommonFacadeValidator.checkLoginPwd(request.getPassword(), true);
        CommonFacadeValidator.checkIdentity(request.getMemberIdentity(), true);
        CommonFacadeValidator.checkPlatFormType(request.getPlatformType());
        CommonFacadeValidator.checkLoginName(request.getLoginName());
        CommonFacadeValidator.checkPlatFormType(request.getLoginNamePlatformType());
       // CommonFacadeValidator.checkSalt(request.getSalt());
        //验证登录平台类型是否支持登录钱包
//        CommonFacadeValidator.checkLoginPlatFormType(request.getLoginNamePlatformType());
//        CommonFacadeValidator.checkLoginPlatFormType(request.getPlatformType());
    }

    /**
     * 验证操作员登陆密码请求信息
     * @param request
     */
    public  static void validator(OperatorLoginPwdByIdRequest request){
        CommonFacadeValidator.checkLoginPwd(request.getPassword(), true);
        CommonFacadeValidator.checkOperatorId(request.getOperatorId(), true);
        CommonFacadeValidator.checkSalt(request.getSalt());
    }


    /**
     * 验证个人会员登陆密码请求信息
     * @param request
     */
    public static void validator(PersonalLoginPwdRequest request){
        CommonFacadeValidator.checkLoginPwd(request.getPassword(), true);
        CommonFacadeValidator.checkIdentity(request.getMemberIdentity(), true);
        CommonFacadeValidator.checkPlatFormType(request.getPlatformType());
        CommonFacadeValidator.checkSalt(request.getSalt());
        //验证登录平台类型是否支持登录钱包
        CommonFacadeValidator.checkLoginPlatFormType(request.getPlatformType());
    }
    /**
     * 验证重置操作员登录密码锁请求
     * @param request
     */
    public static void validator(OperatorLoginPwdLockRequest request){
        CommonFacadeValidator.checkIdentity(request.getMemberIdentity(), true);
        CommonFacadeValidator.checkPlatFormType(request.getPlatformType());
        CommonFacadeValidator.checkLoginName(request.getLoginName());
        CommonFacadeValidator.checkPlatFormType(request.getLoginNamePlatformType());
    }

    /**
     * 验证置个人会员登录密码锁请求参数
     * @param request
     */
    public static void validator(PersonalLoginPwdLockRequest request){
        CommonFacadeValidator.checkIdentity(request.getMemberIdentity(), true);
        CommonFacadeValidator.checkPlatFormType(request.getPlatformType());
    }

    /**
     * 验证登录密码是否设置与锁定
     * @param request
     */
    public static void validator(ValidateLoginPwdRequest request) {
        CommonFacadeValidator.checkOperatorId(request.getOperatorId(), true);
        if (request.getValidateMode() == null) {
            throw new MaIllegalArgumentException("验证模式不能为空");
        }
        if (ValidateLoginPwdModeEnum.getByCode(request.getValidateMode()) == null) {
            throw new MaIllegalArgumentException("验证模式非法");
        }
    }
}
