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
import com.yiranpay.payorder.domain.PayDistributedLock;
import com.yiranpay.payorder.service.IPayDistributedLockService;
/**
 * 分布式锁 信息操作处理
 * 
 * @author yiran
 * @date 2019-08-11
 */
@Controller
@RequestMapping("/payorder/payDistributedLock")
public class PayDistributedLockController extends BaseController
{
    private String prefix = "payorder/payDistributedLock";
	
	@Autowired
	private IPayDistributedLockService payDistributedLockService;
	
	@RequiresPermissions("payorder:payDistributedLock:view")
	@GetMapping()
	public String payDistributedLock()
	{
	    return prefix + "/payDistributedLock";
	}
	
	/**
	 * 查询分布式锁列表
	 */
	@RequiresPermissions("payorder:payDistributedLock:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PayDistributedLock payDistributedLock)
	{
		startPage();
        List<PayDistributedLock> list = payDistributedLockService.selectPayDistributedLockList(payDistributedLock);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出分布式锁列表
	 */
	@RequiresPermissions("payorder:payDistributedLock:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PayDistributedLock payDistributedLock)
    {
    	List<PayDistributedLock> list = payDistributedLockService.selectPayDistributedLockList(payDistributedLock);
        ExcelUtil<PayDistributedLock> util = new ExcelUtil<PayDistributedLock>(PayDistributedLock.class);
        return util.exportExcel(list, "payDistributedLock");
    }
	
	/**
	 * 新增分布式锁
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存分布式锁
	 */
	@RequiresPermissions("payorder:payDistributedLock:add")
	@Log(title = "分布式锁", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PayDistributedLock payDistributedLock)
	{		
		return toAjax(payDistributedLockService.insertPayDistributedLock(payDistributedLock));
	}

	/**
	 * 修改分布式锁
	 */
	@GetMapping("/edit/{lockTicket}")
	public String edit(@PathVariable("lockTicket") String lockTicket, ModelMap mmap)
	{
		PayDistributedLock payDistributedLock = payDistributedLockService.selectPayDistributedLockById(lockTicket);
		mmap.put("payDistributedLock", payDistributedLock);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存分布式锁
	 */
	@RequiresPermissions("payorder:payDistributedLock:edit")
	@Log(title = "分布式锁", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PayDistributedLock payDistributedLock)
	{		
		return toAjax(payDistributedLockService.updatePayDistributedLock(payDistributedLock));
	}
	
	/**
	 * 删除分布式锁
	 */
	@RequiresPermissions("payorder:payDistributedLock:remove")
	@Log(title = "分布式锁", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(payDistributedLockService.deletePayDistributedLockByIds(ids));
	}
	
}
