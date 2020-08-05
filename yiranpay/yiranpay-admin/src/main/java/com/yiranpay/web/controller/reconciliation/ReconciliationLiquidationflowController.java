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
import com.yiranpay.common.enums.BusinessType;
import com.yiranpay.reconciliation.domain.ReconciliationLiquidationflow;
import com.yiranpay.reconciliation.service.IReconciliationLiquidationflowService;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.common.core.page.TableDataInfo;

/**
 * 清算流水Controller
 * 
 * @author panda
 * @date 2020-04-25
 */
@Controller
@RequestMapping("/reconciliation/liquidationflow")
public class ReconciliationLiquidationflowController extends BaseController
{
    private String prefix = "reconciliation/liquidationflow";

    @Autowired
    private IReconciliationLiquidationflowService reconciliationLiquidationflowService;

    @RequiresPermissions("reconciliation:liquidationflow:view")
    @GetMapping()
    public String liquidationflow()
    {
        return prefix + "/liquidationflow";
    }

    /**
     * 查询清算流水列表
     */
    @RequiresPermissions("reconciliation:liquidationflow:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ReconciliationLiquidationflow reconciliationLiquidationflow)
    {
        startPage();
        List<ReconciliationLiquidationflow> list = reconciliationLiquidationflowService.selectReconciliationLiquidationflowList(reconciliationLiquidationflow);
        return getDataTable(list);
    }

    /**
     * 导出清算流水列表
     */
    @RequiresPermissions("reconciliation:liquidationflow:export")
    @Log(title = "清算流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ReconciliationLiquidationflow reconciliationLiquidationflow)
    {
        List<ReconciliationLiquidationflow> list = reconciliationLiquidationflowService.selectReconciliationLiquidationflowList(reconciliationLiquidationflow);
        ExcelUtil<ReconciliationLiquidationflow> util = new ExcelUtil<ReconciliationLiquidationflow>(ReconciliationLiquidationflow.class);
        return util.exportExcel(list, "liquidationflow");
    }

    /**
     * 新增清算流水
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存清算流水
     */
    @RequiresPermissions("reconciliation:liquidationflow:add")
    @Log(title = "清算流水", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ReconciliationLiquidationflow reconciliationLiquidationflow)
    {
        return toAjax(reconciliationLiquidationflowService.insertReconciliationLiquidationflow(reconciliationLiquidationflow));
    }

    /**
     * 修改清算流水
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ReconciliationLiquidationflow reconciliationLiquidationflow = reconciliationLiquidationflowService.selectReconciliationLiquidationflowById(id);
        mmap.put("reconciliationLiquidationflow", reconciliationLiquidationflow);
        return prefix + "/edit";
    }

    /**
     * 修改保存清算流水
     */
    @RequiresPermissions("reconciliation:liquidationflow:edit")
    @Log(title = "清算流水", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ReconciliationLiquidationflow reconciliationLiquidationflow)
    {
        return toAjax(reconciliationLiquidationflowService.updateReconciliationLiquidationflow(reconciliationLiquidationflow));
    }

    /**
     * 删除清算流水
     */
    @RequiresPermissions("reconciliation:liquidationflow:remove")
    @Log(title = "清算流水", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(reconciliationLiquidationflowService.deleteReconciliationLiquidationflowByIds(ids));
    }
}
