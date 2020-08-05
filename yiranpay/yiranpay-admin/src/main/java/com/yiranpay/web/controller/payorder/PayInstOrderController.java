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
import com.yiranpay.payorder.domain.PayInstOrder;
import com.yiranpay.payorder.domaindo.PayInstOrderDO;
import com.yiranpay.payorder.service.IPayInstOrderService;
/**
 * 提交机构订单 信息操作处理
 * 
 * @author yiran
 * @date 2019-07-13
 */
@Controller
@RequestMapping("/payorder/payInstOrder")
public class PayInstOrderController extends BaseController
{
    private String prefix = "payorder/payInstOrder";
	
	@Autowired
	private IPayInstOrderService payInstOrderService;
	
	@RequiresPermissions("payorder:payInstOrder:view")
	@GetMapping()
	public String payInstOrder()
	{
	    return prefix + "/payInstOrder";
	}
	
	/**
	 * 查询提交机构订单列表
	 */
	@RequiresPermissions("payorder:payInstOrder:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PayInstOrderDO payInstOrder)
	{
		startPage();
        List<PayInstOrderDO> list = payInstOrderService.selectPayInstOrderList(payInstOrder);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出提交机构订单列表
	 */
	@RequiresPermissions("payorder:payInstOrder:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PayInstOrderDO payInstOrder)
    {
    	List<PayInstOrderDO> list = payInstOrderService.selectPayInstOrderList(payInstOrder);
        ExcelUtil<PayInstOrderDO> util = new ExcelUtil<PayInstOrderDO>(PayInstOrderDO.class);
        return util.exportExcel(list, "payInstOrder");
    }
	
	/**
	 * 新增提交机构订单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存提交机构订单
	 */
	@RequiresPermissions("payorder:payInstOrder:add")
	@Log(title = "提交机构订单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PayInstOrderDO payInstOrder)
	{		
		return toAjax(payInstOrderService.insertPayInstOrder(payInstOrder));
	}

	/**
	 * 修改提交机构订单
	 */
	@GetMapping("/edit/{instOrderId}")
	public String edit(@PathVariable("instOrderId") Integer instOrderId, ModelMap mmap)
	{
		PayInstOrderDO payInstOrder = payInstOrderService.selectPayInstOrderById(instOrderId);
		mmap.put("payInstOrder", payInstOrder);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存提交机构订单
	 */
	@RequiresPermissions("payorder:payInstOrder:edit")
	@Log(title = "提交机构订单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PayInstOrderDO payInstOrder)
	{		
		return toAjax(payInstOrderService.updatePayInstOrder(payInstOrder));
	}
	
	/**
	 * 删除提交机构订单
	 */
	@RequiresPermissions("payorder:payInstOrder:remove")
	@Log(title = "提交机构订单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(payInstOrderService.deletePayInstOrderByIds(ids));
	}
	
}
