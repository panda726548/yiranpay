package com.yiranpay.web.controller.gateway;

import java.util.Date;
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
import com.yiranpay.gateway.domain.GatewayApprovalLeave;
import com.yiranpay.gateway.domain.GatewayProductApproval;
import com.yiranpay.gateway.domain.GatewayProductMember;
import com.yiranpay.gateway.service.IGatewayApprovalLeaveService;
import com.yiranpay.gateway.service.IGatewayProductApprovalService;
import com.yiranpay.gateway.service.IGatewayProductMemberService;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.framework.util.ShiroUtils;
import com.yiranpay.common.core.page.TableDataInfo;

/**
 * 产品申请Controller
 * 
 * @author panda
 * @date 2020-04-12
 */
@Controller
@RequestMapping("/gateway/approval")
public class GatewayProductApprovalController extends BaseController
{
    private String prefix = "gateway/approval";

    @Autowired
    private IGatewayProductApprovalService gatewayProductApprovalService;
    
    @Autowired
    private IGatewayApprovalLeaveService gatewayApprovalLeaveService;
    
    @Autowired
    private IGatewayProductMemberService gatewayProductMemberService;

    @RequiresPermissions("gateway:approval:view")
    @GetMapping()
    public String approval()
    {
        return prefix + "/approval";
    }

    /**
     * 查询产品申请列表
     */
    @RequiresPermissions("gateway:approval:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GatewayProductApproval gatewayProductApproval)
    {
        startPage();
        List<GatewayProductApproval> list = gatewayProductApprovalService.selectGatewayProductApprovalList(gatewayProductApproval);
        return getDataTable(list);
    }

    /**
     * 导出产品申请列表
     */
    @RequiresPermissions("gateway:approval:export")
    @Log(title = "产品申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GatewayProductApproval gatewayProductApproval)
    {
        List<GatewayProductApproval> list = gatewayProductApprovalService.selectGatewayProductApprovalList(gatewayProductApproval);
        ExcelUtil<GatewayProductApproval> util = new ExcelUtil<GatewayProductApproval>(GatewayProductApproval.class);
        return util.exportExcel(list, "approval");
    }

    /**
     * 新增产品申请
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产品申请
     */
    @RequiresPermissions("gateway:approval:add")
    @Log(title = "产品申请", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GatewayProductApproval gatewayProductApproval)
    {
        return toAjax(gatewayProductApprovalService.insertGatewayProductApproval(gatewayProductApproval));
    }

    /**
     * 修改产品申请
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        GatewayProductApproval gatewayProductApproval = gatewayProductApprovalService.selectGatewayProductApprovalById(id);
        mmap.put("gatewayProductApproval", gatewayProductApproval);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品申请
     */
    @RequiresPermissions("gateway:approval:edit")
    @Log(title = "产品申请", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GatewayProductApproval gatewayProductApproval)
    {
    	//更新申请表状态和审批意见
    	gatewayProductApproval.setProcessstatus(2);
    	gatewayProductApproval.setUpdateBy(ShiroUtils.getLoginName());
    	gatewayProductApprovalService.updateGatewayProductApproval(gatewayProductApproval);
    	//明细表添加数据
    	GatewayApprovalLeave approvalLeave = new GatewayApprovalLeave();
		approvalLeave.setApprovalId(Long.parseLong(String.valueOf(gatewayProductApproval.getId())));
		approvalLeave.setUserid(ShiroUtils.getLoginName());
		approvalLeave.setAuditresult(1);//通过
		approvalLeave.setComment(gatewayProductApproval.getReason());
		approvalLeave.setAudittime(new Date());
    	gatewayApprovalLeaveService.insertGatewayApprovalLeave(approvalLeave);
    	//产品商户关联表添加数据
    	GatewayProductMember productMember = new GatewayProductMember();
    	GatewayProductApproval approval = gatewayProductApprovalService.selectGatewayProductApprovalById(gatewayProductApproval.getId());
    	productMember.setMemberNo(approval.getMemberNo());
    	productMember.setProductCode(approval.getProductCode());
    	//开始时间和结束时间  默认有效期一年
    	Date startTime = new Date();
    	productMember.setStartTime(startTime);
    	// 日期时间偏移
        DateTime dateTime = DateUtil.offset(startTime, DateField.YEAR, 1);//默认一年
        productMember.setEndTime(dateTime);
    	gatewayProductMemberService.insertGatewayProductMember(productMember);
        return toAjax(1);
    }

    /**
     * 删除产品申请
     */
    @RequiresPermissions("gateway:approval:remove")
    @Log(title = "产品申请", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(gatewayProductApprovalService.deleteGatewayProductApprovalByIds(ids));
    }
}
