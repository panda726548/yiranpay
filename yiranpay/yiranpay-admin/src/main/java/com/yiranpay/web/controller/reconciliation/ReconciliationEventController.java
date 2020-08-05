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
import com.yiranpay.reconciliation.domain.ReconciliationEvent;
import com.yiranpay.reconciliation.service.IReconciliationEventService;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.common.core.page.TableDataInfo;

/**
 * 对账下载URLController
 * 
 * @author panda
 * @date 2020-04-30
 */
@Controller
@RequestMapping("/reconciliation/event")
public class ReconciliationEventController extends BaseController
{
    private String prefix = "reconciliation/event";

    @Autowired
    private IReconciliationEventService reconciliationEventService;

    @RequiresPermissions("reconciliation:event:view")
    @GetMapping()
    public String event()
    {
        return prefix + "/event";
    }

    /**
     * 查询对账下载URL列表
     */
    @RequiresPermissions("reconciliation:event:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ReconciliationEvent reconciliationEvent)
    {
        startPage();
        List<ReconciliationEvent> list = reconciliationEventService.selectReconciliationEventList(reconciliationEvent);
        return getDataTable(list);
    }

    /**
     * 导出对账下载URL列表
     */
    @RequiresPermissions("reconciliation:event:export")
    @Log(title = "对账下载URL", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ReconciliationEvent reconciliationEvent)
    {
        List<ReconciliationEvent> list = reconciliationEventService.selectReconciliationEventList(reconciliationEvent);
        ExcelUtil<ReconciliationEvent> util = new ExcelUtil<ReconciliationEvent>(ReconciliationEvent.class);
        return util.exportExcel(list, "event");
    }

    /**
     * 新增对账下载URL
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存对账下载URL
     */
    @RequiresPermissions("reconciliation:event:add")
    @Log(title = "对账下载URL", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ReconciliationEvent reconciliationEvent)
    {
        return toAjax(reconciliationEventService.insertReconciliationEvent(reconciliationEvent));
    }

    /**
     * 修改对账下载URL
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ReconciliationEvent reconciliationEvent = reconciliationEventService.selectReconciliationEventById(id);
        mmap.put("reconciliationEvent", reconciliationEvent);
        return prefix + "/edit";
    }

    /**
     * 修改保存对账下载URL
     */
    @RequiresPermissions("reconciliation:event:edit")
    @Log(title = "对账下载URL", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ReconciliationEvent reconciliationEvent)
    {
        return toAjax(reconciliationEventService.updateReconciliationEvent(reconciliationEvent));
    }

    /**
     * 删除对账下载URL
     */
    @RequiresPermissions("reconciliation:event:remove")
    @Log(title = "对账下载URL", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(reconciliationEventService.deleteReconciliationEventByIds(ids));
    }
}
