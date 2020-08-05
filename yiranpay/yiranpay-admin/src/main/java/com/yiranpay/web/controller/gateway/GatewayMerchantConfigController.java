package com.yiranpay.web.controller.gateway;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yiranpay.common.annotation.Log;
import com.yiranpay.common.enums.BusinessType;
import com.yiranpay.gateway.domain.GatewayMerchantConfig;
import com.yiranpay.gateway.service.IGatewayMerchantConfigService;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.framework.util.ShiroUtils;
import com.yiranpay.common.core.page.TableDataInfo;

/**
 * 商户接口配置Controller
 * 
 * @author panda
 * @date 2020-01-06
 */
@Controller
@RequestMapping("/gateway/config")
public class GatewayMerchantConfigController extends BaseController
{
    private String prefix = "gateway/config";

    @Autowired
    private IGatewayMerchantConfigService gatewayMerchantConfigService;

    @RequiresPermissions("gateway:config:view")
    @GetMapping()
    public String config()
    {
        return prefix + "/config";
    }

    /**
     * 查询商户接口配置列表
     */
    @RequiresPermissions("gateway:config:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GatewayMerchantConfig gatewayMerchantConfig)
    {
        startPage();
        List<GatewayMerchantConfig> list = gatewayMerchantConfigService.selectGatewayMerchantConfigList(gatewayMerchantConfig);
        return getDataTable(list);
    }

    /**
     * 导出商户接口配置列表
     */
    @RequiresPermissions("gateway:config:export")
    @Log(title = "商户接口配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GatewayMerchantConfig gatewayMerchantConfig)
    {
        List<GatewayMerchantConfig> list = gatewayMerchantConfigService.selectGatewayMerchantConfigList(gatewayMerchantConfig);
        ExcelUtil<GatewayMerchantConfig> util = new ExcelUtil<GatewayMerchantConfig>(GatewayMerchantConfig.class);
        return util.exportExcel(list, "config");
    }

    /**
     * 新增商户接口配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商户接口配置
     */
    @RequiresPermissions("gateway:config:add")
    @Log(title = "商户接口配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated GatewayMerchantConfig gatewayMerchantConfig)
    {
    	logger.info(" 新增保存商户接口配置:"+JSON.toJSONString(gatewayMerchantConfig));
    	ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(gatewayMerchantConfigService.insertGatewayMerchantConfig(gatewayMerchantConfig));
    }

    /**
     * 修改商户接口配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        GatewayMerchantConfig gatewayMerchantConfig = gatewayMerchantConfigService.selectGatewayMerchantConfigById(id);
        mmap.put("gatewayMerchantConfig", gatewayMerchantConfig);
        return prefix + "/edit";
    }

    /**
     * 修改保存商户接口配置
     */
    @RequiresPermissions("gateway:config:edit")
    @Log(title = "商户接口配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated GatewayMerchantConfig gatewayMerchantConfig)
    {
        return toAjax(gatewayMerchantConfigService.updateGatewayMerchantConfig(gatewayMerchantConfig));
    }

    /**
     * 删除商户接口配置
     */
    @RequiresPermissions("gateway:config:remove")
    @Log(title = "商户接口配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(gatewayMerchantConfigService.deleteGatewayMerchantConfigByIds(ids));
    }
}
