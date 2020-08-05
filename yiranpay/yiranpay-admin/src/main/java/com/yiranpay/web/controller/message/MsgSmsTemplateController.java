package com.yiranpay.web.controller.message;

import com.yiranpay.common.annotation.Log;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.core.page.TableDataInfo;
import com.yiranpay.common.enums.BusinessType;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.message.domain.MsgSmsTemplate;
import com.yiranpay.message.service.IMsgSmsSendService;
import com.yiranpay.message.service.IMsgSmsTemplateService;
import com.yiranpay.message.vo.SendAuthCodeRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 短息模板Controller
 * 
 * @author glb
 * @date 2020-03-26
 */
@Controller
@RequestMapping("/message/smsTemplate")
public class MsgSmsTemplateController extends BaseController
{
    private String prefix = "message/smsTemplate";

    @Autowired
    private IMsgSmsTemplateService msgSmsTemplateService;

    @Autowired
    private IMsgSmsSendService smsSendService;

    @RequiresPermissions("message:smsTemplate:view")
    @GetMapping()
    public String template()
    {
        return prefix + "/smsTemplate";
    }

    /**
     * 查询短息模板列表
     */
    @RequiresPermissions("message:smsTemplate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MsgSmsTemplate msgSmsTemplate)
    {
        startPage();
        List<MsgSmsTemplate> list = msgSmsTemplateService.selectMsgSmsTemplateList(msgSmsTemplate);
        return getDataTable(list);
    }

    /**
     * 导出短息模板列表
     */
    @RequiresPermissions("message:smsTemplate:export")
    @Log(title = "短息模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MsgSmsTemplate msgSmsTemplate)
    {
        List<MsgSmsTemplate> list = msgSmsTemplateService.selectMsgSmsTemplateList(msgSmsTemplate);
        ExcelUtil<MsgSmsTemplate> util = new ExcelUtil<MsgSmsTemplate>(MsgSmsTemplate.class);
        return util.exportExcel(list, "template");
    }

    /**
     * 新增短息模板
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存短息模板
     */
    @RequiresPermissions("message:smsTemplate:add")
    @Log(title = "短息模板", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MsgSmsTemplate msgSmsTemplate)
    {
        return toAjax(msgSmsTemplateService.insertMsgSmsTemplate(msgSmsTemplate));
    }

    /**
     * 修改短息模板
     */
    @GetMapping("/edit/{smsTemplateId}")
    public String edit(@PathVariable("smsTemplateId") Long smsTemplateId, ModelMap mmap)
    {
        MsgSmsTemplate msgSmsTemplate = msgSmsTemplateService.selectMsgSmsTemplateById(smsTemplateId);
        mmap.put("msgSmsTemplate", msgSmsTemplate);
        return prefix + "/edit";
    }

    /**
     * 修改保存短息模板
     */
    @RequiresPermissions("message:smsTemplate:edit")
    @Log(title = "短息模板", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MsgSmsTemplate msgSmsTemplate)
    {
        return toAjax(msgSmsTemplateService.updateMsgSmsTemplate(msgSmsTemplate));
    }

    /**
     * 删除短息模板
     */
    @RequiresPermissions("message:smsTemplate:remove")
    @Log(title = "短息模板", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(msgSmsTemplateService.deleteMsgSmsTemplateByIds(ids));
    }

    @RequiresPermissions("message:smsTemplate:sendSms")
    @GetMapping("/sendSms")
    public String sendSms()
    {
        return prefix + "/sendSms";
    }

    @RequiresPermissions("message:smsTemplate:send")
    @Log(title = "发送短信", businessType = BusinessType.INSERT)
    @PostMapping("/send")
    @ResponseBody
    public AjaxResult send(SendAuthCodeRequest request)
    {
        return smsSendService.sendAuthCode(request);
    }

    @RequiresPermissions("message:smsTemplate:send")
    @Log(title = "批量发送短信", businessType = BusinessType.INSERT)
    @PostMapping("/batchSend")
    @ResponseBody
    public AjaxResult batchSend(SendAuthCodeRequest request)
    {
        request.setMsgSendType(1);
        return smsSendService.sendAuthCode(request);
    }

    @RequiresPermissions("message:smsTemplate:verifyCode")
    @Log(title = "短信验证码验证", businessType = BusinessType.INSERT)
    @PostMapping("/verifyCode")
    @ResponseBody
    public AjaxResult verifyCode(SendAuthCodeRequest request)
    {
        return smsSendService.verifyMobileAuthCode(request);
    }
}
