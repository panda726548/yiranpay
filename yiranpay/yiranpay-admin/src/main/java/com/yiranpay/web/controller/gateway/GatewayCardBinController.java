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
import com.yiranpay.gateway.domain.GatewayCardBin;
import com.yiranpay.gateway.service.IGatewayCardBinService;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.common.core.page.TableDataInfo;

/**
 * 卡BinController
 * 
 * @author panda
 * @date 2020-01-06
 */
@Controller
@RequestMapping("/gateway/bin")
public class GatewayCardBinController extends BaseController
{
    private String prefix = "gateway/bin";

    @Autowired
    private IGatewayCardBinService gatewayCardBinService;

    @RequiresPermissions("gateway:bin:view")
    @GetMapping()
    public String bin()
    {
        return prefix + "/bin";
    }

    /**
     * 查询卡Bin列表
     */
    @RequiresPermissions("gateway:bin:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GatewayCardBin gatewayCardBin)
    {
        startPage();
        List<GatewayCardBin> list = gatewayCardBinService.selectGatewayCardBinList(gatewayCardBin);
        return getDataTable(list);
    }

    /**
     * 导出卡Bin列表
     */
    @RequiresPermissions("gateway:bin:export")
    @Log(title = "卡Bin", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GatewayCardBin gatewayCardBin)
    {
        List<GatewayCardBin> list = gatewayCardBinService.selectGatewayCardBinList(gatewayCardBin);
        ExcelUtil<GatewayCardBin> util = new ExcelUtil<GatewayCardBin>(GatewayCardBin.class);
        return util.exportExcel(list, "bin");
    }

    /**
     * 新增卡Bin
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存卡Bin
     */
    @RequiresPermissions("gateway:bin:add")
    @Log(title = "卡Bin", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GatewayCardBin gatewayCardBin)
    {
        return toAjax(gatewayCardBinService.insertGatewayCardBin(gatewayCardBin));
    }

    /**
     * 修改卡Bin
     */
    @GetMapping("/edit/{binId}")
    public String edit(@PathVariable("binId") Long binId, ModelMap mmap)
    {
        GatewayCardBin gatewayCardBin = gatewayCardBinService.selectGatewayCardBinById(binId);
        mmap.put("gatewayCardBin", gatewayCardBin);
        return prefix + "/edit";
    }

    /**
     * 修改保存卡Bin
     */
    @RequiresPermissions("gateway:bin:edit")
    @Log(title = "卡Bin", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GatewayCardBin gatewayCardBin)
    {
        return toAjax(gatewayCardBinService.updateGatewayCardBin(gatewayCardBin));
    }

    /**
     * 删除卡Bin
     */
    @RequiresPermissions("gateway:bin:remove")
    @Log(title = "卡Bin", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(gatewayCardBinService.deleteGatewayCardBinByIds(ids));
    }
}
