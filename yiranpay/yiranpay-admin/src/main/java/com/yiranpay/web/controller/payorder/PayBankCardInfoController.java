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
import com.yiranpay.payorder.domaindo.PayBankCardInfoDO;
import com.yiranpay.payorder.service.IPayBankCardInfoService;
/**
 * 银行卡 信息操作处理
 * 
 * @author yiran
 * @date 2019-07-13
 */
@Controller
@RequestMapping("/payorder/payBankCardInfo")
public class PayBankCardInfoController extends BaseController
{
    private String prefix = "payorder/payBankCardInfo";
	
	@Autowired
	private IPayBankCardInfoService payBankCardInfoService;
	
	@RequiresPermissions("payorder:payBankCardInfo:view")
	@GetMapping()
	public String payBankCardInfo()
	{
	    return prefix + "/payBankCardInfo";
	}
	
	/**
	 * 查询银行卡列表
	 */
	@RequiresPermissions("payorder:payBankCardInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PayBankCardInfoDO payBankCardInfo)
	{
		startPage();
        List<PayBankCardInfoDO> list = payBankCardInfoService.selectPayBankCardInfoList(payBankCardInfo);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出银行卡列表
	 */
	@RequiresPermissions("payorder:payBankCardInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PayBankCardInfoDO payBankCardInfo)
    {
    	List<PayBankCardInfoDO> list = payBankCardInfoService.selectPayBankCardInfoList(payBankCardInfo);
        ExcelUtil<PayBankCardInfoDO> util = new ExcelUtil<PayBankCardInfoDO>(PayBankCardInfoDO.class);
        return util.exportExcel(list, "payBankCardInfo");
    }
	
	/**
	 * 新增银行卡
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存银行卡
	 */
	@RequiresPermissions("payorder:payBankCardInfo:add")
	@Log(title = "银行卡", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PayBankCardInfoDO payBankCardInfo)
	{		
		return toAjax(payBankCardInfoService.insertPayBankCardInfo(payBankCardInfo));
	}

	/**
	 * 修改银行卡
	 */
	@GetMapping("/edit/{bankCardId}")
	public String edit(@PathVariable("bankCardId") Integer bankCardId, ModelMap mmap)
	{
		PayBankCardInfoDO payBankCardInfo = payBankCardInfoService.selectPayBankCardInfoById(bankCardId);
		mmap.put("payBankCardInfo", payBankCardInfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存银行卡
	 */
	@RequiresPermissions("payorder:payBankCardInfo:edit")
	@Log(title = "银行卡", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PayBankCardInfoDO payBankCardInfo)
	{		
		return toAjax(payBankCardInfoService.updatePayBankCardInfo(payBankCardInfo));
	}
	
	/**
	 * 删除银行卡
	 */
	@RequiresPermissions("payorder:payBankCardInfo:remove")
	@Log(title = "银行卡", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(payBankCardInfoService.deletePayBankCardInfoByIds(ids));
	}
	
}
