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
import com.yiranpay.paychannel.domain.TmApiNoMode;
import com.yiranpay.paychannel.service.ITmApiNoModeService;
import com.yiranpay.common.config.Global;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.core.page.TableDataInfo;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.framework.util.ShiroUtils;
/**
 * 资金源接口订单号模式 信息操作处理
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Controller
@RequestMapping("/paychannel/tmApiNoMode")
public class TmApiNoModeController extends BaseController
{
    private String prefix = "paychannel/tmApiNoMode";
	
	@Autowired
	private ITmApiNoModeService tmApiNoModeService;
	
	@RequiresPermissions("paychannel:tmApiNoMode:view")
	@GetMapping()
	public String tmApiNoMode()
	{
	    return prefix + "/tmApiNoMode";
	}
	
	/**
	 * 查询资金源接口订单号模式列表
	 */
	@RequiresPermissions("paychannel:tmApiNoMode:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TmApiNoMode tmApiNoMode)
	{
		startPage();
        List<TmApiNoMode> list = tmApiNoModeService.selectTmApiNoModeList(tmApiNoMode);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出资金源接口订单号模式列表
	 */
	@RequiresPermissions("paychannel:tmApiNoMode:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TmApiNoMode tmApiNoMode)
    {
    	List<TmApiNoMode> list = tmApiNoModeService.selectTmApiNoModeList(tmApiNoMode);
        ExcelUtil<TmApiNoMode> util = new ExcelUtil<TmApiNoMode>(TmApiNoMode.class);
        return util.exportExcel(list, "tmApiNoMode");
    }
	
	/**
	 * 新增资金源接口订单号模式
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资金源接口订单号模式
	 */
	@RequiresPermissions("paychannel:tmApiNoMode:add")
	@Log(title = "资金源接口订单号模式", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TmApiNoMode tmApiNoMode)
	{		
		return toAjax(tmApiNoModeService.insertTmApiNoMode(tmApiNoMode));
	}

	/**
	 * 修改资金源接口订单号模式
	 */
	@GetMapping("/edit/{apiNoModeId}")
	public String edit(@PathVariable("apiNoModeId") Integer apiNoModeId, ModelMap mmap)
	{
		TmApiNoMode tmApiNoMode = tmApiNoModeService.selectTmApiNoModeById(apiNoModeId);
		mmap.put("tmApiNoMode", tmApiNoMode);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资金源接口订单号模式
	 */
	@RequiresPermissions("paychannel:tmApiNoMode:edit")
	@Log(title = "资金源接口订单号模式", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TmApiNoMode tmApiNoMode)
	{		
		return toAjax(tmApiNoModeService.updateTmApiNoMode(tmApiNoMode));
	}
	
	/**
	 * 删除资金源接口订单号模式
	 */
	@RequiresPermissions("paychannel:tmApiNoMode:remove")
	@Log(title = "资金源接口订单号模式", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tmApiNoModeService.deleteTmApiNoModeByIds(ids));
	}
	
}
