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
import com.yiranpay.member.domain.MemberTrMemberAccount;
import com.yiranpay.member.service.IMemberTrMemberAccountService;
/**
 * 会员账户 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Controller
@RequestMapping("/member/memberTrMemberAccount")
public class MemberTrMemberAccountController extends BaseController
{
    private String prefix = "member/memberTrMemberAccount";
	
	@Autowired
	private IMemberTrMemberAccountService memberTrMemberAccountService;
	
	@RequiresPermissions("member:memberTrMemberAccount:view")
	@GetMapping()
	public String memberTrMemberAccount()
	{
	    return prefix + "/memberTrMemberAccount";
	}
	
	/**
	 * 查询会员账户列表
	 */
	@RequiresPermissions("member:memberTrMemberAccount:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberTrMemberAccount memberTrMemberAccount)
	{
		startPage();
        List<MemberTrMemberAccount> list = memberTrMemberAccountService.selectMemberTrMemberAccountList(memberTrMemberAccount);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出会员账户列表
	 */
	@RequiresPermissions("member:memberTrMemberAccount:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTrMemberAccount memberTrMemberAccount)
    {
    	List<MemberTrMemberAccount> list = memberTrMemberAccountService.selectMemberTrMemberAccountList(memberTrMemberAccount);
        ExcelUtil<MemberTrMemberAccount> util = new ExcelUtil<MemberTrMemberAccount>(MemberTrMemberAccount.class);
        return util.exportExcel(list, "memberTrMemberAccount");
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
	@RequiresPermissions("member:memberTrMemberAccount:add")
	@Log(title = "会员账户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MemberTrMemberAccount memberTrMemberAccount)
	{		
		return toAjax(memberTrMemberAccountService.insertMemberTrMemberAccount(memberTrMemberAccount));
	}

	/**
	 * 修改会员账户
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MemberTrMemberAccount memberTrMemberAccount = memberTrMemberAccountService.selectMemberTrMemberAccountById(id);
		mmap.put("memberTrMemberAccount", memberTrMemberAccount);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存会员账户
	 */
	@RequiresPermissions("member:memberTrMemberAccount:edit")
	@Log(title = "会员账户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberTrMemberAccount memberTrMemberAccount)
	{		
		return toAjax(memberTrMemberAccountService.updateMemberTrMemberAccount(memberTrMemberAccount));
	}
	
	/**
	 * 删除会员账户
	 */
	@RequiresPermissions("member:memberTrMemberAccount:remove")
	@Log(title = "会员账户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(memberTrMemberAccountService.deleteMemberTrMemberAccountByIds(ids));
	}
	
}
