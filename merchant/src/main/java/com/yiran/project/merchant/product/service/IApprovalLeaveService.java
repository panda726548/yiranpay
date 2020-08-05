package com.yiran.project.merchant.product.service;

import java.util.List;

import com.yiran.project.merchant.product.domain.ApprovalLeave;

/**
 * 产品审批明细Service接口
 * 
 * @author panda
 * @date 2020-04-12
 */
public interface IApprovalLeaveService 
{
	/**
     * 新增产品审批明细
     * 
     * @param gatewayApprovalLeave 产品审批明细
     * @return 结果
     */
    public int insertApprovalLeave(ApprovalLeave approvalLeave);
    
    public List<ApprovalLeave> selectApprovalLeaveList(ApprovalLeave approvalLeave);
}
