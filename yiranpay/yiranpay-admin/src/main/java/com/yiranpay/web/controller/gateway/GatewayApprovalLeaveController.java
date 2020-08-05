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
import com.yiranpay.gateway.domain.GatewayApprovalLeave;
import com.yiranpay.gateway.service.IGatewayApprovalLeaveService;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.common.core.page.TableDataInfo;

/**
 * 产品审批明细Controller
 * 
 * @author panda
 * @date 2020-04-12
 */
@Controller
@RequestMapping("/gateway/leave")
public class GatewayApprovalLeaveController extends BaseController
{
    private String prefix = "gateway/leave";

    @Autowired
    private IGatewayApprovalLeaveService gatewayApprovalLeaveService;

    @RequiresPermissions("gateway:leave:view")
    @GetMapping()
    public String leave()
    {
        return prefix + "/leave";
    }

    /**
     * 查询产品审批明细列表
     */
    @RequiresPermissions("gateway:leave:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GatewayApprovalLeave gatewayApprovalLeave)
    {
        startPage();
        List<GatewayApprovalLeave> list = gatewayApprovalLeaveService.selectGatewayApprovalLeaveList(gatewayApprovalLeave);
        return getDataTable(list);
    }

    /**
     * 导出产品审批明细列表
     */
    @RequiresPermissions("gateway:leave:export")
    @Log(title = "产品审批明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GatewayApprovalLeave gatewayApprovalLeave)
    {
        List<GatewayApprovalLeave> list = gatewayApprovalLeaveService.selectGatewayApprovalLeaveList(gatewayApprovalLeave);
        ExcelUtil<GatewayApprovalLeave> util = new ExcelUtil<GatewayApprovalLeave>(GatewayApprovalLeave.class);
        return util.exportExcel(list, "leave");
    }

    /**
     * 新增产品审批明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产品审批明细
     */
    @RequiresPermissions("gateway:leave:add")
    @Log(title = "产品审批明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GatewayApprovalLeave gatewayApprovalLeave)
    {
        return toAjax(gatewayApprovalLeaveService.insertGatewayApprovalLeave(gatewayApprovalLeave));
    }

    /**
     * 修改产品审批明细
     */
    @GetMapping("/edit/{auditid}")
    public String edit(@PathVariable("auditid") Long auditid, ModelMap mmap)
    {
        GatewayApprovalLeave gatewayApprovalLeave = gatewayApprovalLeaveService.selectGatewayApprovalLeaveById(auditid);
        mmap.put("gatewayApprovalLeave", gatewayApprovalLeave);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品审批明细
     */
    @RequiresPermissions("gateway:leave:edit")
    @Log(title = "产品审批明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GatewayApprovalLeave gatewayApprovalLeave)
    {
        return toAjax(gatewayApprovalLeaveService.updateGatewayApprovalLeave(gatewayApprovalLeave));
    }

    /**
     * 删除产品审批明细
     */
    @RequiresPermissions("gateway:leave:remove")
    @Log(title = "产品审批明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(gatewayApprovalLeaveService.deleteGatewayApprovalLeaveByIds(ids));
    }
}
