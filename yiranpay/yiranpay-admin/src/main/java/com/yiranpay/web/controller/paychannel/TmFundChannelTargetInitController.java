package com.yiranpay.web.controller.paychannel;

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
import com.yiranpay.paychannel.domain.TmFundChannelTargetInit;
import com.yiranpay.paychannel.service.ITmFundChannelTargetInitService;
import com.yiranpay.common.config.Global;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.core.page.TableDataInfo;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.framework.util.ShiroUtils;
/**
 * 提供资金渠道目标机构 信息操作处理
 * 
 * @author yiran
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/paychannel/tmFundChannelTargetInit")
public class TmFundChannelTargetInitController extends BaseController
{
    private String prefix = "paychannel/tmFundChannelTargetInit";
	
	@Autowired
	private ITmFundChannelTargetInitService tmFundChannelTargetInitService;
	
	@RequiresPermissions("paychannel:tmFundChannelTargetInit:view")
	@GetMapping()
	public String tmFundChannelTargetInit()
	{
	    return prefix + "/tmFundChannelTargetInit";
	}
	
	/**
	 * 查询提供资金渠道目标机构列表
	 */
	@RequiresPermissions("paychannel:tmFundChannelTargetInit:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TmFundChannelTargetInit tmFundChannelTargetInit)
	{
		startPage();
        List<TmFundChannelTargetInit> list = tmFundChannelTargetInitService.selectTmFundChannelTargetInitList(tmFundChannelTargetInit);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出提供资金渠道目标机构列表
	 */
	@RequiresPermissions("paychannel:tmFundChannelTargetInit:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TmFundChannelTargetInit tmFundChannelTargetInit)
    {
    	List<TmFundChannelTargetInit> list = tmFundChannelTargetInitService.selectTmFundChannelTargetInitList(tmFundChannelTargetInit);
        ExcelUtil<TmFundChannelTargetInit> util = new ExcelUtil<TmFundChannelTargetInit>(TmFundChannelTargetInit.class);
        return util.exportExcel(list, "tmFundChannelTargetInit");
    }
	
	/**
	 * 新增提供资金渠道目标机构
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存提供资金渠道目标机构
	 */
	@RequiresPermissions("paychannel:tmFundChannelTargetInit:add")
	@Log(title = "提供资金渠道目标机构", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TmFundChannelTargetInit tmFundChannelTargetInit)
	{		
		return toAjax(tmFundChannelTargetInitService.insertTmFundChannelTargetInit(tmFundChannelTargetInit));
	}

	/**
	 * 修改提供资金渠道目标机构
	 */
	@GetMapping("/edit/{targetInstCode}")
	public String edit(@PathVariable("targetInstCode") String targetInstCode, ModelMap mmap)
	{
		TmFundChannelTargetInit tmFundChannelTargetInit = tmFundChannelTargetInitService.selectTmFundChannelTargetInitById(targetInstCode);
		mmap.put("tmFundChannelTargetInit", tmFundChannelTargetInit);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存提供资金渠道目标机构
	 */
	@RequiresPermissions("paychannel:tmFundChannelTargetInit:edit")
	@Log(title = "提供资金渠道目标机构", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TmFundChannelTargetInit tmFundChannelTargetInit)
	{		
		return toAjax(tmFundChannelTargetInitService.updateTmFundChannelTargetInit(tmFundChannelTargetInit));
	}
	
	/**
	 * 删除提供资金渠道目标机构
	 */
	@RequiresPermissions("paychannel:tmFundChannelTargetInit:remove")
	@Log(title = "提供资金渠道目标机构", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tmFundChannelTargetInitService.deleteTmFundChannelTargetInitByIds(ids));
	}
	
}
