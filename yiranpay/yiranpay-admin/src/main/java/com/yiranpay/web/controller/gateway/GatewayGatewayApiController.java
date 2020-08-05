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
import com.yiranpay.common.constant.UserConstants;
import com.yiranpay.common.enums.BusinessType;
import com.yiranpay.gateway.domain.GatewayGatewayApi;
import com.yiranpay.gateway.domain.GatewayMerchantConfig;
import com.yiranpay.gateway.service.IGatewayGatewayApiService;
import com.yiranpay.system.domain.SysRole;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.core.domain.Ztree;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.framework.util.ShiroUtils;

/**
 * 接口权限Controller
 * 
 * @author panda
 * @date 2020-01-06
 */
@Controller
@RequestMapping("/gateway/api")
public class GatewayGatewayApiController extends BaseController
{
    private String prefix = "gateway/api";

    @Autowired
    private IGatewayGatewayApiService gatewayGatewayApiService;

    @RequiresPermissions("gateway:api:view")
    @GetMapping()
    public String api()
    {
        return prefix + "/api2";
    }

    /**
     * 查询接口权限列表
     */
    @RequiresPermissions("gateway:api:list")
    @PostMapping("/list")
    @ResponseBody
    public List<GatewayGatewayApi> list(GatewayGatewayApi gatewayGatewayApi)
    {
        List<GatewayGatewayApi> apiList = gatewayGatewayApiService.selectMenuList(gatewayGatewayApi);
        return apiList;
    }

    /**
     * 导出接口权限列表
     */
    @RequiresPermissions("gateway:api:export")
    @Log(title = "接口权限", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GatewayGatewayApi gatewayGatewayApi)
    {
        List<GatewayGatewayApi> list = gatewayGatewayApiService.selectGatewayGatewayApiList(gatewayGatewayApi);
        ExcelUtil<GatewayGatewayApi> util = new ExcelUtil<GatewayGatewayApi>(GatewayGatewayApi.class);
        return util.exportExcel(list, "api");
    }

    /**
     * 新增接口权限
     */
    /**
     * 新增
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap)
    {
    	GatewayGatewayApi apiMmenu = null;
        if (0L != parentId)
        {
        	apiMmenu = gatewayGatewayApiService.selectGatewayGatewayApiById(parentId);
        }
        else
        {
        	apiMmenu = new GatewayGatewayApi();
        	apiMmenu.setApiId(0L);
        	apiMmenu.setApiName("主目录");
        }
        mmap.put("apimenu", apiMmenu);
        return prefix + "/add";
    }

    /**
     * 新增保存接口权限
     */
    @RequiresPermissions("gateway:api:add")
    @Log(title = "接口权限", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GatewayGatewayApi gatewayGatewayApi)
    {
    	logger.info("保存接口权限:"+JSON.toJSONString(gatewayGatewayApi));
    	if (UserConstants.API_NAME_NOT_UNIQUE.equals(gatewayGatewayApiService.checkApiNameUnique(gatewayGatewayApi)))
        {
            return error("新增API接口权限菜单'" + gatewayGatewayApi.getApiName() + "'失败，接口名称已存在");
        }
    	gatewayGatewayApi.setCreateBy(ShiroUtils.getLoginName());
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(gatewayGatewayApiService.insertGatewayGatewayApi(gatewayGatewayApi));
    }

    /**
     * 修改接口权限
     */
    @GetMapping("/edit/{apiId}")
    public String edit(@PathVariable("apiId") Long apiId, ModelMap mmap)
    {
        GatewayGatewayApi gatewayGatewayApi = gatewayGatewayApiService.selectGatewayGatewayApiById(apiId);
        mmap.put("apimenu", gatewayGatewayApi);
        return prefix + "/edit";
    }

    /**
     * 修改保存接口权限
     */
    @RequiresPermissions("gateway:api:edit")
    @Log(title = "接口权限", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GatewayGatewayApi gatewayGatewayApi)
    {
    	if (UserConstants.API_NAME_NOT_UNIQUE.equals(gatewayGatewayApiService.checkApiNameUnique(gatewayGatewayApi)))
        {
            return error("修改接口'" + gatewayGatewayApi.getApiName() + "'失败，接口名称已存在");
        }
    	gatewayGatewayApi.setUpdateBy(ShiroUtils.getLoginName());
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(gatewayGatewayApiService.updateGatewayGatewayApi(gatewayGatewayApi));
    }

    /**
     * 删除接口权限
     */
    @RequiresPermissions("gateway:api:remove")
    @Log(title = "接口权限", businessType = BusinessType.DELETE)
    @PostMapping( "/remove/{apiId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("apiId") Long apiId)
    {
    	if (gatewayGatewayApiService.selectCountApiByParentId(apiId) > 0)
        {
            return AjaxResult.warn("存在子菜单,不允许删除");
        }
       
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(gatewayGatewayApiService.deleteApiById(apiId));
    }
    
    /**
     * 校验接口名称
     */
    @PostMapping("/checkApiNameUnique")
    @ResponseBody
    public String checkMenuNameUnique(GatewayGatewayApi gatewayGatewayApi)
    {
        return gatewayGatewayApiService.checkApiNameUnique(gatewayGatewayApi);
    }
    
    /**
     * 选择菜单树
     */
    @GetMapping("/selectApiTree/{apiId}")
    public String selectMenuTree(@PathVariable("apiId") Long apiId, ModelMap mmap)
    {
        mmap.put("apimenu", gatewayGatewayApiService.selectApiMenuById(apiId));
        return prefix + "/tree";
    }
    
    /**
     * 加载所有菜单列表树
     */
    @GetMapping("/apiTreeData")
    @ResponseBody
    public List<Ztree> apiTreeData()
    {
        List<Ztree> ztrees = gatewayGatewayApiService.apiMenuTreeData();
        return ztrees;
    }
    
    /**
     * 加载商户API接口菜单列表树
     */
    @GetMapping("/apiMenuAuthTreeData")
    @ResponseBody
    public List<Ztree> apiMenuAuthTreeData(GatewayMerchantConfig config)
    {
        List<Ztree> ztrees = gatewayGatewayApiService.apiMenuAuthTreeData(config);
        return ztrees;
    }
}
