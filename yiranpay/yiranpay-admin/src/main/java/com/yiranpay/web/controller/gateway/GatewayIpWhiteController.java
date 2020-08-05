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
import com.yiranpay.gateway.domain.GatewayIpWhite;
import com.yiranpay.gateway.service.IGatewayIpWhiteService;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.common.core.page.TableDataInfo;

/**
 * IP白名单Controller
 * 
 * @author panda
 * @date 2020-01-09
 */
@Controller
@RequestMapping("/gateway/white")
public class GatewayIpWhiteController extends BaseController
{
    private String prefix = "gateway/white";

    @Autowired
    private IGatewayIpWhiteService gatewayIpWhiteService;

    @RequiresPermissions("gateway:white:view")
    @GetMapping()
    public String white()
    {
        return prefix + "/white";
    }

    /**
     * 查询IP白名单列表
     */
    @RequiresPermissions("gateway:white:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GatewayIpWhite gatewayIpWhite)
    {
        startPage();
        List<GatewayIpWhite> list = gatewayIpWhiteService.selectGatewayIpWhiteList(gatewayIpWhite);
        return getDataTable(list);
    }

    /**
     * 导出IP白名单列表
     */
    @RequiresPermissions("gateway:white:export")
    @Log(title = "IP白名单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GatewayIpWhite gatewayIpWhite)
    {
        List<GatewayIpWhite> list = gatewayIpWhiteService.selectGatewayIpWhiteList(gatewayIpWhite);
        ExcelUtil<GatewayIpWhite> util = new ExcelUtil<GatewayIpWhite>(GatewayIpWhite.class);
        return util.exportExcel(list, "white");
    }

    /**
     * 新增IP白名单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存IP白名单
     */
    @RequiresPermissions("gateway:white:add")
    @Log(title = "IP白名单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GatewayIpWhite gatewayIpWhite)
    {
        return toAjax(gatewayIpWhiteService.insertGatewayIpWhite(gatewayIpWhite));
    }

    /**
     * 修改IP白名单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        GatewayIpWhite gatewayIpWhite = gatewayIpWhiteService.selectGatewayIpWhiteById(id);
        mmap.put("gatewayIpWhite", gatewayIpWhite);
        return prefix + "/edit";
    }

    /**
     * 修改保存IP白名单
     */
    @RequiresPermissions("gateway:white:edit")
    @Log(title = "IP白名单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GatewayIpWhite gatewayIpWhite)
    {
        return toAjax(gatewayIpWhiteService.updateGatewayIpWhite(gatewayIpWhite));
    }

    /**
     * 删除IP白名单
     */
    @RequiresPermissions("gateway:white:remove")
    @Log(title = "IP白名单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(gatewayIpWhiteService.deleteGatewayIpWhiteByIds(ids));
    }
}
