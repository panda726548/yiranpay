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
import com.yiranpay.gateway.domain.GatewaySecretKey;
import com.yiranpay.gateway.service.IGatewaySecretKeyService;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.common.core.page.TableDataInfo;

/**
 * 商户秘钥管理Controller
 * 
 * @author panda
 * @date 2020-01-15
 */
@Controller
@RequestMapping("/gateway/key")
public class GatewaySecretKeyController extends BaseController
{
    private String prefix = "gateway/key";

    @Autowired
    private IGatewaySecretKeyService gatewaySecretKeyService;

    @RequiresPermissions("gateway:key:view")
    @GetMapping()
    public String key()
    {
        return prefix + "/key";
    }

    /**
     * 查询商户秘钥管理列表
     */
    @RequiresPermissions("gateway:key:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GatewaySecretKey gatewaySecretKey)
    {
        startPage();
        List<GatewaySecretKey> list = gatewaySecretKeyService.selectGatewaySecretKeyList(gatewaySecretKey);
        return getDataTable(list);
    }

    /**
     * 导出商户秘钥管理列表
     */
    @RequiresPermissions("gateway:key:export")
    @Log(title = "商户秘钥管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GatewaySecretKey gatewaySecretKey)
    {
        List<GatewaySecretKey> list = gatewaySecretKeyService.selectGatewaySecretKeyList(gatewaySecretKey);
        ExcelUtil<GatewaySecretKey> util = new ExcelUtil<GatewaySecretKey>(GatewaySecretKey.class);
        return util.exportExcel(list, "key");
    }

    /**
     * 新增商户秘钥管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商户秘钥管理
     */
    @RequiresPermissions("gateway:key:add")
    @Log(title = "商户秘钥管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GatewaySecretKey gatewaySecretKey)
    {
        return toAjax(gatewaySecretKeyService.insertGatewaySecretKey(gatewaySecretKey));
    }

    /**
     * 修改商户秘钥管理
     */
    @GetMapping("/edit/{secretkeyId}")
    public String edit(@PathVariable("secretkeyId") Long secretkeyId, ModelMap mmap)
    {
        GatewaySecretKey gatewaySecretKey = gatewaySecretKeyService.selectGatewaySecretKeyById(secretkeyId);
        mmap.put("gatewaySecretKey", gatewaySecretKey);
        return prefix + "/edit";
    }

    /**
     * 修改保存商户秘钥管理
     */
    @RequiresPermissions("gateway:key:edit")
    @Log(title = "商户秘钥管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GatewaySecretKey gatewaySecretKey)
    {
        return toAjax(gatewaySecretKeyService.updateGatewaySecretKey(gatewaySecretKey));
    }

    /**
     * 删除商户秘钥管理
     */
    @RequiresPermissions("gateway:key:remove")
    @Log(title = "商户秘钥管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(gatewaySecretKeyService.deleteGatewaySecretKeyByIds(ids));
    }
}
