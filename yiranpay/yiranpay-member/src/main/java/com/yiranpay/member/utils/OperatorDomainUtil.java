/**
 *
 */
package com.yiranpay.member.utils;


import org.apache.commons.lang.StringUtils;

import com.yiranpay.member.domain.CompanyMember;
import com.yiranpay.member.domain.LoginName;
import com.yiranpay.member.domain.MemberTmOperator;
import com.yiranpay.member.domain.MemberTrPassword;
import com.yiranpay.member.domain.PayPassWord;
import com.yiranpay.member.enums.LockEnum;
import com.yiranpay.member.enums.MemberTypeEnum;
import com.yiranpay.member.enums.OperatorStatusEnum;
import com.yiranpay.member.enums.OperatorTypeEnum;
import com.yiranpay.member.enums.PassWordStatusEnum;
import com.yiranpay.member.enums.PlatFormTypeEnum;
import com.yiranpay.member.request.CreateMemberInfoRequest;
import com.yiranpay.member.request.IntegratedPersonalRequest;
import com.yiranpay.member.request.PersonalMemberRequest;


/**
 * <p>操作员领域对象工具类</p>
 */
public class OperatorDomainUtil {

	/**
     * 创建会员请求对象转换成会员领域对象
     * @param request 创建会员请求对象
     * @return 默认操作员
     */
    public static MemberTmOperator convertReqToDefaultOperator(CreateMemberInfoRequest request) {
    	MemberTmOperator operator = new MemberTmOperator();
        operator.setOperatorType(OperatorTypeEnum.getByCode(request.getMemberType().longValue()).getCode().intValue());
        operator.setIsDefault(1);//是否为默认操作员(0 否 1是)
        operator.setOperatorType(OperatorStatusEnum.NORMAL.getCode().intValue());
        operator.setLockStatus(LockEnum.UNLOCK.getCode().intValue());
        operator.setPassword(request.getLoginPassword());
        if(StringUtils.isNotBlank(request.getLoginPassword())){
        	operator.setPwdStatus(PassWordStatusEnum.NORMAL.getCode());
        }
        LoginName loginName = new LoginName();
        //loginName.setLoginName(StringUtil.toLowerCase(StringUtil.trim(request.getLoginName())));
        loginName.setLoginName("admin");
        loginName.setLoginNameType(request.getLoginNameType());
        int pid = PlatFormTypeEnum.DEFAULT.getCode();
        if (StringUtils.isNotBlank(request.getLoginNamePlatformType())) {
            pid = Integer.parseInt(StringUtils.trim(request.getLoginNamePlatformType()));
        }
        loginName.setPlatFormType(pid);
        MemberTypeEnum memberTypeEnum = MemberTypeEnum.getByCode(request.getMemberType()
            .longValue());
        //个人会员不需要登录名
        if (MemberTypeUtil.isCompanyMemberType(memberTypeEnum.getCode().intValue())) {
            operator.addLoginName(loginName);
        }

        operator.setStatus(OperatorStatusEnum.NORMAL.getCode().intValue());
        return operator;
    }
    
    /**
     * 集成创建个人会员请求对象转换成默认操作员域对象
     * @param request 集成创建个人会员请求对象
     * @return 默认操作员
     */
    public static MemberTmOperator convertReqToDefaultOperator(IntegratedPersonalRequest request) {
    	MemberTmOperator operator = new MemberTmOperator();
        PersonalMemberRequest req = request.getPersonalRequest();
        operator.setOperatorType(OperatorTypeEnum.PERSONAL.getCode().intValue());
        operator.setIsDefault(1);//是否为默认操作员(0 否 1是) 
        operator.setStatus(OperatorStatusEnum.NORMAL.getCode().intValue());
        operator.setLockStatus(LockEnum.UNLOCK.getCode().intValue());
        operator.setPassword(req.getLoginPassword());
        //个人会员操作员登录信息无意义
        /*
        LoginName loginName = new LoginName();
        loginName.setLoginName(StringUtil.toLowerCase(StringUtil.trim(req.getLoginName())));
        loginName.setLoginNameType(req.getLoginNameType());
        int pid = PlatFormTypeEnum.DEFAULT.getCode();
        if (StringUtil.isNotBlank(request.getPlatformType())) {
            pid = Integer.parseInt(StringUtil.trim(req.getLoginNamePlatformType()));
        }
        loginName.setPlatFormType(pid);
        operator.addLoginName(loginName);
        */

        return operator;
    }
    
    public static MemberTrPassword convertToPasswordDO(MemberTmOperator operator) {
    	MemberTrPassword passwordDO = new MemberTrPassword();
        if (null != operator.getPayPwdList() && operator.getPayPwdList().size() > 0) {
            PayPassWord payPassWord = operator.getPayPwdList().get(0);
            passwordDO.setAccountId(payPassWord.getAccountId());
            passwordDO.setPassword(payPassWord.getPassWord());
            passwordDO.setMpassword(payPassWord.getMpassWord());
            if(payPassWord.getPwdStatus() != null)
            	passwordDO.setStatus(payPassWord.getPwdStatus().getCode());
        }
        passwordDO.setOperatorId(operator.getOperatorId());
        passwordDO.setCreateUser(operator.getCreateUser());
        return passwordDO;
    }

	public static MemberTmOperator convertReqToDefaultOperator(CompanyMember companyMember) {
		MemberTmOperator operator = new MemberTmOperator();
        operator.setOperatorType(Integer.parseInt(companyMember.getMemberType()));
        operator.setIsDefault(1);//是否为默认操作员(0 否 1是)
        operator.setOperatorType(OperatorStatusEnum.NORMAL.getCode().intValue());
        operator.setLockStatus(LockEnum.UNLOCK.getCode().intValue());
        operator.setPassword(companyMember.getLoginPassword());
        operator.setNickname(companyMember.getMemberName());
        if(StringUtils.isNotBlank(companyMember.getLoginPassword())){
        	operator.setPwdStatus(PassWordStatusEnum.NORMAL.getCode());
        }
        LoginName loginName = new LoginName();
        loginName.setLoginName(StringUtils.trim(companyMember.getLoginName()));
        loginName.setLoginNameType(Integer.parseInt(companyMember.getLoginNameType()));
        int pid = PlatFormTypeEnum.DEFAULT.getCode();
        if (StringUtils.isNotBlank(companyMember.getLoginNamePlatformType())) {
            pid = Integer.parseInt(StringUtils.trim(companyMember.getLoginNamePlatformType()));
        }
        loginName.setPlatFormType(pid);
        MemberTypeEnum memberTypeEnum = MemberTypeEnum.getByCode(Long.parseLong(companyMember.getMemberType()));
        //个人会员不需要登录名
        if (MemberTypeUtil.isCompanyMemberType(memberTypeEnum.getCode().intValue())) {
            operator.addLoginName(loginName);
        }

        operator.setStatus(OperatorStatusEnum.NORMAL.getCode().intValue());
        return operator;
	}

}
