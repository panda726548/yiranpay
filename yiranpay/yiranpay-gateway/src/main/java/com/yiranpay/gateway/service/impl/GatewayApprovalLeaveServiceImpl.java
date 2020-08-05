package com.yiranpay.gateway.service.impl;

import java.util.List;
import com.yiranpay.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiranpay.gateway.mapper.GatewayApprovalLeaveMapper;
import com.yiranpay.gateway.domain.GatewayApprovalLeave;
import com.yiranpay.gateway.service.IGatewayApprovalLeaveService;
import com.yiranpay.common.core.text.Convert;

/**
 * 产品审批明细Service业务层处理
 * 
 * @author panda
 * @date 2020-04-12
 */
@Service
public class GatewayApprovalLeaveServiceImpl implements IGatewayApprovalLeaveService 
{
    @Autowired
    private GatewayApprovalLeaveMapper gatewayApprovalLeaveMapper;

    /**
     * 查询产品审批明细
     * 
     * @param auditid 产品审批明细ID
     * @return 产品审批明细
     */
    @Override
    public GatewayApprovalLeave selectGatewayApprovalLeaveById(Long auditid)
    {
        return gatewayApprovalLeaveMapper.selectGatewayApprovalLeaveById(auditid);
    }

    /**
     * 查询产品审批明细列表
     * 
     * @param gatewayApprovalLeave 产品审批明细
     * @return 产品审批明细
     */
    @Override
    public List<GatewayApprovalLeave> selectGatewayApprovalLeaveList(GatewayApprovalLeave gatewayApprovalLeave)
    {
        return gatewayApprovalLeaveMapper.selectGatewayApprovalLeaveList(gatewayApprovalLeave);
    }

    /**
     * 新增产品审批明细
     * 
     * @param gatewayApprovalLeave 产品审批明细
     * @return 结果
     */
    @Override
    public int insertGatewayApprovalLeave(GatewayApprovalLeave gatewayApprovalLeave)
    {
        gatewayApprovalLeave.setCreateTime(DateUtils.getNowDate());
        return gatewayApprovalLeaveMapper.insertGatewayApprovalLeave(gatewayApprovalLeave);
    }

    /**
     * 修改产品审批明细
     * 
     * @param gatewayApprovalLeave 产品审批明细
     * @return 结果
     */
    @Override
    public int updateGatewayApprovalLeave(GatewayApprovalLeave gatewayApprovalLeave)
    {
        gatewayApprovalLeave.setUpdateTime(DateUtils.getNowDate());
        return gatewayApprovalLeaveMapper.updateGatewayApprovalLeave(gatewayApprovalLeave);
    }

    /**
     * 删除产品审批明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGatewayApprovalLeaveByIds(String ids)
    {
        return gatewayApprovalLeaveMapper.deleteGatewayApprovalLeaveByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品审批明细信息
     * 
     * @param auditid 产品审批明细ID
     * @return 结果
     */
    @Override
    public int deleteGatewayApprovalLeaveById(Long auditid)
    {
        return gatewayApprovalLeaveMapper.deleteGatewayApprovalLeaveById(auditid);
    }
}
