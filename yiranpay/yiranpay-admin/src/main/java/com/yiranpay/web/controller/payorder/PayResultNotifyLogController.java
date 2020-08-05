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
import com.yiranpay.payorder.domain.PayResultNotifyLog;
import com.yiranpay.payorder.service.IPayResultNotifyLogService;
/**
 * 支付结果通知日志 信息操作处理
 * 
 * @author yiran
 * @date 2019-08-14
 */
@Controller
@RequestMapping("/payorder/payResultNotifyLog")
public class PayResultNotifyLogController extends BaseController
{
    private String prefix = "payorder/payResultNotifyLog";
	
	@Autowired
	private IPayResultNotifyLogService payResultNotifyLogService;
	
	@RequiresPermissions("payorder:payResultNotifyLog:view")
	@GetMapping()
	public String payResultNotifyLog()
	{
	    return prefix + "/payResultNotifyLog";
	}
	
	/**
	 * 查询支付结果通知日志列表
	 */
	@RequiresPermissions("payorder:payResultNotifyLog:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PayResultNotifyLog payResultNotifyLog)
	{
		startPage();
        List<PayResultNotifyLog> list = payResultNotifyLogService.selectPayResultNotifyLogList(payResultNotifyLog);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出支付结果通知日志列表
	 */
	@RequiresPermissions("payorder:payResultNotifyLog:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PayResultNotifyLog payResultNotifyLog)
    {
    	List<PayResultNotifyLog> list = payResultNotifyLogService.selectPayResultNotifyLogList(payResultNotifyLog);
        ExcelUtil<PayResultNotifyLog> util = new ExcelUtil<PayResultNotifyLog>(PayResultNotifyLog.class);
        return util.exportExcel(list, "payResultNotifyLog");
    }
	
	/**
	 * 新增支付结果通知日志
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存支付结果通知日志
	 */
	@RequiresPermissions("payorder:payResultNotifyLog:add")
	@Log(title = "支付结果通知日志", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PayResultNotifyLog payResultNotifyLog)
	{		
		return toAjax(payResultNotifyLogService.insertPayResultNotifyLog(payResultNotifyLog));
	}

	/**
	 * 修改支付结果通知日志
	 */
	@GetMapping("/edit/{notifyRequestId}")
	public String edit(@PathVariable("notifyRequestId") Integer notifyRequestId, ModelMap mmap)
	{
		PayResultNotifyLog payResultNotifyLog = payResultNotifyLogService.selectPayResultNotifyLogById(notifyRequestId);
		mmap.put("payResultNotifyLog", payResultNotifyLog);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存支付结果通知日志
	 */
	@RequiresPermissions("payorder:payResultNotifyLog:edit")
	@Log(title = "支付结果通知日志", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PayResultNotifyLog payResultNotifyLog)
	{		
		return toAjax(payResultNotifyLogService.updatePayResultNotifyLog(payResultNotifyLog));
	}
	
	/**
	 * 删除支付结果通知日志
	 */
	@RequiresPermissions("payorder:payResultNotifyLog:remove")
	@Log(title = "支付结果通知日志", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(payResultNotifyLogService.deletePayResultNotifyLogByIds(ids));
	}
	
}
