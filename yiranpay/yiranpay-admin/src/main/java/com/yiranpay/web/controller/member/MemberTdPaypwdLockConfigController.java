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
import com.yiranpay.member.domain.MemberTdPaypwdLockConfig;
import com.yiranpay.member.service.IMemberTdPaypwdLockConfigService;
/**
 * 支付密码锁定配置 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Controller
@RequestMapping("/member/memberTdPaypwdLockConfig")
public class MemberTdPaypwdLockConfigController extends BaseController
{
    private String prefix = "member/memberTdPaypwdLockConfig";
	
	@Autowired
	private IMemberTdPaypwdLockConfigService memberTdPaypwdLockConfigService;
	
	@RequiresPermissions("member:memberTdPaypwdLockConfig:view")
	@GetMapping()
	public String memberTdPaypwdLockConfig()
	{
	    return prefix + "/memberTdPaypwdLockConfig";
	}
	
	/**
	 * 查询支付密码锁定配置列表
	 */
	@RequiresPermissions("member:memberTdPaypwdLockConfig:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberTdPaypwdLockConfig memberTdPaypwdLockConfig)
	{
		startPage();
        List<MemberTdPaypwdLockConfig> list = memberTdPaypwdLockConfigService.selectMemberTdPaypwdLockConfigList(memberTdPaypwdLockConfig);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出支付密码锁定配置列表
	 */
	@RequiresPermissions("member:memberTdPaypwdLockConfig:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTdPaypwdLockConfig memberTdPaypwdLockConfig)
    {
    	List<MemberTdPaypwdLockConfig> list = memberTdPaypwdLockConfigService.selectMemberTdPaypwdLockConfigList(memberTdPaypwdLockConfig);
        ExcelUtil<MemberTdPaypwdLockConfig> util = new ExcelUtil<MemberTdPaypwdLockConfig>(MemberTdPaypwdLockConfig.class);
        return util.exportExcel(list, "memberTdPaypwdLockConfig");
    }
	
	/**
	 * 新增支付密码锁定配置
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存支付密码锁定配置
	 */
	@RequiresPermissions("member:memberTdPaypwdLockConfig:add")
	@Log(title = "支付密码锁定配置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MemberTdPaypwdLockConfig memberTdPaypwdLockConfig)
	{		
		return toAjax(memberTdPaypwdLockConfigService.insertMemberTdPaypwdLockConfig(memberTdPaypwdLockConfig));
	}

	/**
	 * 修改支付密码锁定配置
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MemberTdPaypwdLockConfig memberTdPaypwdLockConfig = memberTdPaypwdLockConfigService.selectMemberTdPaypwdLockConfigById(id);
		mmap.put("memberTdPaypwdLockConfig", memberTdPaypwdLockConfig);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存支付密码锁定配置
	 */
	@RequiresPermissions("member:memberTdPaypwdLockConfig:edit")
	@Log(title = "支付密码锁定配置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberTdPaypwdLockConfig memberTdPaypwdLockConfig)
	{		
		return toAjax(memberTdPaypwdLockConfigService.updateMemberTdPaypwdLockConfig(memberTdPaypwdLockConfig));
	}
	
	/**
	 * 删除支付密码锁定配置
	 */
	@RequiresPermissions("member:memberTdPaypwdLockConfig:remove")
	@Log(title = "支付密码锁定配置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(memberTdPaypwdLockConfigService.deleteMemberTdPaypwdLockConfigByIds(ids));
	}
	
}
