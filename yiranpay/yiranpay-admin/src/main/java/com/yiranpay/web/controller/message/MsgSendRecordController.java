package com.yiranpay.web.controller.message;

import com.yiranpay.common.annotation.Log;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.page.TableDataInfo;
import com.yiranpay.common.enums.BusinessType;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.message.domain.MsgSendRecord;
import com.yiranpay.message.service.IMsgSendRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.message.domain.MsgEmail;
import com.yiranpay.message.service.IMsgEmailService;

import java.util.List;

/**
 * 短信发送记录Controller
 * 
 * @author glb
 * @date 2020-03-26
 */
@Controller
@RequestMapping("/message/sendRecord")
public class MsgSendRecordController extends BaseController
{
    private String prefix = "message/sendRecord";

    @Autowired
    private IMsgSendRecordService msgSendRecordService;

    @RequiresPermissions("message:sendRecord:view")
    @GetMapping()
    public String record()
    {
        return prefix + "/sendRecord";
    }

    /**
     * 查询短信发送记录列表
     */
    @RequiresPermissions("message:sendRecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MsgSendRecord msgSendRecord)
    {
        startPage();
        List<MsgSendRecord> list = msgSendRecordService.selectMsgSendRecordList(msgSendRecord);
        return getDataTable(list);
    }

    /**
     * 导出短信发送记录列表
     */
    @RequiresPermissions("message:sendRecord:export")
    @Log(title = "短信发送记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MsgSendRecord msgSendRecord)
    {
        List<MsgSendRecord> list = msgSendRecordService.selectMsgSendRecordList(msgSendRecord);
        ExcelUtil<MsgSendRecord> util = new ExcelUtil<MsgSendRecord>(MsgSendRecord.class);
        return util.exportExcel(list, "record");
    }

    /**
     * 新增短信发送记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存短信发送记录
     */
    @RequiresPermissions("message:sendRecord:add")
    @Log(title = "短信发送记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MsgSendRecord msgSendRecord)
    {
        return toAjax(msgSendRecordService.insertMsgSendRecord(msgSendRecord));
    }

    /**
     * 修改短信发送记录
     */
    @GetMapping("/edit/{sendRecordId}")
    public String edit(@PathVariable("sendRecordId") Long sendRecordId, ModelMap mmap)
    {
        MsgSendRecord msgSendRecord = msgSendRecordService.selectMsgSendRecordById(sendRecordId);
        mmap.put("msgSendRecord", msgSendRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存短信发送记录
     */
    @RequiresPermissions("message:sendRecord:edit")
    @Log(title = "短信发送记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MsgSendRecord msgSendRecord)
    {
        return toAjax(msgSendRecordService.updateMsgSendRecord(msgSendRecord));
    }

    /**
     * 删除短信发送记录
     */
    @RequiresPermissions("message:sendRecord:remove")
    @Log(title = "短信发送记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(msgSendRecordService.deleteMsgSendRecordByIds(ids));
    }
}
