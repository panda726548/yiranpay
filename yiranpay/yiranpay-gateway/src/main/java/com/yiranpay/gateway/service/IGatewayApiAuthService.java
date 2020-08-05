package com.yiranpay.gateway.service;

import com.yiranpay.gateway.domain.GatewayApiAuth;
import java.util.List;

/**
 * 商户和权限关联Service接口
 * 
 * @author panda
 * @date 2020-01-06
 */
public interface IGatewayApiAuthService 
{
    /**
     * 查询商户和权限关联
     * 
     * @param apiId 商户和权限关联ID
     * @return 商户和权限关联
     */
    public GatewayApiAuth selectGatewayApiAuthById(Long apiId);

    /**
     * 查询商户和权限关联列表
     * 
     * @param gatewayApiAuth 商户和权限关联
     * @return 商户和权限关联集合
     */
    public List<GatewayApiAuth> selectGatewayApiAuthList(GatewayApiAuth gatewayApiAuth);

    /**
     * 新增商户和权限关联
     * 
     * @param gatewayApiAuth 商户和权限关联
     * @return 结果
     */
    public int insertGatewayApiAuth(GatewayApiAuth gatewayApiAuth);

    /**
     * 修改商户和权限关联
     * 
     * @param gatewayApiAuth 商户和权限关联
     * @return 结果
     */
    public int updateGatewayApiAuth(GatewayApiAuth gatewayApiAuth);

    /**
     * 批量删除商户和权限关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGatewayApiAuthByIds(String ids);

    /**
     * 删除商户和权限关联信息
     * 
     * @param apiId 商户和权限关联ID
     * @return 结果
     */
    public int deleteGatewayApiAuthById(Long apiId);
}
