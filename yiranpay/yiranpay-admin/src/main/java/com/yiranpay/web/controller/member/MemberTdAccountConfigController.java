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
import com.yiranpay.member.domain.MemberTdAccountConfig;
import com.yiranpay.member.service.IMemberTdAccountConfigService;
/**
 * 账户配置 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Controller
@RequestMapping("/member/memberTdAccountConfig")
public class MemberTdAccountConfigController extends BaseController
{
    private String prefix = "member/memberTdAccountConfig";
	
	@Autowired
	private IMemberTdAccountConfigService memberTdAccountConfigService;
	
	@RequiresPermissions("member:memberTdAccountConfig:view")
	@GetMapping()
	public String memberTdAccountConfig()
	{
	    return prefix + "/memberTdAccountConfig";
	}
	
	/**
	 * 查询账户配置列表
	 */
	@RequiresPermissions("member:memberTdAccountConfig:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberTdAccountConfig memberTdAccountConfig)
	{
		startPage();
        List<MemberTdAccountConfig> list = memberTdAccountConfigService.selectMemberTdAccountConfigList(memberTdAccountConfig);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出账户配置列表
	 */
	@RequiresPermissions("member:memberTdAccountConfig:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTdAccountConfig memberTdAccountConfig)
    {
    	List<MemberTdAccountConfig> list = memberTdAccountConfigService.selectMemberTdAccountConfigList(memberTdAccountConfig);
        ExcelUtil<MemberTdAccountConfig> util = new ExcelUtil<MemberTdAccountConfig>(MemberTdAccountConfig.class);
        return util.exportExcel(list, "memberTdAccountConfig");
    }
	
	/**
	 * 新增账户配置
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存账户配置
	 */
	@RequiresPermissions("member:memberTdAccountConfig:add")
	@Log(title = "账户配置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MemberTdAccountConfig memberTdAccountConfig)
	{		
		return toAjax(memberTdAccountConfigService.insertMemberTdAccountConfig(memberTdAccountConfig));
	}

	/**
	 * 修改账户配置
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MemberTdAccountConfig memberTdAccountConfig = memberTdAccountConfigService.selectMemberTdAccountConfigById(id);
		mmap.put("memberTdAccountConfig", memberTdAccountConfig);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存账户配置
	 */
	@RequiresPermissions("member:memberTdAccountConfig:edit")
	@Log(title = "账户配置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberTdAccountConfig memberTdAccountConfig)
	{		
		return toAjax(memberTdAccountConfigService.updateMemberTdAccountConfig(memberTdAccountConfig));
	}
	
	/**
	 * 删除账户配置
	 */
	@RequiresPermissions("member:memberTdAccountConfig:remove")
	@Log(title = "账户配置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(memberTdAccountConfigService.deleteMemberTdAccountConfigByIds(ids));
	}
	
}
