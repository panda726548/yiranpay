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
import com.yiranpay.reconciliation.domain.ReconciliationAccountflow;
import com.yiranpay.reconciliation.service.IReconciliationAccountflowService;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.common.core.page.TableDataInfo;

/**
 * 入账流水Controller
 * 
 * @author panda
 * @date 2020-04-25
 */
@Controller
@RequestMapping("/reconciliation/accountflow")
public class ReconciliationAccountflowController extends BaseController
{
    private String prefix = "reconciliation/accountflow";

    @Autowired
    private IReconciliationAccountflowService reconciliationAccountflowService;

    @RequiresPermissions("reconciliation:accountflow:view")
    @GetMapping()
    public String accountflow()
    {
        return prefix + "/accountflow";
    }

    /**
     * 查询入账流水列表
     */
    @RequiresPermissions("reconciliation:accountflow:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ReconciliationAccountflow reconciliationAccountflow)
    {
        startPage();
        List<ReconciliationAccountflow> list = reconciliationAccountflowService.selectReconciliationAccountflowList(reconciliationAccountflow);
        return getDataTable(list);
    }

    /**
     * 导出入账流水列表
     */
    @RequiresPermissions("reconciliation:accountflow:export")
    @Log(title = "入账流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ReconciliationAccountflow reconciliationAccountflow)
    {
        List<ReconciliationAccountflow> list = reconciliationAccountflowService.selectReconciliationAccountflowList(reconciliationAccountflow);
        ExcelUtil<ReconciliationAccountflow> util = new ExcelUtil<ReconciliationAccountflow>(ReconciliationAccountflow.class);
        return util.exportExcel(list, "accountflow");
    }

    /**
     * 新增入账流水
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存入账流水
     */
    @RequiresPermissions("reconciliation:accountflow:add")
    @Log(title = "入账流水", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ReconciliationAccountflow reconciliationAccountflow)
    {
        return toAjax(reconciliationAccountflowService.insertReconciliationAccountflow(reconciliationAccountflow));
    }

    /**
     * 修改入账流水
     */
    @GetMapping("/edit/{glideId}")
    public String edit(@PathVariable("glideId") Long glideId, ModelMap mmap)
    {
        ReconciliationAccountflow reconciliationAccountflow = reconciliationAccountflowService.selectReconciliationAccountflowById(glideId);
        mmap.put("reconciliationAccountflow", reconciliationAccountflow);
        return prefix + "/edit";
    }

    /**
     * 修改保存入账流水
     */
    @RequiresPermissions("reconciliation:accountflow:edit")
    @Log(title = "入账流水", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ReconciliationAccountflow reconciliationAccountflow)
    {
        return toAjax(reconciliationAccountflowService.updateReconciliationAccountflow(reconciliationAccountflow));
    }

    /**
     * 删除入账流水
     */
    @RequiresPermissions("reconciliation:accountflow:remove")
    @Log(title = "入账流水", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(reconciliationAccountflowService.deleteReconciliationAccountflowByIds(ids));
    }
}
