package com.yiran.project.merchant.product.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yiran.common.utils.security.ShiroUtils;
import com.yiran.framework.web.domain.AjaxResult;
import com.yiran.project.merchant.product.domain.ApprovalLeave;
import com.yiran.project.merchant.product.domain.ProductApproval;
import com.yiran.project.merchant.product.domain.ProductInfo;
import com.yiran.project.merchant.product.domain.ProductMember;
import com.yiran.project.merchant.product.service.IApprovalLeaveService;
import com.yiran.project.merchant.product.service.IProductApprovalService;
import com.yiran.project.merchant.product.service.IProductInfoService;
import com.yiran.project.merchant.product.service.IProductMemberService;

@Controller
@RequestMapping("/merchanr/product")
public class ProductController {

	private String prefix = "merchant/product";
	@Autowired
	private IProductMemberService productMemberService;
	@Autowired
	private IProductInfoService productInfoService;
	@Autowired
	private IProductApprovalService productApprovalService;
	@Autowired
	private IApprovalLeaveService approvalLeaveService;
	
	@GetMapping("/productList")
    public String productInfo(ModelMap mmap)
    {
		String memberNo = ShiroUtils.getUserId();
		ProductMember productMember = new ProductMember();
		productMember.setMemberNo(memberNo);
		//根据当前商户号查询已经申请审核通过的产品
		List<ProductMember> list = productMemberService.selectProductMemberList(productMember);
		//所有产品应用
		ProductInfo productInfo = new ProductInfo();
		List<ProductInfo> infoList = productInfoService.selectProductInfoList(productInfo);
		System.out.println("所有的产品："+JSON.toJSONString(infoList));
		//我的开通应用：
		List<ProductInfo> alreadyOpened = new ArrayList<ProductInfo>();
		//未开通
		List<ProductInfo> weiOpen = new ArrayList<ProductInfo>();
		for (ProductInfo info : infoList) {
			boolean flag = isExistence(info,list);
			if(flag){//开通
				//1.申请ID  extend1字段 根据产品code和商户号查询
				ProductApproval approval = productApprovalService.selectProductApprova(info.getProductCode(),memberNo);
				info.setExtend1(String.valueOf(approval.getId()));
				info.setExtend2(String.valueOf(approval.getProcessstatus()));//申请状态
				alreadyOpened.add(info);
			}else{
				ProductApproval approval = productApprovalService.selectProductApprova(info.getProductCode(),memberNo);
				if(approval !=null){
					if(approval.getProcessstatus() == 1){
						info.setExtend1(String.valueOf(approval.getId()));
						info.setExtend2(String.valueOf(approval.getProcessstatus()));//申请状态
						alreadyOpened.add(info);
					}
				}else{
					weiOpen.add(info);
				}
				
			}
		}
		mmap.put("alreadyOpened", alreadyOpened);
		System.out.println("已经开通产品："+JSON.toJSONString(alreadyOpened));
		mmap.put("weiOpen", weiOpen);
		System.out.println("未开通产品："+JSON.toJSONString(weiOpen));
        return prefix + "/product_list";
    }
	
	
	@GetMapping( "/application/{productCode}")
    @ResponseBody
    public AjaxResult application(@PathVariable("productCode") String productCode)
    {
		System.out.println("申请产品code："+ productCode);
		String memberNo = ShiroUtils.getUserId();
		ProductInfo productInfo = productInfoService.selectProductInfoByProductCode(productCode);
		//添加到申请表
		ProductApproval approval = new ProductApproval();
		approval.setMemberNo(memberNo);
		approval.setProductCode(productCode);
		approval.setProductName(productInfo.getProductName());
		approval.setReason("申请【"+productInfo.getProductName()+"】产品");
		approval.setProcessstatus(1);//待审批
		approval.setCreateBy(ShiroUtils.getLoginName());
		int approvalId = productApprovalService.insertProductApproval(approval);
		//申请明细表
		ApprovalLeave approvalLeave = new ApprovalLeave();
		approvalLeave.setApprovalId(Long.parseLong(String.valueOf(approval.getId())));
		approvalLeave.setUserid(ShiroUtils.getLoginName());
		approvalLeave.setAuditresult(2);//申请
		approvalLeave.setComment("申请【"+productInfo.getProductName()+"】产品");
		approvalLeave.setAudittime(new Date());
		approvalLeaveService.insertApprovalLeave(approvalLeave);
        return AjaxResult.success();
    }
	
	@GetMapping("/showApprovalLeave/{productCode}")
    public String showApprovalLeave(@PathVariable("productCode") String productCode,ModelMap mmap)
    {
		//获取产品信息
		String memberNo = ShiroUtils.getUserId();
		ProductInfo productInfo = productInfoService.selectProductInfoByProductCode(productCode);
		mmap.put("proInfo", productInfo);
		ProductApproval productApproval = productApprovalService.selectProductApprova(productCode, memberNo);
		ApprovalLeave approvalLeave = new ApprovalLeave();
		approvalLeave.setApprovalId(Long.parseLong(String.valueOf(productApproval.getId())));
		List<ApprovalLeave> leaveList = approvalLeaveService.selectApprovalLeaveList(approvalLeave);
		mmap.put("leaveList", leaveList);
        return prefix + "/showApprovalLeave";
    }
	
	private boolean isExistence(ProductInfo info, List<ProductMember> list) {
		for (ProductMember pm : list) {
			if(info.getProductCode().equals(pm.getProductCode())){//存在
				return true;
			}
		}
		return false;
	}
}
