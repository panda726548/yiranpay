package com.yiranpay.web.controller.member;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yiranpay.common.annotation.Log;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.core.page.TableDataInfo;
import com.yiranpay.common.enums.BusinessType;
import com.yiranpay.common.utils.StrUtils;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.member.constant.FieldLength;
import com.yiranpay.member.constant.MaConstant;
import com.yiranpay.member.domain.AccountDomain;
import com.yiranpay.member.domain.CompanyMember;
import com.yiranpay.member.domain.MemberAndAccount;
import com.yiranpay.member.domain.MemberTmMember;
import com.yiranpay.member.domain.MemberTmMemberIdentity;
import com.yiranpay.member.domain.MemberTmMerchant;
import com.yiranpay.member.domain.MemberTmOperator;
import com.yiranpay.member.domain.MemberTrCompanyMember;
import com.yiranpay.member.domain.PersonalMember;
import com.yiranpay.member.enums.ActivateStatusEnum;
import com.yiranpay.member.enums.MemberAccountStatusEnum;
import com.yiranpay.member.enums.MemberStatusEnum;
import com.yiranpay.member.enums.ResponseCode;
import com.yiranpay.member.exception.MaBizException;
import com.yiranpay.member.mapper.MemberTmMemberIdentityMapper;
import com.yiranpay.member.mapper.MemberTmMemberMapper;
import com.yiranpay.member.mapper.MemberTmMerchantMapper;
import com.yiranpay.member.mapper.MemberTrCompanyMemberMapper;
import com.yiranpay.member.service.IMemberSequenceService;
import com.yiranpay.member.service.IMemberTmMemberService;
import com.yiranpay.member.service.IMemberTmOperatorService;
import com.yiranpay.member.service.IMemberTrCompanyMemberService;
import com.yiranpay.member.service.IMemberTrMemberAccountService;
import com.yiranpay.member.utils.AccountDomainUtil;
import com.yiranpay.member.utils.MemberDomainUtil;
import com.yiranpay.member.utils.MemberTypeUtil;
import com.yiranpay.member.utils.OperatorDomainUtil;
import com.yiranpay.member.utils.SQLExceptionUtil;
import com.yiranpay.payorder.enums.EncryptType;
import com.yiranpay.payorder.service.IUesServiceClient;
/**
 * 企业会员 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Controller
@RequestMapping("/member/memberTrCompanyMember")
public class MemberTrCompanyMemberController extends BaseController
{
    private String prefix = "member/memberTrCompanyMember";
	
	@Autowired
	private IMemberTrCompanyMemberService memberTrCompanyMemberService;
	
	@Autowired
	private MemberTmMemberMapper memberTmMemberMapper;
	@Autowired
	private IMemberTmOperatorService memberTmOperatorService;
	@Autowired
	private MemberTrCompanyMemberMapper memberTrCompanyMemberMapper;
	@Autowired
	private IMemberSequenceService memberSequenceService;
	@Autowired
	private IMemberTrMemberAccountService memberTrMemberAccountService;
	@Autowired
	private MemberTmMerchantMapper memberTmMerchantMapper;
	@Autowired
	private MemberTmMemberIdentityMapper memberTmMemberIdentityMapper;
	@Autowired
	private IUesServiceClient uesServiceClient;
	
	@RequiresPermissions("member:memberTrCompanyMember:view")
	@GetMapping()
	public String memberTrCompanyMember()
	{
	    return prefix + "/memberTrCompanyMember";
	}
	
	/**
	 * 查询企业会员列表
	 */
	@RequiresPermissions("member:memberTrCompanyMember:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberTrCompanyMember memberTrCompanyMember)
	{
		startPage();
        List<MemberTrCompanyMember> list = memberTrCompanyMemberService.selectMemberTrCompanyMemberList(memberTrCompanyMember);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出企业会员列表
	 */
	@RequiresPermissions("member:memberTrCompanyMember:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTrCompanyMember memberTrCompanyMember)
    {
    	List<MemberTrCompanyMember> list = memberTrCompanyMemberService.selectMemberTrCompanyMemberList(memberTrCompanyMember);
        ExcelUtil<MemberTrCompanyMember> util = new ExcelUtil<MemberTrCompanyMember>(MemberTrCompanyMember.class);
        return util.exportExcel(list, "memberTrCompanyMember");
    }
	
	/**
	 * 新增企业会员
	 */
	@GetMapping("/add")
	public String add()
	{
		System.out.println("注册会员信息....");
	    return prefix + "/wizard";
	}
	
	/**
	 * 新增保存企业会员
	 */
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(CompanyMember companyMember) throws Exception
	{		
		System.out.println("注册商户请求参数：" + JSON.toJSONString(companyMember));
		//公司会员
		MemberTrCompanyMember member = MemberDomainUtil.convertReqToCompayMember(companyMember);
        logger.info("创建企业会员--->基础会员企业信息:"+JSON.toJSONString(member));
        MemberTmOperator operator = OperatorDomainUtil.convertReqToDefaultOperator(companyMember);
        logger.info("创建企业会员--->操作员:"+JSON.toJSONString(operator));
        if (StringUtils.isNotBlank(companyMember.getLoginPassword())) {
            String ticket = uesServiceClient.encryptData(StringUtils.trim(companyMember
                .getLoginPassword()),EncryptType.AES);
            operator.setPassword(ticket);
        } else {
            operator.setPassword(null);
        } 
        //设置账户状态
        MemberAccountStatusEnum accountStatus = MemberAccountStatusEnum.getByCode(Integer.parseInt(companyMember.getMemberAccountFlag()));
        accountStatus = accountStatus == null ? MemberAccountStatusEnum.NOTACTIVATED : accountStatus;
        MemberTmMerchant merchant = getMemberTmMerchant(companyMember);
        logger.info("创建企业会员--->设置商户信息:"+JSON.toJSONString(merchant));
        boolean haveCompanyInfo = false;
        if(null != member){
           haveCompanyInfo = true;
        }
        MemberAndAccount ma  = integratedCreateCompanyMember(member, operator, merchant, accountStatus, haveCompanyInfo);
        if(ma != null){
        	logger.info("创建企业会员--->返回结果:"+JSON.toJSONString(ma));
        	//发邮件通知用户
        	
        	
        	return toAjax(1);
        }
		return toAjax(0);
	}

	private MemberTmMerchant getMemberTmMerchant(CompanyMember companyMember){
		MemberTmMerchant merchant = new MemberTmMerchant();
		merchant.setMerchantName(companyMember.getMerchantName());
		merchant.setMerchantType(Integer.parseInt(companyMember.getMerchantType()));
		merchant.setStatus(Integer.parseInt(companyMember.getMerchatStatus()));	
		return merchant;
	}
	
	private MemberAndAccount integratedCreateCompanyMember(MemberTrCompanyMember member,
			MemberTmOperator operator, MemberTmMerchant merchant, MemberAccountStatusEnum statusEnum,
			boolean haveCompanyInfo) throws MaBizException {
		String memberId = genMemberId(member.getMemberType());
		String merchantId = genMerchantId();
        member.setMemberId(memberId);
        operator.setMemberId(memberId);
        merchant.setMerchantId(merchantId);
        //创建会员基本户信息
        if (needBasicAccount(statusEnum)) {
           generateBasicAccount(member,statusEnum);
        }
        MemberAndAccount ma = new MemberAndAccount();
        ma = integratedStore(member, operator, merchant,haveCompanyInfo);
        if (needBasicAccount(statusEnum)) {
            //开户并更新账户关系
            doOpenBasicAccount(member, ma);
        }
        return ma;
	}
	private void doOpenBasicAccount(MemberTmMember member, MemberAndAccount ma) {
        //3 调用储值
        //String accountId = memberTrMemberAccountService.openAccount(member.getBaseAccount());
        //4 更新账户关系，会员标识设置为激活
        //ma.setAccountId(accountId);
        member.getBaseAccount().setAccountId(ma.getAccountId());
        reStoreBaseAccount(member.getBaseAccount());
    }
	private void reStoreBaseAccount(AccountDomain baseAccount) {
		
		int i = memberTmMemberMapper.updateActiveTime(MemberStatusEnum.NORMAL.getCode().intValue(), baseAccount.getMemberId());
		 if (i == 0) {
	            try {
					throw new MaBizException(ResponseCode.MEMBER_ACTIVE_FAIL);
				} catch (MaBizException e) {
					e.printStackTrace();
				}
	        }
	}
	private MemberAndAccount integratedStore(MemberTrCompanyMember member, MemberTmOperator defaultOperator,
			MemberTmMerchant merchant, boolean haveCompanyInfo) throws MaBizException {
		MemberAndAccount ma = new MemberAndAccount();
        //创建会员标识
        try {
            this.createIdentity(member);
        } catch (Exception e) {
            logger.warn("创建会员标志异常", e);
            if (SQLExceptionUtil.isUniqueException(e)) {
                throw new MaBizException(ResponseCode.MEMBER_IDENTITY_EXIST);
            } else {
                throw new MaBizException(ResponseCode.MEMBER_CREATE_FAIL, e.getMessage());
            }
        }
        //创建会员
        memberTmMemberMapper.insertMemberTmMember(MemberDomainUtil.convertToMemberDO(member));
        //创建操作员（登录名密码）
        memberTmOperatorService.store(defaultOperator);
        //创建账户关系
        if ((member.getAccounts() != null || member.getAccounts().size() > 0)) {
        	//新增一张账户明细表
        	int accountId = memberTrMemberAccountService.insertMemberTrMemberAccount(member.getBaseAccount());
        	ma.setAccountId(String.valueOf(accountId));
        }
        //企业信息
        if(haveCompanyInfo){
            //保存企业信息
           
        	memberTrCompanyMemberMapper.insertMemberTrCompanyMember(member);
        }
        //商户信息
        if(null != merchant){
            merchant.setMemberId(member.getMemberId());
            memberTmMerchantMapper.insertMemberTmMerchant(merchant);
            ma.setMerchantId(merchant.getMerchantId());
        }
        ma.setMemberId(member.getMemberId());
        ma.setOperatorId(defaultOperator.getOperatorId());
        return ma;
	}
	private void createIdentity(MemberTmMember member) {
		List<MemberTmMemberIdentity> identitys = MemberDomainUtil.convertToMemberIdentityDO(member);
        for (MemberTmMemberIdentity item : identitys) {
        	memberTmMemberIdentityMapper.insertMemberTmMemberIdentity(item);
        }
		
	}
	/**
	 * 根据类型获取会员ID
	 * @param memberType
	 * @return
	 */
	private String genMemberId(Integer memberType) {

        String pre = null;
        String seq = memberSequenceService.getMenberSequenceNo("SEQ_MEMBER_ID");

        if (MemberTypeUtil.isCompanyMemberType(memberType)) {
            pre = MaConstant.PRE_MEMBER_COMPANY_ID;
        } else if (MemberTypeUtil.isPersonMemberType(memberType)) {
            pre = MaConstant.PRE_MEMBER_PERSONAL_ID;
        } else if (MemberTypeUtil.isVirtualMemberType(memberType)){
        	pre = MaConstant.PRE_MEMBER_VIRUTLMERCHANT_ID;
        }else if (MemberTypeUtil.isVirtualMemberType(memberType)){
        	pre = MaConstant.PRE_MERCHANT_ID;
        }else {
            pre = MaConstant.PRE_MEMBER_INSTITUTION_ID;
        }
        String memberId = pre
                          + StrUtils.alignRight(seq, MaConstant.MEMBER_ID_SEQ_LENGTH,
                              MaConstant.ID_FIX_CHAR);

        return memberId;
    }
	private boolean needBasicAccount(MemberAccountStatusEnum statusEnum) {
        return (MemberAccountStatusEnum.ACTIVATED == statusEnum) || (MemberAccountStatusEnum.ACTIVATED_ALL == statusEnum);
    }
	
	 private void generateBasicAccount(PersonalMember member, MemberAccountStatusEnum statusEnum) {
	        ActivateStatusEnum activate = null;
	        if(MemberAccountStatusEnum.ACTIVATED_ALL == statusEnum){
	            activate = ActivateStatusEnum.ACTIVATED;
	        }else{
	            activate = ActivateStatusEnum.NOTACTIVATED;
	        }
	        AccountDomain accountDomain = AccountDomainUtil.buildOpenBaseAccountRequest(member,activate);
	        member.addAccount(accountDomain);
	    }
	 
	 private void generateBasicAccount(MemberTrCompanyMember member, MemberAccountStatusEnum statusEnum) {
	        ActivateStatusEnum activate = null;
	        if(MemberAccountStatusEnum.ACTIVATED_ALL == statusEnum){
	            activate = ActivateStatusEnum.ACTIVATED;
	        }else{
	            activate = ActivateStatusEnum.NOTACTIVATED;
	        }
	        AccountDomain accountDomain = AccountDomainUtil.buildOpenBaseAccountRequest(member,activate);
	        member.addAccount(accountDomain);
	    }
	private String genMerchantId() {
		String prefix = MaConstant.PRE_MERCHANT_ID;
		int seqLen = FieldLength.MERCHANT_ID - prefix.length();
		String merchantId = prefix
				+ StrUtils.alignRight(
						String.valueOf(memberSequenceService.getMenberSequenceNo("SEQ_MERCHANT_ID")),
						seqLen, MaConstant.ID_FIX_CHAR);
		return merchantId;
	}
	/**
	 * 修改企业会员
	 */
	@GetMapping("/edit/{memberId}")
	public String edit(@PathVariable("memberId") String memberId, ModelMap mmap)
	{
		MemberTrCompanyMember memberTrCompanyMember = memberTrCompanyMemberService.selectMemberTrCompanyMemberById(memberId);
		mmap.put("memberTrCompanyMember", memberTrCompanyMember);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存企业会员
	 */
	@RequiresPermissions("member:memberTrCompanyMember:edit")
	@Log(title = "企业会员", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberTrCompanyMember memberTrCompanyMember)
	{		
		return toAjax(memberTrCompanyMemberService.updateMemberTrCompanyMember(memberTrCompanyMember));
	}
	
	/**
	 * 删除企业会员
	 */
	@RequiresPermissions("member:memberTrCompanyMember:remove")
	@Log(title = "企业会员", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(memberTrCompanyMemberService.deleteMemberTrCompanyMemberByIds(ids));
	}
	
}
