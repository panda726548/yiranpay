package com.yiranpay.gateway.service;

import com.yiranpay.gateway.domain.GatewayApprovalLeave;
import java.util.List;

/**
 * 产品审批明细Service接口
 * 
 * @author panda
 * @date 2020-04-12
 */
public interface IGatewayApprovalLeaveService 
{
    /**
     * 查询产品审批明细
     * 
     * @param auditid 产品审批明细ID
     * @return 产品审批明细
     */
    public GatewayApprovalLeave selectGatewayApprovalLeaveById(Long auditid);

    /**
     * 查询产品审批明细列表
     * 
     * @param gatewayApprovalLeave 产品审批明细
     * @return 产品审批明细集合
     */
    public List<GatewayApprovalLeave> selectGatewayApprovalLeaveList(GatewayApprovalLeave gatewayApprovalLeave);

    /**
     * 新增产品审批明细
     * 
     * @param gatewayApprovalLeave 产品审批明细
     * @return 结果
     */
    public int insertGatewayApprovalLeave(GatewayApprovalLeave gatewayApprovalLeave);

    /**
     * 修改产品审批明细
     * 
     * @param gatewayApprovalLeave 产品审批明细
     * @return 结果
     */
    public int updateGatewayApprovalLeave(GatewayApprovalLeave gatewayApprovalLeave);

    /**
     * 批量删除产品审批明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGatewayApprovalLeaveByIds(String ids);

    /**
     * 删除产品审批明细信息
     * 
     * @param auditid 产品审批明细ID
     * @return 结果
     */
    public int deleteGatewayApprovalLeaveById(Long auditid);
}
