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
import com.yiranpay.member.domain.MemberTrPassword;
import com.yiranpay.member.service.IMemberTrPasswordService;
/**
 * 会员支付密码 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Controller
@RequestMapping("/member/memberTrPassword")
public class MemberTrPasswordController extends BaseController
{
    private String prefix = "member/memberTrPassword";
	
	@Autowired
	private IMemberTrPasswordService memberTrPasswordService;
	
	@RequiresPermissions("member:memberTrPassword:view")
	@GetMapping()
	public String memberTrPassword()
	{
	    return prefix + "/memberTrPassword";
	}
	
	/**
	 * 查询会员支付密码列表
	 */
	@RequiresPermissions("member:memberTrPassword:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberTrPassword memberTrPassword)
	{
		startPage();
        List<MemberTrPassword> list = memberTrPasswordService.selectMemberTrPasswordList(memberTrPassword);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出会员支付密码列表
	 */
	@RequiresPermissions("member:memberTrPassword:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTrPassword memberTrPassword)
    {
    	List<MemberTrPassword> list = memberTrPasswordService.selectMemberTrPasswordList(memberTrPassword);
        ExcelUtil<MemberTrPassword> util = new ExcelUtil<MemberTrPassword>(MemberTrPassword.class);
        return util.exportExcel(list, "memberTrPassword");
    }
	
	/**
	 * 新增会员支付密码
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存会员支付密码
	 */
	@RequiresPermissions("member:memberTrPassword:add")
	@Log(title = "会员支付密码", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MemberTrPassword memberTrPassword)
	{		
		return toAjax(memberTrPasswordService.insertMemberTrPassword(memberTrPassword));
	}

	/**
	 * 修改会员支付密码
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MemberTrPassword memberTrPassword = memberTrPasswordService.selectMemberTrPasswordById(id);
		mmap.put("memberTrPassword", memberTrPassword);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存会员支付密码
	 */
	@RequiresPermissions("member:memberTrPassword:edit")
	@Log(title = "会员支付密码", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberTrPassword memberTrPassword)
	{		
		return toAjax(memberTrPasswordService.updateMemberTrPassword(memberTrPassword));
	}
	
	/**
	 * 删除会员支付密码
	 */
	@RequiresPermissions("member:memberTrPassword:remove")
	@Log(title = "会员支付密码", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(memberTrPasswordService.deleteMemberTrPasswordByIds(ids));
	}
	
}
