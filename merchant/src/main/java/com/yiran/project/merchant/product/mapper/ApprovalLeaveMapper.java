package com.yiran.project.merchant.product.mapper;

import java.util.List;

import com.yiran.project.merchant.product.domain.ApprovalLeave;

/**
 * 产品审批明细Mapper接口
 * 
 * @author panda
 * @date 2020-04-12
 */
public interface ApprovalLeaveMapper 
{
    
    /**
     * 查询产品审批明细列表
     * 
     * @param gatewayApprovalLeave 产品审批明细
     * @return 产品审批明细集合
     */
    public List<ApprovalLeave> selectApprovalLeaveList(ApprovalLeave approvalLeave);

    /**
     * 新增产品审批明细
     * 
     * @param gatewayApprovalLeave 产品审批明细
     * @return 结果
     */
    public int insertApprovalLeave(ApprovalLeave approvalLeave);

   
}
