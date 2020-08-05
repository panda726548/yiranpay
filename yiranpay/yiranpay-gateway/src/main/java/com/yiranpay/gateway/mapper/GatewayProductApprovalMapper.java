package com.yiranpay.gateway.mapper;

import com.yiranpay.gateway.domain.GatewayProductApproval;
import java.util.List;

/**
 * 产品申请Mapper接口
 * 
 * @author panda
 * @date 2020-04-12
 */
public interface GatewayProductApprovalMapper 
{
    /**
     * 查询产品申请
     * 
     * @param id 产品申请ID
     * @return 产品申请
     */
    public GatewayProductApproval selectGatewayProductApprovalById(Long id);

    /**
     * 查询产品申请列表
     * 
     * @param gatewayProductApproval 产品申请
     * @return 产品申请集合
     */
    public List<GatewayProductApproval> selectGatewayProductApprovalList(GatewayProductApproval gatewayProductApproval);

    /**
     * 新增产品申请
     * 
     * @param gatewayProductApproval 产品申请
     * @return 结果
     */
    public int insertGatewayProductApproval(GatewayProductApproval gatewayProductApproval);

    /**
     * 修改产品申请
     * 
     * @param gatewayProductApproval 产品申请
     * @return 结果
     */
    public int updateGatewayProductApproval(GatewayProductApproval gatewayProductApproval);

    /**
     * 删除产品申请
     * 
     * @param id 产品申请ID
     * @return 结果
     */
    public int deleteGatewayProductApprovalById(Long id);

    /**
     * 批量删除产品申请
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGatewayProductApprovalByIds(String[] ids);
}
