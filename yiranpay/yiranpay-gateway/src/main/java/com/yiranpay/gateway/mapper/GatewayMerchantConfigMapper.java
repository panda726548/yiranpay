package com.yiranpay.gateway.mapper;

import com.yiranpay.gateway.domain.GatewayMerchantConfig;
import java.util.List;

/**
 * 商户接口配置Mapper接口
 * 
 * @author panda
 * @date 2020-01-06
 */
public interface GatewayMerchantConfigMapper 
{
    /**
     * 查询商户接口配置
     * 
     * @param id 商户接口配置ID
     * @return 商户接口配置
     */
    public GatewayMerchantConfig selectGatewayMerchantConfigById(Long id);

    /**
     * 查询商户接口配置列表
     * 
     * @param gatewayMerchantConfig 商户接口配置
     * @return 商户接口配置集合
     */
    public List<GatewayMerchantConfig> selectGatewayMerchantConfigList(GatewayMerchantConfig gatewayMerchantConfig);

    /**
     * 新增商户接口配置
     * 
     * @param gatewayMerchantConfig 商户接口配置
     * @return 结果
     */
    public int insertGatewayMerchantConfig(GatewayMerchantConfig gatewayMerchantConfig);

    /**
     * 修改商户接口配置
     * 
     * @param gatewayMerchantConfig 商户接口配置
     * @return 结果
     */
    public int updateGatewayMerchantConfig(GatewayMerchantConfig gatewayMerchantConfig);

    /**
     * 删除商户接口配置
     * 
     * @param id 商户接口配置ID
     * @return 结果
     */
    public int deleteGatewayMerchantConfigById(Long id);

    /**
     * 批量删除商户接口配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGatewayMerchantConfigByIds(String[] ids);
}
