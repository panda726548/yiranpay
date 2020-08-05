package com.yiran.project.merchant.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yiran.common.utils.security.ShiroUtils;
import com.yiran.framework.web.controller.BaseController;
import com.yiran.project.merchant.account.domain.MemberTrCompanyMember;
import com.yiran.project.merchant.account.domain.MemberTrMemberAccount;
import com.yiran.project.merchant.account.domain.MemberTrMemberAccountDetail;
import com.yiran.project.merchant.account.service.IMemberTrCompanyMemberService;
import com.yiran.project.merchant.account.service.IMemberTrMemberAccountDetailService;
import com.yiran.project.merchant.account.service.IMemberTrMemberAccountService;
import com.yiran.project.merchant.order.domain.PayerInfo;
import com.yiran.project.merchant.order.domain.TradeOrder;
import com.yiran.project.system.user.domain.MemberTmMemberIdentity;
import com.yiran.project.system.user.service.IMemberTmMemberIdentityService;

@Controller
@RequestMapping("/merchant/companyMember")
public class CompanyMemberController extends BaseController{

	private String prefix = "merchant/companyMember";
	@Autowired
	private IMemberTrCompanyMemberService memberTrCompanyMemberService;
	@Autowired
	private IMemberTrMemberAccountService memberTrMemberAccountService;
	@Autowired
	private IMemberTrMemberAccountDetailService memberTrMemberAccountDetailService;
	@Autowired
	private IMemberTmMemberIdentityService memberTmMemberIdentityService;
	
	@GetMapping("/showinfo")
    public String show( ModelMap mmap){
		String memberId = ShiroUtils.getUserId();
		MemberTrCompanyMember companyMember = memberTrCompanyMemberService.selectMemberTrCompanyMemberById(memberId);
		MemberTrMemberAccount memberAccount = memberTrMemberAccountService.selectMemberTrMemberAccountByMemberId(memberId);
		MemberTrMemberAccountDetail accountDetail = memberTrMemberAccountDetailService.selectMemberTrMemberAccountDetailByMemberAccountId(memberAccount.getId());
		List<MemberTmMemberIdentity> list = memberTmMemberIdentityService.selectMemberTmMemberIdentityByMemberId(memberId);
		MemberTmMemberIdentity emailIdentity = null;
		for (MemberTmMemberIdentity id : list) {
			if(id.getIdentityType() == 1){
				emailIdentity = id;
			}
		}
		mmap.put("companyMember", companyMember);
    	mmap.put("memberAccount", memberAccount);
    	mmap.put("accountDetail", accountDetail);
    	mmap.put("emailIdentity", emailIdentity);
        return prefix + "/companyMemberShow";
    }
	
	
}
