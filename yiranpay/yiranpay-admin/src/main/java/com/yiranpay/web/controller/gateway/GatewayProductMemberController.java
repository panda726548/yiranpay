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
import com.yiranpay.gateway.domain.GatewayProductMember;
import com.yiranpay.gateway.service.IGatewayProductMemberService;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.common.core.page.TableDataInfo;

/**
 * 产品商户关联Controller
 * 
 * @author panda
 * @date 2020-04-12
 */
@Controller
@RequestMapping("/gateway/member")
public class GatewayProductMemberController extends BaseController
{
    private String prefix = "gateway/member";

    @Autowired
    private IGatewayProductMemberService gatewayProductMemberService;

    @RequiresPermissions("gateway:member:view")
    @GetMapping()
    public String member()
    {
        return prefix + "/member";
    }

    /**
     * 查询产品商户关联列表
     */
    @RequiresPermissions("gateway:member:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GatewayProductMember gatewayProductMember)
    {
        startPage();
        List<GatewayProductMember> list = gatewayProductMemberService.selectGatewayProductMemberList(gatewayProductMember);
        return getDataTable(list);
    }

    /**
     * 导出产品商户关联列表
     */
    @RequiresPermissions("gateway:member:export")
    @Log(title = "产品商户关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GatewayProductMember gatewayProductMember)
    {
        List<GatewayProductMember> list = gatewayProductMemberService.selectGatewayProductMemberList(gatewayProductMember);
        ExcelUtil<GatewayProductMember> util = new ExcelUtil<GatewayProductMember>(GatewayProductMember.class);
        return util.exportExcel(list, "member");
    }

    /**
     * 新增产品商户关联
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产品商户关联
     */
    @RequiresPermissions("gateway:member:add")
    @Log(title = "产品商户关联", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GatewayProductMember gatewayProductMember)
    {
        return toAjax(gatewayProductMemberService.insertGatewayProductMember(gatewayProductMember));
    }

    /**
     * 修改产品商户关联
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        GatewayProductMember gatewayProductMember = gatewayProductMemberService.selectGatewayProductMemberById(id);
        mmap.put("gatewayProductMember", gatewayProductMember);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品商户关联
     */
    @RequiresPermissions("gateway:member:edit")
    @Log(title = "产品商户关联", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GatewayProductMember gatewayProductMember)
    {
        return toAjax(gatewayProductMemberService.updateGatewayProductMember(gatewayProductMember));
    }

    /**
     * 删除产品商户关联
     */
    @RequiresPermissions("gateway:member:remove")
    @Log(title = "产品商户关联", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(gatewayProductMemberService.deleteGatewayProductMemberByIds(ids));
    }
}
