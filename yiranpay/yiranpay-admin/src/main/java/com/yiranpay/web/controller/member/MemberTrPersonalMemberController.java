package com.yiranpay.web.controller.member;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yiranpay.common.annotation.Log;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.core.page.TableDataInfo;
import com.yiranpay.common.enums.BusinessType;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.member.domain.MemberTrPersonalMember;
import com.yiranpay.member.service.IMemberTrPersonalMemberService;
/**
 * 个人会员 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Controller
@RequestMapping("/member/memberTrPersonalMember")
public class MemberTrPersonalMemberController extends BaseController
{
    private String prefix = "member/memberTrPersonalMember";
	
	@Autowired
	private IMemberTrPersonalMemberService memberTrPersonalMemberService;
	
	@RequiresPermissions("member:memberTrPersonalMember:view")
	@GetMapping()
	public String memberTrPersonalMember()
	{
	    return prefix + "/memberTrPersonalMember";
	}
	
	/**
	 * 查询个人会员列表
	 */
	@RequiresPermissions("member:memberTrPersonalMember:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberTrPersonalMember memberTrPersonalMember)
	{
		startPage();
        List<MemberTrPersonalMember> list = memberTrPersonalMemberService.selectMemberTrPersonalMemberList(memberTrPersonalMember);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出个人会员列表
	 */
	@RequiresPermissions("member:memberTrPersonalMember:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTrPersonalMember memberTrPersonalMember)
    {
    	List<MemberTrPersonalMember> list = memberTrPersonalMemberService.selectMemberTrPersonalMemberList(memberTrPersonalMember);
        ExcelUtil<MemberTrPersonalMember> util = new ExcelUtil<MemberTrPersonalMember>(MemberTrPersonalMember.class);
        return util.exportExcel(list, "memberTrPersonalMember");
    }
	
	/**
	 * 新增个人会员
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存个人会员
	 */
	@RequiresPermissions("member:memberTrPersonalMember:add")
	@Log(title = "个人会员", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MemberTrPersonalMember memberTrPersonalMember)
	{		
		return toAjax(memberTrPersonalMemberService.insertMemberTrPersonalMember(memberTrPersonalMember));
	}

	/**
	 * 修改个人会员
	 */
	@GetMapping("/edit/{memberId}")
	public String edit(@PathVariable("memberId") String memberId, ModelMap mmap)
	{
		MemberTrPersonalMember memberTrPersonalMember = memberTrPersonalMemberService.selectMemberTrPersonalMemberById(memberId);
		mmap.put("memberTrPersonalMember", memberTrPersonalMember);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存个人会员
	 */
	@RequiresPermissions("member:memberTrPersonalMember:edit")
	@Log(title = "个人会员", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberTrPersonalMember memberTrPersonalMember)
	{		
		return toAjax(memberTrPersonalMemberService.updateMemberTrPersonalMember(memberTrPersonalMember));
	}
	
	/**
	 * 删除个人会员
	 */
	@RequiresPermissions("member:memberTrPersonalMember:remove")
	@Log(title = "个人会员", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(memberTrPersonalMemberService.deleteMemberTrPersonalMemberByIds(ids));
	}
	
}
