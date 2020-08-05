package com.yiran.framework.shiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.yiran.common.constant.Constants;
import com.yiran.common.constant.ShiroConstants;
import com.yiran.common.constant.UserConstants;
import com.yiran.common.enums.UserStatus;
import com.yiran.common.exception.user.CaptchaException;
import com.yiran.common.exception.user.UserBlockedException;
import com.yiran.common.exception.user.UserDeleteException;
import com.yiran.common.exception.user.UserNotExistsException;
import com.yiran.common.exception.user.UserPasswordNotMatchException;
import com.yiran.common.utils.DateUtils;
import com.yiran.common.utils.MessageUtils;
import com.yiran.common.utils.ServletUtils;
import com.yiran.common.utils.security.ShiroUtils;
import com.yiran.framework.manager.AsyncManager;
import com.yiran.framework.manager.factory.AsyncFactory;
import com.yiran.project.system.user.domain.MemberTmMemberIdentity;
import com.yiran.project.system.user.domain.MemberTmOperator;
import com.yiran.project.system.user.domain.User;
import com.yiran.project.system.user.service.IMemberTmMemberIdentityService;
import com.yiran.project.system.user.service.IMemberTmOperatorService;

/**
 * 登录校验方法
 * 
 * @author yiran
 */
@Component
public class LoginService
{
	@Autowired
	private IMemberTmMemberIdentityService memberIdentityService;
	
	@Autowired
	private IMemberTmOperatorService memberTmOperatorService;

	@Autowired
    private PasswordService passwordService;
	
	
    /**
     * 登录
     */
    public User login(String username, String password)
    {
        // 验证码校验
        if (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA)))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }

        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }

        MemberTmMemberIdentity mi = new MemberTmMemberIdentity();
        mi.setIdentity(username);
        List<MemberTmMemberIdentity> identityList = memberIdentityService.selectMemberTmMemberIdentityList(mi);
        //先根据会员标识查出会员号，在根据会员号到操作员表查出密码
        User user = null;
        if(identityList.size() > 0){
        	MemberTmMemberIdentity memberIdentity = identityList.get(0);
        	MemberTmOperator operator = memberTmOperatorService.selectMemberTmOperatorByMemberId(memberIdentity.getMemberId());
        	
        	user = new User();
        	user.setLoginName(memberIdentity.getIdentity());
        	user.setMemberId(memberIdentity.getMemberId());
        	user.setStatus(operator.getStatus());
        	user.setPassword(operator.getPassword());
        }
       
        if (user == null)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.not.exists")));
            throw new UserNotExistsException();
        }
        
        if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.blocked", user.getRemark())));
            throw new UserBlockedException();
        }

        passwordService.validate(user, password);

        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        return user;
    }

    private boolean maybeEmail(String username)
    {
        if (!username.matches(UserConstants.EMAIL_PATTERN))
        {
            return false;
        }
        return true;
    }

    private boolean maybeMobilePhoneNumber(String username)
    {
        if (!username.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN))
        {
            return false;
        }
        return true;
    }

}
