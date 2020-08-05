package com.yiranpay.gateway.mapper;

import com.yiranpay.gateway.domain.GatewayProductMember;
import java.util.List;

/**
 * 产品商户关联Mapper接口
 * 
 * @author panda
 * @date 2020-04-12
 */
public interface GatewayProductMemberMapper 
{
    /**
     * 查询产品商户关联
     * 
     * @param id 产品商户关联ID
     * @return 产品商户关联
     */
    public GatewayProductMember selectGatewayProductMemberById(Long id);

    /**
     * 查询产品商户关联列表
     * 
     * @param gatewayProductMember 产品商户关联
     * @return 产品商户关联集合
     */
    public List<GatewayProductMember> selectGatewayProductMemberList(GatewayProductMember gatewayProductMember);

    /**
     * 新增产品商户关联
     * 
     * @param gatewayProductMember 产品商户关联
     * @return 结果
     */
    public int insertGatewayProductMember(GatewayProductMember gatewayProductMember);

    /**
     * 修改产品商户关联
     * 
     * @param gatewayProductMember 产品商户关联
     * @return 结果
     */
    public int updateGatewayProductMember(GatewayProductMember gatewayProductMember);

    /**
     * 删除产品商户关联
     * 
     * @param id 产品商户关联ID
     * @return 结果
     */
    public int deleteGatewayProductMemberById(Long id);

    /**
     * 批量删除产品商户关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGatewayProductMemberByIds(String[] ids);
}
