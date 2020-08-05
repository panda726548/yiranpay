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

import com.alibaba.fastjson.JSON;
import com.yiranpay.common.annotation.Log;
import com.yiranpay.common.constant.UserConstants;
import com.yiranpay.common.enums.BusinessType;
import com.yiranpay.gateway.domain.GatewayGatewayApi;
import com.yiranpay.gateway.domain.GatewayProductInfo;
import com.yiranpay.gateway.service.IGatewayProductInfoService;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.core.domain.Ztree;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.framework.util.ShiroUtils;
import com.yiranpay.common.core.page.TableDataInfo;

/**
 * 产品Controller
 * 
 * @author panda
 * @date 2020-04-12
 */
@Controller
@RequestMapping("/gateway/info")
public class GatewayProductInfoController extends BaseController
{
    private String prefix = "gateway/info";

    @Autowired
    private IGatewayProductInfoService gatewayProductInfoService;

    @RequiresPermissions("gateway:info:view")
    @GetMapping()
    public String info()
    {
        return prefix + "/info";
    }

    /**
     * 查询产品列表
     */
    @RequiresPermissions("gateway:info:list")
    @PostMapping("/list")
    @ResponseBody
    public List<GatewayProductInfo> list(GatewayProductInfo gatewayProductInfo)
    {
        List<GatewayProductInfo> list = gatewayProductInfoService.selectGatewayProductInfoList(gatewayProductInfo);
        return list;
    }

    /**
     * 导出产品列表
     */
    @RequiresPermissions("gateway:info:export")
    @Log(title = "产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GatewayProductInfo gatewayProductInfo)
    {
        List<GatewayProductInfo> list = gatewayProductInfoService.selectGatewayProductInfoList(gatewayProductInfo);
        ExcelUtil<GatewayProductInfo> util = new ExcelUtil<GatewayProductInfo>(GatewayProductInfo.class);
        return util.exportExcel(list, "info");
    }

    /**
     * 新增产品
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap)
    {
    	GatewayProductInfo productmenu = null;
        if (0L != parentId)
        {
        	productmenu = gatewayProductInfoService.selectGatewayGatewayProductById(parentId);
        }
        else
        {
        	productmenu = new GatewayProductInfo();
        	productmenu.setProductId(0L);
        	productmenu.setProductName("主目录");
        }
        mmap.put("productmenu", productmenu);
        return prefix + "/add";
    }

    /**
     * 新增保存产品
     */
    @RequiresPermissions("gateway:info:add")
    @Log(title = "产品信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GatewayProductInfo gatewayProductInfo)
    {
    	
    	if (UserConstants.API_NAME_NOT_UNIQUE.equals(gatewayProductInfoService.checkProductNameUnique(gatewayProductInfo)))
        {
            return error("新增产品'" + gatewayProductInfo.getProductName() + "'失败，产品名称已存在");
        }
    	gatewayProductInfo.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(gatewayProductInfoService.insertGatewayProductInfo(gatewayProductInfo));
    }

    /**
     * 修改产品
     */
    @GetMapping("/edit/{productId}")
    public String edit(@PathVariable("productId") Long productId, ModelMap mmap)
    {
        GatewayProductInfo gatewayProductInfo = gatewayProductInfoService.selectGatewayProductInfoById(productId);
        mmap.put("productmenu", gatewayProductInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品
     */
    @RequiresPermissions("gateway:info:edit")
    @Log(title = "产品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GatewayProductInfo gatewayProductInfo)
    {
    	if (UserConstants.API_NAME_NOT_UNIQUE.equals(gatewayProductInfoService.checkProductNameUnique(gatewayProductInfo)))
        {
            return error("修改产品【" + gatewayProductInfo.getProductName() + "】失败，接口名称已存在");
        }
    	gatewayProductInfo.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(gatewayProductInfoService.updateGatewayProductInfo(gatewayProductInfo));
    }

    /**
     * 删除产品
     */
    @RequiresPermissions("gateway:info:remove")
    @Log(title = "产品", businessType = BusinessType.DELETE)
    @GetMapping( "/remove/{productId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("productId") Long productId)
    {
    	if (gatewayProductInfoService.selectCountProductByParentId(productId) > 0)
        {
            return AjaxResult.warn("存在子菜单,不允许删除");
        }
       
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(gatewayProductInfoService.deleteGatewayProductInfoById(productId));
    }
    
    @PostMapping("/checkProductNameUnique")
    @ResponseBody
    public String checkMenuNameUnique(GatewayProductInfo gatewayProductInfo)
    {
        return gatewayProductInfoService.checkProductNameUnique(gatewayProductInfo);
    }
    
    /**
     * 选择菜单树
     */
    @GetMapping("/selectProductTree/{productId}")
    public String selectMenuTree(@PathVariable("productId") Long productId, ModelMap mmap)
    {
    	GatewayProductInfo productInfo = gatewayProductInfoService.selectProductMenuById(productId);
    	System.out.println(JSON.toJSONString(productInfo));
        mmap.put("productmenu", productInfo);
        return prefix + "/tree";
    }
    
    /**
     * 加载所有菜单列表树
     */
    @GetMapping("/productTreeData")
    @ResponseBody
    public List<Ztree> apiTreeData()
    {
        List<Ztree> ztrees = gatewayProductInfoService.productMenuTreeData();
        System.out.println(JSON.toJSONString(ztrees));
        return ztrees;
    }
    
    
}
