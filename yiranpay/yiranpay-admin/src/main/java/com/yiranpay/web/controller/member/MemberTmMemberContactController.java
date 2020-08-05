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
import com.yiranpay.member.domain.MemberTmMemberContact;
import com.yiranpay.member.service.IMemberTmMemberContactService;
/**
 * 会员账户 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Controller
@RequestMapping("/member/memberTmMemberContact")
public class MemberTmMemberContactController extends BaseController
{
    private String prefix = "member/memberTmMemberContact";
	
	@Autowired
	private IMemberTmMemberContactService memberTmMemberContactService;
	
	@RequiresPermissions("member:memberTmMemberContact:view")
	@GetMapping()
	public String memberTmMemberContact()
	{
	    return prefix + "/memberTmMemberContact";
	}
	
	/**
	 * 查询会员账户列表
	 */
	@RequiresPermissions("member:memberTmMemberContact:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberTmMemberContact memberTmMemberContact)
	{
		startPage();
        List<MemberTmMemberContact> list = memberTmMemberContactService.selectMemberTmMemberContactList(memberTmMemberContact);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出会员账户列表
	 */
	@RequiresPermissions("member:memberTmMemberContact:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTmMemberContact memberTmMemberContact)
    {
    	List<MemberTmMemberContact> list = memberTmMemberContactService.selectMemberTmMemberContactList(memberTmMemberContact);
        ExcelUtil<MemberTmMemberContact> util = new ExcelUtil<MemberTmMemberContact>(MemberTmMemberContact.class);
        return util.exportExcel(list, "memberTmMemberContact");
    }
	
	/**
	 * 新增会员账户
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存会员账户
	 */
	@RequiresPermissions("member:memberTmMemberContact:add")
	@Log(title = "会员账户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MemberTmMemberContact memberTmMemberContact)
	{	
		return toAjax(memberTmMemberContactService.insertMemberTmMemberContact(memberTmMemberContact));
	}

	/**
	 * 修改会员账户
	 */
	@GetMapping("/edit/{contactId}")
	public String edit(@PathVariable("contactId") Integer contactId, ModelMap mmap)
	{
		MemberTmMemberContact memberTmMemberContact = memberTmMemberContactService.selectMemberTmMemberContactById(contactId);
		mmap.put("memberTmMemberContact", memberTmMemberContact);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存会员账户
	 */
	@RequiresPermissions("member:memberTmMemberContact:edit")
	@Log(title = "会员账户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberTmMemberContact memberTmMemberContact)
	{		
		return toAjax(memberTmMemberContactService.updateMemberTmMemberContact(memberTmMemberContact));
	}
	
	/**
	 * 删除会员账户
	 */
	@RequiresPermissions("member:memberTmMemberContact:remove")
	@Log(title = "会员账户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(memberTmMemberContactService.deleteMemberTmMemberContactByIds(ids));
	}
	
}
