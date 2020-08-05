package com.yiran.project.merchant.key.mapper;

import java.util.List;

import com.yiran.project.merchant.key.domain.GatewaySecretKey;

/**
 * 商户秘钥管理Mapper接口
 * 
 * @author panda
 * @date 2020-01-15
 */
public interface GatewaySecretKeyMapper 
{
    /**
     * 查询商户秘钥管理
     * 
     * @param secretkeyId 商户秘钥管理ID
     * @return 商户秘钥管理
     */
    public GatewaySecretKey selectGatewaySecretKeyById(Long secretkeyId);

    /**
     * 查询商户秘钥管理列表
     * 
     * @param gatewaySecretKey 商户秘钥管理
     * @return 商户秘钥管理集合
     */
    public List<GatewaySecretKey> selectGatewaySecretKeyList(GatewaySecretKey gatewaySecretKey);

    /**
     * 新增商户秘钥管理
     * 
     * @param gatewaySecretKey 商户秘钥管理
     * @return 结果
     */
    public int insertGatewaySecretKey(GatewaySecretKey gatewaySecretKey);

    /**
     * 修改商户秘钥管理
     * 
     * @param gatewaySecretKey 商户秘钥管理
     * @return 结果
     */
    public int updateGatewaySecretKey(GatewaySecretKey gatewaySecretKey);

    /**
     * 删除商户秘钥管理
     * 
     * @param secretkeyId 商户秘钥管理ID
     * @return 结果
     */
    public int deleteGatewaySecretKeyById(Long secretkeyId);

    /**
     * 批量删除商户秘钥管理
     * 
     * @param secretkeyIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteGatewaySecretKeyByIds(String[] secretkeyIds);
}
