package com.yiranpay.gateway.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiranpay.gateway.mapper.GatewayApiAuthMapper;
import com.yiranpay.gateway.domain.GatewayApiAuth;
import com.yiranpay.gateway.service.IGatewayApiAuthService;
import com.yiranpay.common.core.text.Convert;

/**
 * 商户和权限关联Service业务层处理
 * 
 * @author panda
 * @date 2020-01-06
 */
@Service
public class GatewayApiAuthServiceImpl implements IGatewayApiAuthService 
{
    @Autowired
    private GatewayApiAuthMapper gatewayApiAuthMapper;

    /**
     * 查询商户和权限关联
     * 
     * @param apiId 商户和权限关联ID
     * @return 商户和权限关联
     */
    @Override
    public GatewayApiAuth selectGatewayApiAuthById(Long apiId)
    {
        return gatewayApiAuthMapper.selectGatewayApiAuthById(apiId);
    }

    /**
     * 查询商户和权限关联列表
     * 
     * @param gatewayApiAuth 商户和权限关联
     * @return 商户和权限关联
     */
    @Override
    public List<GatewayApiAuth> selectGatewayApiAuthList(GatewayApiAuth gatewayApiAuth)
    {
        return gatewayApiAuthMapper.selectGatewayApiAuthList(gatewayApiAuth);
    }

    /**
     * 新增商户和权限关联
     * 
     * @param gatewayApiAuth 商户和权限关联
     * @return 结果
     */
    @Override
    public int insertGatewayApiAuth(GatewayApiAuth gatewayApiAuth)
    {
        return gatewayApiAuthMapper.insertGatewayApiAuth(gatewayApiAuth);
    }

    /**
     * 修改商户和权限关联
     * 
     * @param gatewayApiAuth 商户和权限关联
     * @return 结果
     */
    @Override
    public int updateGatewayApiAuth(GatewayApiAuth gatewayApiAuth)
    {
        return gatewayApiAuthMapper.updateGatewayApiAuth(gatewayApiAuth);
    }

    /**
     * 删除商户和权限关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGatewayApiAuthByIds(String ids)
    {
        return gatewayApiAuthMapper.deleteGatewayApiAuthByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商户和权限关联信息
     * 
     * @param apiId 商户和权限关联ID
     * @return 结果
     */
    @Override
    public int deleteGatewayApiAuthById(Long apiId)
    {
        return gatewayApiAuthMapper.deleteGatewayApiAuthById(apiId);
    }
}
