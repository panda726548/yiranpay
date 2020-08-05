package com.yiranpay.web.controller.message;

import com.yiranpay.common.annotation.Log;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.core.page.TableDataInfo;
import com.yiranpay.common.enums.BusinessType;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.framework.util.ShiroUtils;
import com.yiranpay.message.domain.MsgEmail;
import com.yiranpay.message.service.IMsgEmailService;
import com.yiranpay.system.domain.SysUser;
import com.yiranpay.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.yiranpay.framework.util.ShiroUtils.getUserId;

/**
 * Email邮件Controller
 * 
 * @author glb
 * @date 2020-03-26
 */
@Controller
@RequestMapping("/message/email")
public class MsgEmailController extends BaseController
{
    private String prefix = "message/email";

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IMsgEmailService msgEmailService;

    @RequiresPermissions("message:email:view")
    @GetMapping("/{link}")
    public String view(ModelMap mmap, @PathVariable("link") String link, String type, String label) {
        mmap.put("emailLink", link);
        mmap.put("emailType", type);
        mmap.put("emailLabel", label);
        return prefix + "/email";
    }

    /**
     * 查询Email邮件列表
     */
    @RequiresPermissions("message:email:list")
    @PostMapping("/list/{link}")
    @ResponseBody
    public TableDataInfo list(MsgEmail msgEmail, @PathVariable("link") String link, String type, String label) {
        startPage();
        //设置文件夹列表
        if("0".equals(link)){
            msgEmail.setToUser(getUserId());
            msgEmail.setEmailFolder("1");
        }else{
            msgEmail.setFromUser(getUserId());
            msgEmail.setEmailFolder(link);
        }
        msgEmail.setEmailType(type);
        msgEmail.setEmailLabel(label);
        List<MsgEmail> list = msgEmailService.selectMsgEmailList(msgEmail);
        return getDataTable(list);
    }

    /**
     * 写信页
     * @param mmap
     * @return
     */
    @GetMapping("/write")
    public String write(ModelMap mmap) {
        mmap.put("users", sysUserService.selectUserList(new SysUser()));
        return prefix + "/write";
    }

    /**
     * 读信页
     * @param mmap
     * @return
     */
    @GetMapping("/read")
    public String read(ModelMap mmap,Long emailId) {
        mmap.put("email", msgEmailService.selectMsgEmailById(emailId));
        return prefix + "/read";
    }

    /**
     * 发送内部邮件
     * @param msgEmail
     * @return
     */
    @PostMapping("/sendWithInside")
    @ResponseBody
    public AjaxResult sendWithInside(MsgEmail msgEmail) {

        return toAjax(msgEmailService.sendWithInside(msgEmail, ShiroUtils.getSysUser()));
    }

    /**
     * 发送外部邮件
     * @param msgEmail
     * @return
     */
    @PostMapping("/sendWithOutside")
    @ResponseBody
    public AjaxResult sendWithOutside(MsgEmail msgEmail) {
        return toAjax(msgEmailService.sendWithOutside(msgEmail,ShiroUtils.getSysUser()));
    }

    /**
     * 移入回收站
     * @param msgEmail
     * @return
     */
    @PostMapping("/moveToRecoveryBin")
    @ResponseBody
    public AjaxResult moveToRecoveryBin(MsgEmail msgEmail) {
        return toAjax(msgEmailService.moveToRecoveryBin(msgEmail));
    }

    /**
     * 恢复到收件箱
     * @param msgEmail
     * @return
     */
    @PostMapping("/moveToInBox")
    @ResponseBody
    public AjaxResult moveToInBox(MsgEmail msgEmail) {
        return toAjax(msgEmailService.moveToInBox(msgEmail));
    }

    /**
     * 保存至草稿
     * @param msgEmail
     * @return
     */
    @PostMapping("/saveToRough")
    @ResponseBody
    public AjaxResult saveToRough(MsgEmail msgEmail) {
        return toAjax(msgEmailService.saveToRough(msgEmail,ShiroUtils.getSysUser()));
    }

    /**
     * 批量逻辑删除
     * @param ids
     * @return
     */
    @PostMapping("/logicRemove")
    @ResponseBody
    public AjaxResult logicRemove(String ids) {
        try {
            return toAjax(msgEmailService.deleteMsgEmailByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 批量物理删除
     * @param ids
     * @return
     */
    @PostMapping("/physicsRemove")
    @ResponseBody
    public AjaxResult physicsRemove(String ids) {
        try {
            return toAjax(msgEmailService.truncateEmailByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 导出Email邮件列表
     */
    @RequiresPermissions("message:email:export")
    @Log(title = "Email邮件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MsgEmail msgEmail)
    {
        List<MsgEmail> list = msgEmailService.selectMsgEmailList(msgEmail);
        ExcelUtil<MsgEmail> util = new ExcelUtil<MsgEmail>(MsgEmail.class);
        return util.exportExcel(list, "email");
    }

    /**
     * 新增Email邮件
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存Email邮件
     */
    @RequiresPermissions("message:email:add")
    @Log(title = "Email邮件", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MsgEmail msgEmail)
    {
        return toAjax(msgEmailService.insertMsgEmail(msgEmail));
    }

    /**
     * 修改Email邮件
     */
    @GetMapping("/edit/{emailId}")
    public String edit(@PathVariable("emailId") Long emailId, ModelMap mmap)
    {
        MsgEmail msgEmail = msgEmailService.selectMsgEmailById(emailId);
        mmap.put("msgEmail", msgEmail);
        return prefix + "/edit";
    }

    /**
     * 修改保存Email邮件
     */
    @RequiresPermissions("message:email:edit")
    @Log(title = "Email邮件", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MsgEmail msgEmail)
    {
        return toAjax(msgEmailService.updateMsgEmail(msgEmail));
    }

    /**
     * 删除Email邮件
     */
    @RequiresPermissions("message:email:remove")
    @Log(title = "Email邮件", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(msgEmailService.deleteMsgEmailByIds(ids));
    }
}
