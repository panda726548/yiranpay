package com.yiranpay.web.controller.message;

import com.yiranpay.common.annotation.Log;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.core.page.TableDataInfo;
import com.yiranpay.common.enums.BusinessType;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.message.domain.MsgSmsConfig;
import com.yiranpay.message.service.IMsgSmsConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 短息配置Controller
 * 
 * @author glb
 * @date 2020-03-26
 */
@Controller
@RequestMapping("/message/smsConfig")
public class MsgSmsConfigController extends BaseController
{
    private String prefix = "message/smsConfig";

    @Autowired
    private IMsgSmsConfigService msgSmsConfigService;

    @RequiresPermissions("message:smsConfig:view")
    @GetMapping()
    public String config()
    {
        return prefix + "/smsConfig";
    }

    /**
     * 查询短息配置列表
     */
    @RequiresPermissions("message:smsConfig:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MsgSmsConfig msgSmsConfig)
    {
        startPage();
        List<MsgSmsConfig> list = msgSmsConfigService.selectMsgSmsConfigList(msgSmsConfig);
        return getDataTable(list);
    }

    /**
     * 导出短息配置列表
     */
    @RequiresPermissions("message:smsConfig:export")
    @Log(title = "短息配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MsgSmsConfig msgSmsConfig)
    {
        List<MsgSmsConfig> list = msgSmsConfigService.selectMsgSmsConfigList(msgSmsConfig);
        ExcelUtil<MsgSmsConfig> util = new ExcelUtil<MsgSmsConfig>(MsgSmsConfig.class);
        return util.exportExcel(list, "config");
    }

    /**
     * 新增短息配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存短息配置
     */
    @RequiresPermissions("message:smsConfig:add")
    @Log(title = "短息配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MsgSmsConfig msgSmsConfig)
    {
        return toAjax(msgSmsConfigService.insertMsgSmsConfig(msgSmsConfig));
    }

    /**
     * 修改短息配置
     */
    @GetMapping("/edit/{smsConfigId}")
    public String edit(@PathVariable("smsConfigId") Long smsConfigId, ModelMap mmap)
    {
        MsgSmsConfig msgSmsConfig = msgSmsConfigService.selectMsgSmsConfigById(smsConfigId);
        mmap.put("msgSmsConfig", msgSmsConfig);
        return prefix + "/edit";
    }

    /**
     * 修改保存短息配置
     */
    @RequiresPermissions("message:smsConfig:edit")
    @Log(title = "短息配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MsgSmsConfig msgSmsConfig)
    {
        return toAjax(msgSmsConfigService.updateMsgSmsConfig(msgSmsConfig));
    }

    /**
     * 删除短息配置
     */
    @RequiresPermissions("message:smsConfig:remove")
    @Log(title = "短息配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(msgSmsConfigService.deleteMsgSmsConfigByIds(ids));
    }
}
