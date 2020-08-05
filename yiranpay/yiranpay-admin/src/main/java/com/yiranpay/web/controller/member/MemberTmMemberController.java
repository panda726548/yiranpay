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
import com.yiranpay.member.domain.CompanyMember;
import com.yiranpay.member.domain.MemberTmMember;
import com.yiranpay.member.mapper.MemberTmMemberIdentityMapper;
import com.yiranpay.member.mapper.MemberTmMemberMapper;
import com.yiranpay.member.mapper.MemberTmMerchantMapper;
import com.yiranpay.member.mapper.MemberTrCompanyMemberMapper;
import com.yiranpay.member.service.IMemberSequenceService;
import com.yiranpay.member.service.IMemberTmMemberService;
import com.yiranpay.member.service.IMemberTmOperatorService;
import com.yiranpay.member.service.IMemberTrMemberAccountService;
import com.yiranpay.payorder.service.IUesServiceClient;
import com.yiranpay.common.utils.poi.ExcelUtil;
/**
 * 会员 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Controller
@RequestMapping("/member/memberTmMember")
public class MemberTmMemberController extends BaseController
{
    private String prefix = "member/memberTmMember";
	
	@Autowired
	private IMemberTmMemberService memberTmMemberService;
	
	@RequiresPermissions("member:memberTmMember:view")
	@GetMapping()
	public String memberTmMember()
	{
	    return prefix + "/memberTmMember";
	}
	
	/**
	 * 查询会员列表
	 */
	@RequiresPermissions("member:memberTmMember:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberTmMember memberTmMember)
	{
		startPage();
        List<MemberTmMember> list = memberTmMemberService.selectMemberTmMemberList(memberTmMember);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出会员列表
	 */
	@RequiresPermissions("member:memberTmMember:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTmMember memberTmMember)
    {
    	List<MemberTmMember> list = memberTmMemberService.selectMemberTmMemberList(memberTmMember);
        ExcelUtil<MemberTmMember> util = new ExcelUtil<MemberTmMember>(MemberTmMember.class);
        return util.exportExcel(list, "memberTmMember");
    }
	
	/**
	 * 新增会员
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存会员
	 * @throws Exception 
	 */
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(CompanyMember companyMember) throws Exception
	{		
		
		return null;
	}
	
	
	/**
	 * 修改会员
	 */
	@GetMapping("/edit/{memberId}")
	public String edit(@PathVariable("memberId") String memberId, ModelMap mmap)
	{
		MemberTmMember memberTmMember = memberTmMemberService.selectMemberTmMemberById(memberId);
		mmap.put("memberTmMember", memberTmMember);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存会员
	 */
	@RequiresPermissions("member:memberTmMember:edit")
	@Log(title = "会员", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberTmMember memberTmMember)
	{		
		return toAjax(memberTmMemberService.updateMemberTmMember(memberTmMember));
	}
	
	/**
	 * 删除会员
	 */
	@RequiresPermissions("member:memberTmMember:remove")
	@Log(title = "会员", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(memberTmMemberService.deleteMemberTmMemberByIds(ids));
	}
	
}
