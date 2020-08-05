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
import com.yiranpay.common.enums.BusinessType;
import com.yiranpay.member.domain.MemberTdConfig;
import com.yiranpay.member.service.IMemberTdConfigService;
import com.yiranpay.common.config.Global;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.core.page.TableDataInfo;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.framework.util.ShiroUtils;
/**
 * 配置 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Controller
@RequestMapping("/member/memberTdConfig")
public class MemberTdConfigController extends BaseController
{
    private String prefix = "member/memberTdConfig";
	
	@Autowired
	private IMemberTdConfigService memberTdConfigService;
	
	@RequiresPermissions("member:memberTdConfig:view")
	@GetMapping()
	public String memberTdConfig()
	{
	    return prefix + "/memberTdConfig";
	}
	
	/**
	 * 查询配置列表
	 */
	@RequiresPermissions("member:memberTdConfig:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberTdConfig memberTdConfig)
	{
		startPage();
        List<MemberTdConfig> list = memberTdConfigService.selectMemberTdConfigList(memberTdConfig);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出配置列表
	 */
	@RequiresPermissions("member:memberTdConfig:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTdConfig memberTdConfig)
    {
    	List<MemberTdConfig> list = memberTdConfigService.selectMemberTdConfigList(memberTdConfig);
        ExcelUtil<MemberTdConfig> util = new ExcelUtil<MemberTdConfig>(MemberTdConfig.class);
        return util.exportExcel(list, "memberTdConfig");
    }
	
	/**
	 * 新增配置
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存配置
	 */
	@RequiresPermissions("member:memberTdConfig:add")
	@Log(title = "配置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MemberTdConfig memberTdConfig)
	{		
		return toAjax(memberTdConfigService.insertMemberTdConfig(memberTdConfig));
	}

	/**
	 * 修改配置
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MemberTdConfig memberTdConfig = memberTdConfigService.selectMemberTdConfigById(id);
		mmap.put("memberTdConfig", memberTdConfig);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存配置
	 */
	@RequiresPermissions("member:memberTdConfig:edit")
	@Log(title = "配置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberTdConfig memberTdConfig)
	{		
		return toAjax(memberTdConfigService.updateMemberTdConfig(memberTdConfig));
	}
	
	/**
	 * 删除配置
	 */
	@RequiresPermissions("member:memberTdConfig:remove")
	@Log(title = "配置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(memberTdConfigService.deleteMemberTdConfigByIds(ids));
	}
	
}
