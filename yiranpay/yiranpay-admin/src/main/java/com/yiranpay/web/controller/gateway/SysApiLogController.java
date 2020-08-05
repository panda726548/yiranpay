package com.yiranpay.web.controller.gateway;

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
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.gateway.domain.SysApiLog;
import com.yiranpay.gateway.service.ISysApiLogService;
import com.yiranpay.common.core.page.TableDataInfo;

/**
 * 网关API接口日志Controller
 * 
 * @author panda
 * @date 2020-04-23
 */
@Controller
@RequestMapping("/system/log")
public class SysApiLogController extends BaseController
{
    private String prefix = "gateway/log";

    @Autowired
    private ISysApiLogService sysApiLogService;

    @RequiresPermissions("system:log:view")
    @GetMapping()
    public String log()
    {
        return prefix + "/log";
    }

    /**
     * 查询网关API接口日志列表
     */
    @RequiresPermissions("system:log:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysApiLog sysApiLog)
    {
        startPage();
        List<SysApiLog> list = sysApiLogService.selectSysApiLogList(sysApiLog);
        return getDataTable(list);
    }

    /**
     * 导出网关API接口日志列表
     */
    @RequiresPermissions("system:log:export")
    @Log(title = "网关API接口日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysApiLog sysApiLog)
    {
        List<SysApiLog> list = sysApiLogService.selectSysApiLogList(sysApiLog);
        ExcelUtil<SysApiLog> util = new ExcelUtil<SysApiLog>(SysApiLog.class);
        return util.exportExcel(list, "log");
    }

    /**
     * 新增网关API接口日志
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存网关API接口日志
     */
    @RequiresPermissions("system:log:add")
    @Log(title = "网关API接口日志", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysApiLog sysApiLog)
    {
        return toAjax(sysApiLogService.insertSysApiLog(sysApiLog));
    }

    /**
     * 修改网关API接口日志
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SysApiLog sysApiLog = sysApiLogService.selectSysApiLogById(id);
        mmap.put("sysApiLog", sysApiLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存网关API接口日志
     */
    @RequiresPermissions("system:log:edit")
    @Log(title = "网关API接口日志", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysApiLog sysApiLog)
    {
        return toAjax(sysApiLogService.updateSysApiLog(sysApiLog));
    }

    /**
     * 删除网关API接口日志
     */
    @RequiresPermissions("system:log:remove")
    @Log(title = "网关API接口日志", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysApiLogService.deleteSysApiLogByIds(ids));
    }
}
