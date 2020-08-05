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
import com.yiranpay.member.domain.MemberTmMemberIdentity;
import com.yiranpay.member.service.IMemberTmMemberIdentityService;
/**
 * 会员标识 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-31
 */
@Controller
@RequestMapping("/member/memberTmMemberIdentity")
public class MemberTmMemberIdentityController extends BaseController
{
    private String prefix = "member/memberTmMemberIdentity";
	
	@Autowired
	private IMemberTmMemberIdentityService memberTmMemberIdentityService;
	
	@RequiresPermissions("member:memberTmMemberIdentity:view")
	@GetMapping()
	public String memberTmMemberIdentity()
	{
	    return prefix + "/memberTmMemberIdentity";
	}
	
	/**
	 * 查询会员标识列表
	 */
	@RequiresPermissions("member:memberTmMemberIdentity:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberTmMemberIdentity memberTmMemberIdentity)
	{
		startPage();
        List<MemberTmMemberIdentity> list = memberTmMemberIdentityService.selectMemberTmMemberIdentityList(memberTmMemberIdentity);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出会员标识列表
	 */
	@RequiresPermissions("member:memberTmMemberIdentity:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTmMemberIdentity memberTmMemberIdentity)
    {
    	List<MemberTmMemberIdentity> list = memberTmMemberIdentityService.selectMemberTmMemberIdentityList(memberTmMemberIdentity);
        ExcelUtil<MemberTmMemberIdentity> util = new ExcelUtil<MemberTmMemberIdentity>(MemberTmMemberIdentity.class);
        return util.exportExcel(list, "memberTmMemberIdentity");
    }
	
	/**
	 * 新增会员标识
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存会员标识
	 */
	@RequiresPermissions("member:memberTmMemberIdentity:add")
	@Log(title = "会员标识", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MemberTmMemberIdentity memberTmMemberIdentity)
	{		
		return toAjax(memberTmMemberIdentityService.insertMemberTmMemberIdentity(memberTmMemberIdentity));
	}

	/**
	 * 修改会员标识
	 */
	@GetMapping("/edit/{memberId}")
	public String edit(@PathVariable("memberId") String memberId, ModelMap mmap)
	{
		MemberTmMemberIdentity memberTmMemberIdentity = memberTmMemberIdentityService.selectMemberTmMemberIdentityById(memberId);
		mmap.put("memberTmMemberIdentity", memberTmMemberIdentity);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存会员标识
	 */
	@RequiresPermissions("member:memberTmMemberIdentity:edit")
	@Log(title = "会员标识", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberTmMemberIdentity memberTmMemberIdentity)
	{		
		return toAjax(memberTmMemberIdentityService.updateMemberTmMemberIdentity(memberTmMemberIdentity));
	}
	
	/**
	 * 删除会员标识
	 */
	@RequiresPermissions("member:memberTmMemberIdentity:remove")
	@Log(title = "会员标识", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(memberTmMemberIdentityService.deleteMemberTmMemberIdentityByIds(ids));
	}
	
}
