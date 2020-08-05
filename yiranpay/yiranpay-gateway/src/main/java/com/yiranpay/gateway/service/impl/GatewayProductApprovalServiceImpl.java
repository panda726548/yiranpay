package com.yiranpay.gateway.service.impl;

import java.util.List;
import com.yiranpay.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiranpay.gateway.mapper.GatewayProductApprovalMapper;
import com.yiranpay.gateway.domain.GatewayProductApproval;
import com.yiranpay.gateway.service.IGatewayProductApprovalService;
import com.yiranpay.common.core.text.Convert;

/**
 * 产品申请Service业务层处理
 * 
 * @author panda
 * @date 2020-04-12
 */
@Service
public class GatewayProductApprovalServiceImpl implements IGatewayProductApprovalService 
{
    @Autowired
    private GatewayProductApprovalMapper gatewayProductApprovalMapper;

    /**
     * 查询产品申请
     * 
     * @param id 产品申请ID
     * @return 产品申请
     */
    @Override
    public GatewayProductApproval selectGatewayProductApprovalById(Long id)
    {
        return gatewayProductApprovalMapper.selectGatewayProductApprovalById(id);
    }

    /**
     * 查询产品申请列表
     * 
     * @param gatewayProductApproval 产品申请
     * @return 产品申请
     */
    @Override
    public List<GatewayProductApproval> selectGatewayProductApprovalList(GatewayProductApproval gatewayProductApproval)
    {
        return gatewayProductApprovalMapper.selectGatewayProductApprovalList(gatewayProductApproval);
    }

    /**
     * 新增产品申请
     * 
     * @param gatewayProductApproval 产品申请
     * @return 结果
     */
    @Override
    public int insertGatewayProductApproval(GatewayProductApproval gatewayProductApproval)
    {
        gatewayProductApproval.setCreateTime(DateUtils.getNowDate());
        return gatewayProductApprovalMapper.insertGatewayProductApproval(gatewayProductApproval);
    }

    /**
     * 修改产品申请
     * 
     * @param gatewayProductApproval 产品申请
     * @return 结果
     */
    @Override
    public int updateGatewayProductApproval(GatewayProductApproval gatewayProductApproval)
    {
        gatewayProductApproval.setUpdateTime(DateUtils.getNowDate());
        return gatewayProductApprovalMapper.updateGatewayProductApproval(gatewayProductApproval);
    }

    /**
     * 删除产品申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGatewayProductApprovalByIds(String ids)
    {
        return gatewayProductApprovalMapper.deleteGatewayProductApprovalByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品申请信息
     * 
     * @param id 产品申请ID
     * @return 结果
     */
    @Override
    public int deleteGatewayProductApprovalById(Long id)
    {
        return gatewayProductApprovalMapper.deleteGatewayProductApprovalById(id);
    }
}
