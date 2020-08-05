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
import com.yiranpay.member.domain.MemberTmMerchant;
import com.yiranpay.member.service.IMemberTmMerchantService;
/**
 * 商户 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Controller
@RequestMapping("/member/memberTmMerchant")
public class MemberTmMerchantController extends BaseController
{
    private String prefix = "member/memberTmMerchant";
	
	@Autowired
	private IMemberTmMerchantService memberTmMerchantService;
	
	@RequiresPermissions("member:memberTmMerchant:view")
	@GetMapping()
	public String memberTmMerchant()
	{
	    return prefix + "/memberTmMerchant";
	}
	
	/**
	 * 查询商户列表
	 */
	@RequiresPermissions("member:memberTmMerchant:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberTmMerchant memberTmMerchant)
	{
		startPage();
        List<MemberTmMerchant> list = memberTmMerchantService.selectMemberTmMerchantList(memberTmMerchant);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出商户列表
	 */
	@RequiresPermissions("member:memberTmMerchant:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTmMerchant memberTmMerchant)
    {
    	List<MemberTmMerchant> list = memberTmMerchantService.selectMemberTmMerchantList(memberTmMerchant);
        ExcelUtil<MemberTmMerchant> util = new ExcelUtil<MemberTmMerchant>(MemberTmMerchant.class);
        return util.exportExcel(list, "memberTmMerchant");
    }
	
	/**
	 * 新增商户
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商户
	 */
	@RequiresPermissions("member:memberTmMerchant:add")
	@Log(title = "商户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MemberTmMerchant memberTmMerchant)
	{		
		return toAjax(memberTmMerchantService.insertMemberTmMerchant(memberTmMerchant));
	}

	/**
	 * 修改商户
	 */
	@GetMapping("/edit/{merchantId}")
	public String edit(@PathVariable("merchantId") String merchantId, ModelMap mmap)
	{
		MemberTmMerchant memberTmMerchant = memberTmMerchantService.selectMemberTmMerchantById(merchantId);
		mmap.put("memberTmMerchant", memberTmMerchant);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商户
	 */
	@RequiresPermissions("member:memberTmMerchant:edit")
	@Log(title = "商户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberTmMerchant memberTmMerchant)
	{		
		return toAjax(memberTmMerchantService.updateMemberTmMerchant(memberTmMerchant));
	}
	
	/**
	 * 删除商户
	 */
	@RequiresPermissions("member:memberTmMerchant:remove")
	@Log(title = "商户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(memberTmMerchantService.deleteMemberTmMerchantByIds(ids));
	}
	
}
