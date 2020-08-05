package com.yiranpay.web.controller.payorder;

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
import com.yiranpay.payorder.domain.PayMonitorLog;
import com.yiranpay.payorder.service.IPayMonitorLogService;
/**
 * 监控日志 信息操作处理
 * 
 * @author yiran
 * @date 2019-07-13
 */
@Controller
@RequestMapping("/payorder/payMonitorLog")
public class PayMonitorLogController extends BaseController
{
    private String prefix = "payorder/payMonitorLog";
	
	@Autowired
	private IPayMonitorLogService payMonitorLogService;
	
	@RequiresPermissions("payorder:payMonitorLog:view")
	@GetMapping()
	public String payMonitorLog()
	{
	    return prefix + "/payMonitorLog";
	}
	
	/**
	 * 查询监控日志列表
	 */
	@RequiresPermissions("payorder:payMonitorLog:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PayMonitorLog payMonitorLog)
	{
		startPage();
        List<PayMonitorLog> list = payMonitorLogService.selectPayMonitorLogList(payMonitorLog);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出监控日志列表
	 */
	@RequiresPermissions("payorder:payMonitorLog:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PayMonitorLog payMonitorLog)
    {
    	List<PayMonitorLog> list = payMonitorLogService.selectPayMonitorLogList(payMonitorLog);
        ExcelUtil<PayMonitorLog> util = new ExcelUtil<PayMonitorLog>(PayMonitorLog.class);
        return util.exportExcel(list, "payMonitorLog");
    }
	
	/**
	 * 新增监控日志
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存监控日志
	 */
	@RequiresPermissions("payorder:payMonitorLog:add")
	@Log(title = "监控日志", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PayMonitorLog payMonitorLog)
	{		
		return toAjax(payMonitorLogService.insertPayMonitorLog(payMonitorLog));
	}

	/**
	 * 修改监控日志
	 */
	@GetMapping("/edit/{logId}")
	public String edit(@PathVariable("logId") Integer logId, ModelMap mmap)
	{
		PayMonitorLog payMonitorLog = payMonitorLogService.selectPayMonitorLogById(logId);
		mmap.put("payMonitorLog", payMonitorLog);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存监控日志
	 */
	@RequiresPermissions("payorder:payMonitorLog:edit")
	@Log(title = "监控日志", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PayMonitorLog payMonitorLog)
	{		
		return toAjax(payMonitorLogService.updatePayMonitorLog(payMonitorLog));
	}
	
	/**
	 * 删除监控日志
	 */
	@RequiresPermissions("payorder:payMonitorLog:remove")
	@Log(title = "监控日志", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(payMonitorLogService.deletePayMonitorLogByIds(ids));
	}
	
}
