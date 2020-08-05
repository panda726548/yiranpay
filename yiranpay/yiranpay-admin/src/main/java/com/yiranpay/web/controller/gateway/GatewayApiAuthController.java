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
import com.yiranpay.gateway.domain.GatewayApiAuth;
import com.yiranpay.gateway.service.IGatewayApiAuthService;
import com.yiranpay.common.core.page.TableDataInfo;

/**
 * 商户和权限关联Controller
 * 
 * @author panda
 * @date 2020-01-06
 */
@Controller
@RequestMapping("/gateway/auth")
public class GatewayApiAuthController extends BaseController
{
    private String prefix = "gateway/auth";

    @Autowired
    private IGatewayApiAuthService gatewayApiAuthService;

    @RequiresPermissions("gateway:auth:view")
    @GetMapping()
    public String auth()
    {
        return prefix + "/auth";
    }

    /**
     * 查询商户和权限关联列表
     */
    @RequiresPermissions("gateway:auth:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GatewayApiAuth gatewayApiAuth)
    {
        startPage();
        List<GatewayApiAuth> list = gatewayApiAuthService.selectGatewayApiAuthList(gatewayApiAuth);
        return getDataTable(list);
    }

    /**
     * 导出商户和权限关联列表
     */
    @RequiresPermissions("gateway:auth:export")
    @Log(title = "商户和权限关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GatewayApiAuth gatewayApiAuth)
    {
        List<GatewayApiAuth> list = gatewayApiAuthService.selectGatewayApiAuthList(gatewayApiAuth);
        ExcelUtil<GatewayApiAuth> util = new ExcelUtil<GatewayApiAuth>(GatewayApiAuth.class);
        return util.exportExcel(list, "auth");
    }

    /**
     * 新增商户和权限关联
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商户和权限关联
     */
    @RequiresPermissions("gateway:auth:add")
    @Log(title = "商户和权限关联", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GatewayApiAuth gatewayApiAuth)
    {
        return toAjax(gatewayApiAuthService.insertGatewayApiAuth(gatewayApiAuth));
    }

    /**
     * 修改商户和权限关联
     */
    @GetMapping("/edit/{apiId}")
    public String edit(@PathVariable("apiId") Long apiId, ModelMap mmap)
    {
        GatewayApiAuth gatewayApiAuth = gatewayApiAuthService.selectGatewayApiAuthById(apiId);
        mmap.put("gatewayApiAuth", gatewayApiAuth);
        return prefix + "/edit";
    }

    /**
     * 修改保存商户和权限关联
     */
    @RequiresPermissions("gateway:auth:edit")
    @Log(title = "商户和权限关联", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GatewayApiAuth gatewayApiAuth)
    {
        return toAjax(gatewayApiAuthService.updateGatewayApiAuth(gatewayApiAuth));
    }

    /**
     * 删除商户和权限关联
     */
    @RequiresPermissions("gateway:auth:remove")
    @Log(title = "商户和权限关联", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(gatewayApiAuthService.deleteGatewayApiAuthByIds(ids));
    }
}
