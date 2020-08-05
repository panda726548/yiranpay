package com.yiranpay.web.controller.payorder;

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
import com.yiranpay.payorder.domain.SysUesEnData;
import com.yiranpay.payorder.enums.EncryptType;
import com.yiranpay.payorder.service.ISysUesEnDataService;
import com.yiranpay.payorder.service.IUesServiceClient;

import cn.hutool.json.JSONUtil;

import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.common.core.page.TableDataInfo;

/**
 * UES数据加密Controller
 * 
 * @author panda
 * @date 2020-03-03
 */
@Controller
@RequestMapping("/payorder/data")
public class SysUesEnDataController extends BaseController
{
    private String prefix = "payorder/data";

    @Autowired
    private ISysUesEnDataService sysUesEnDataService;
    @Autowired
	private IUesServiceClient uesServiceClient;
    @RequiresPermissions("payorder:data:view")
    @GetMapping()
    public String data()
    {
        return prefix + "/data";
    }

    /**
     * 查询UES数据加密列表
     */
    @RequiresPermissions("payorder:data:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysUesEnData sysUesEnData)
    {
        startPage();
        List<SysUesEnData> list = sysUesEnDataService.selectSysUesEnDataList(sysUesEnData);
        return getDataTable(list);
    }

    /**
     * 导出UES数据加密列表
     */
    @RequiresPermissions("payorder:data:export")
    @Log(title = "UES数据加密", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysUesEnData sysUesEnData)
    {
        List<SysUesEnData> list = sysUesEnDataService.selectSysUesEnDataList(sysUesEnData);
        ExcelUtil<SysUesEnData> util = new ExcelUtil<SysUesEnData>(SysUesEnData.class);
        return util.exportExcel(list, "data");
    }

    /**
     * 新增UES数据加密
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存UES数据加密
     */
    @RequiresPermissions("payorder:data:add")
    @Log(title = "UES数据加密", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysUesEnData sysUesEnData)
    {
        return toAjax(sysUesEnDataService.insertSysUesEnData(sysUesEnData));
    }

    /**
     * 修改UES数据加密
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SysUesEnData sysUesEnData = sysUesEnDataService.selectSysUesEnDataById(id);
        mmap.put("sysUesEnData", sysUesEnData);
        return prefix + "/edit";
    }

    /**
     * 修改保存UES数据加密
     */
    @RequiresPermissions("payorder:data:edit")
    @Log(title = "UES数据加密", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysUesEnData sysUesEnData)
    {
        return toAjax(sysUesEnDataService.updateSysUesEnData(sysUesEnData));
    }

    /**
     * 删除UES数据加密
     */
    @RequiresPermissions("payorder:data:remove")
    @Log(title = "UES数据加密", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysUesEnDataService.deleteSysUesEnDataByIds(ids));
    }
    /**
	 * 加密页面
	 */
	@GetMapping("/encrypt")
	@RequiresPermissions("payorder:data:encrypt")
	public String encrypt()
	{
	    return prefix + "/encrypt";
	}
    /**
	 * 加密
	 * @throws Exception 
	 */
	@PostMapping("/encrypt")
	@ResponseBody
	public AjaxResult doEncrypt(SysUesEnData uesEnData)
	{	
		
		logger.info("加密原文:{}",JSONUtil.toJsonStr(uesEnData));
		String ticket = "";
		try {
			ticket = uesServiceClient.encryptData(uesEnData.getOriginalText(), EncryptType.AES);
		} catch (Exception e) {
			AjaxResult.error("调用UES加密异常");
		}
		logger.info("加密后ticket:{}",ticket);
		 //加密具体操作
		 return AjaxResult.success("加密成功!",ticket);
	}

	/**
	 * 解密
	 */
	@GetMapping("/decrypt")
	@RequiresPermissions("payorder:data:decrypt")
	public String decrypt()
	{
	    return prefix + "/decrypt";
	}
	
	/**
	 * 解密
	 */
	@PostMapping("/decrypt")
	@ResponseBody
	public AjaxResult doDecrypt(SysUesEnData uesEnData)
	{	
		logger.info("票据ticket:{}",JSONUtil.toJsonStr(uesEnData));
		String originalText = uesServiceClient.getDataByTicket(uesEnData.getTicket(), EncryptType.AES);
		logger.info("根据票据ticket:{},解密原文:{}",uesEnData.getTicket(),originalText);
		//解密具体操作
		 return AjaxResult.success("解密成功!",originalText);
	}
}
