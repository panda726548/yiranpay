package com.yiranpay.web.controller.reconciliation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.yiranpay.amqp.domain.MqExchanges;
import com.yiranpay.amqp.domain.MqQueue;
import com.yiranpay.common.annotation.Log;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.core.page.TableDataInfo;
import com.yiranpay.common.enums.BusinessType;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.reconciliation.domain.ReconciliationAccountCheckBatch;
import com.yiranpay.reconciliation.domain.ReconciliationAccountflow;
import com.yiranpay.reconciliation.service.IReconciliationAccountCheckBatchService;
import com.yiranpay.reconciliation.service.IReconciliationAccountflowService;
/**
 * 对账批次 信息操作处理
 * 
 * @author yiran
 * @date 2019-09-20
 */
@Controller
@RequestMapping("/reconciliation/reconciliationAccountCheckBatch")
public class ReconciliationAccountCheckBatchController extends BaseController
{
    private String prefix = "reconciliation/reconciliationAccountCheckBatch";
	
	@Autowired
	private IReconciliationAccountCheckBatchService reconciliationAccountCheckBatchService;
	
	@Autowired
	private IReconciliationAccountflowService reconciliationAccountflowService;
	 
	@RequiresPermissions("reconciliation:reconciliationAccountCheckBatch:view")
	@GetMapping()
	public String reconciliationAccountCheckBatch()
	{
	    return prefix + "/reconciliationAccountCheckBatch";
	}
	
	/**
	 * 查询对账批次列表
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckBatch:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ReconciliationAccountCheckBatch reconciliationAccountCheckBatch)
	{
		startPage();
        List<ReconciliationAccountCheckBatch> list = reconciliationAccountCheckBatchService.selectReconciliationAccountCheckBatchList(reconciliationAccountCheckBatch);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出对账批次列表
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckBatch:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ReconciliationAccountCheckBatch reconciliationAccountCheckBatch)
    {
    	List<ReconciliationAccountCheckBatch> list = reconciliationAccountCheckBatchService.selectReconciliationAccountCheckBatchList(reconciliationAccountCheckBatch);
        ExcelUtil<ReconciliationAccountCheckBatch> util = new ExcelUtil<ReconciliationAccountCheckBatch>(ReconciliationAccountCheckBatch.class);
        return util.exportExcel(list, "reconciliationAccountCheckBatch");
    }
	
	/**
	 * 新增对账批次
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存对账批次
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckBatch:add")
	@Log(title = "对账批次", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ReconciliationAccountCheckBatch reconciliationAccountCheckBatch)
	{		
		return toAjax(reconciliationAccountCheckBatchService.insertReconciliationAccountCheckBatch(reconciliationAccountCheckBatch));
	}

	/**
	 * 修改对账批次
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ReconciliationAccountCheckBatch reconciliationAccountCheckBatch = reconciliationAccountCheckBatchService.selectReconciliationAccountCheckBatchById(id);
		mmap.put("bach", reconciliationAccountCheckBatch);
	    return prefix + "/show";
	}
	
	/**
	 * 修改保存对账批次
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckBatch:edit")
	@Log(title = "对账批次", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ReconciliationAccountCheckBatch reconciliationAccountCheckBatch)
	{		
		return toAjax(reconciliationAccountCheckBatchService.updateReconciliationAccountCheckBatch(reconciliationAccountCheckBatch));
	}
	
	/**
	 * 删除对账批次
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckBatch:remove")
	@Log(title = "对账批次", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(reconciliationAccountCheckBatchService.deleteReconciliationAccountCheckBatchByIds(ids));
	}
	
	@GetMapping("/showdetail/{id}")
	public String showQueues(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ReconciliationAccountCheckBatch batch = reconciliationAccountCheckBatchService.selectReconciliationAccountCheckBatchById(id);
		//获取对账批次号
		String compareBatchNo = batch.getBatchNo();
		//获取对账日期
		Date compareDate = batch.getBillDate();
		//获取对账渠道号
		String fundsChannel = batch.getBankType();
		ReconciliationAccountflow accountflow = new ReconciliationAccountflow();
		accountflow.setCompareBatchNo(compareBatchNo);
		accountflow.setAccountDate(new SimpleDateFormat("yyyy-MM-dd").format(compareDate));
		accountflow.setFundsChannel(fundsChannel);
		List<ReconciliationAccountflow> accountflowList = reconciliationAccountflowService.selectReconciliationAccountflowList(accountflow);
		mmap.put("accountflowList", accountflowList);
	    return prefix + "/accountflow";
	}
}
