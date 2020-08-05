package com.yiranpay.web.controller.reconciliation;

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
import com.yiranpay.reconciliation.domain.ReconciliationAccountCheckMistake;
import com.yiranpay.reconciliation.service.IReconciliationAccountCheckMistakeService;
/**
 * 对账差错 信息操作处理
 * 
 * @author yiran
 * @date 2019-09-20
 */
@Controller
@RequestMapping("/reconciliation/reconciliationAccountCheckMistake")
public class ReconciliationAccountCheckMistakeController extends BaseController
{
    private String prefix = "reconciliation/reconciliationAccountCheckMistake";
	
	@Autowired
	private IReconciliationAccountCheckMistakeService reconciliationAccountCheckMistakeService;
	
	@RequiresPermissions("reconciliation:reconciliationAccountCheckMistake:view")
	@GetMapping()
	public String reconciliationAccountCheckMistake()
	{
	    return prefix + "/reconciliationAccountCheckMistake";
	}
	
	/**
	 * 查询对账差错列表
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckMistake:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ReconciliationAccountCheckMistake reconciliationAccountCheckMistake)
	{
		startPage();
        List<ReconciliationAccountCheckMistake> list = reconciliationAccountCheckMistakeService.selectReconciliationAccountCheckMistakeList(reconciliationAccountCheckMistake);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出对账差错列表
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckMistake:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ReconciliationAccountCheckMistake reconciliationAccountCheckMistake)
    {
    	List<ReconciliationAccountCheckMistake> list = reconciliationAccountCheckMistakeService.selectReconciliationAccountCheckMistakeList(reconciliationAccountCheckMistake);
        ExcelUtil<ReconciliationAccountCheckMistake> util = new ExcelUtil<ReconciliationAccountCheckMistake>(ReconciliationAccountCheckMistake.class);
        return util.exportExcel(list, "reconciliationAccountCheckMistake");
    }
	
	/**
	 * 新增对账差错
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存对账差错
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckMistake:add")
	@Log(title = "对账差错", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ReconciliationAccountCheckMistake reconciliationAccountCheckMistake)
	{		
		return toAjax(reconciliationAccountCheckMistakeService.insertReconciliationAccountCheckMistake(reconciliationAccountCheckMistake));
	}

	/**
	 * 修改对账差错
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ReconciliationAccountCheckMistake reconciliationAccountCheckMistake = reconciliationAccountCheckMistakeService.selectReconciliationAccountCheckMistakeById(id);
		mmap.put("reconciliationAccountCheckMistake", reconciliationAccountCheckMistake);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存对账差错
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckMistake:edit")
	@Log(title = "对账差错", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ReconciliationAccountCheckMistake reconciliationAccountCheckMistake){
		return toAjax(reconciliationAccountCheckMistakeService.updateReconciliationAccountCheckMistake(reconciliationAccountCheckMistake));
	}
	
	/**
	 * 删除对账差错
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckMistake:remove")
	@Log(title = "对账差错", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(reconciliationAccountCheckMistakeService.deleteReconciliationAccountCheckMistakeByIds(ids));
	}
	
}
