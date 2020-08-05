package com.yiran.project.system.notice.controller;

import java.util.List;

import com.yiran.project.system.notice.service.INoticeReadService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yiran.framework.aspectj.lang.annotation.Log;
import com.yiran.framework.aspectj.lang.enums.BusinessType;
import com.yiran.framework.web.controller.BaseController;
import com.yiran.framework.web.domain.AjaxResult;
import com.yiran.framework.web.page.TableDataInfo;
import com.yiran.project.system.notice.domain.Notice;
import com.yiran.project.system.notice.service.INoticeService;

/**
 * 公告 信息操作处理
 * 
 * @author yiran
 */
@Controller
@RequestMapping("/system/notice")
public class NoticeController extends BaseController
{
    private String prefix = "system/notice";

    @Autowired
    private INoticeService noticeService;
    @Autowired
    private INoticeReadService noticeReadService;

    @GetMapping()
    public String notice()
    {
        return prefix + "/notice";
    }

    /**
     * 查询公告列表
     */

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Notice notice)
    {
        startPage();
        List<Notice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 新增公告
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存公告
     */

    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Notice notice)
    {
        return toAjax(noticeService.insertNotice(notice));
    }

    /**
     * 修改公告
     */
    @GetMapping("/edit/{noticeId}")
    public String edit(@PathVariable("noticeId") Long noticeId, ModelMap mmap)
    {
        mmap.put("notice", noticeService.selectNoticeById(noticeId));
        return prefix + "/edit";
    }

    /**
     * 修改保存公告
     */

    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Notice notice)
    {
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 删除公告
     */

    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(noticeService.deleteNoticeByIds(ids));
    }

    @GetMapping("/read/{noticeId}")
    public String read(@PathVariable("noticeId") long noticeId,ModelMap modelMap){
        modelMap.put("notice",noticeService.selectNoticeById(noticeId));

        return prefix+"/read";
    }

    @PostMapping("read")
    @ResponseBody
    public String read(long noticeId){
        noticeReadService.read(noticeId);
        return "1";
    }

}
