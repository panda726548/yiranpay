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
import com.yiranpay.payorder.domain.PayRefundOrder;
import com.yiranpay.payorder.service.IPayRefundOrderService;
/**
 * 退款订单 信息操作处理
 * 
 * @author yiran
 * @date 2019-07-13
 */
@Controller
@RequestMapping("/payorder/payRefundOrder")
public class PayRefundOrderController extends BaseController
{
    private String prefix = "payorder/payRefundOrder";
	
	@Autowired
	private IPayRefundOrderService payRefundOrderService;
	
	@RequiresPermissions("payorder:payRefundOrder:view")
	@GetMapping()
	public String payRefundOrder()
	{
	    return prefix + "/payRefundOrder";
	}
	
	/**
	 * 查询退款订单列表
	 */
	@RequiresPermissions("payorder:payRefundOrder:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PayRefundOrder payRefundOrder)
	{
		startPage();
        List<PayRefundOrder> list = payRefundOrderService.selectPayRefundOrderList(payRefundOrder);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出退款订单列表
	 */
	@RequiresPermissions("payorder:payRefundOrder:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PayRefundOrder payRefundOrder)
    {
    	List<PayRefundOrder> list = payRefundOrderService.selectPayRefundOrderList(payRefundOrder);
        ExcelUtil<PayRefundOrder> util = new ExcelUtil<PayRefundOrder>(PayRefundOrder.class);
        return util.exportExcel(list, "payRefundOrder");
    }
	
	/**
	 * 新增退款订单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存退款订单
	 */
	@RequiresPermissions("payorder:payRefundOrder:add")
	@Log(title = "退款订单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PayRefundOrder payRefundOrder)
	{		
		return toAjax(payRefundOrderService.insertPayRefundOrder(payRefundOrder));
	}

	/**
	 * 修改退款订单
	 */
	@GetMapping("/edit/{instOrderId}")
	public String edit(@PathVariable("instOrderId") Integer instOrderId, ModelMap mmap)
	{
		PayRefundOrder payRefundOrder = payRefundOrderService.selectPayRefundOrderById(instOrderId);
		mmap.put("payRefundOrder", payRefundOrder);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存退款订单
	 */
	@RequiresPermissions("payorder:payRefundOrder:edit")
	@Log(title = "退款订单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PayRefundOrder payRefundOrder)
	{		
		return toAjax(payRefundOrderService.updatePayRefundOrder(payRefundOrder));
	}
	
	/**
	 * 删除退款订单
	 */
	@RequiresPermissions("payorder:payRefundOrder:remove")
	@Log(title = "退款订单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(payRefundOrderService.deletePayRefundOrderByIds(ids));
	}
	
}
