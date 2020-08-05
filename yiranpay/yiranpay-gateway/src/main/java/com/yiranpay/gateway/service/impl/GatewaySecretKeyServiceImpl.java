package com.yiranpay.gateway.service.impl;

import java.util.List;
import com.yiranpay.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiranpay.gateway.mapper.GatewaySecretKeyMapper;
import com.yiranpay.gateway.domain.GatewaySecretKey;
import com.yiranpay.gateway.service.IGatewaySecretKeyService;
import com.yiranpay.common.core.text.Convert;

/**
 * 商户秘钥管理Service业务层处理
 * 
 * @author panda
 * @date 2020-01-15
 */
@Service
public class GatewaySecretKeyServiceImpl implements IGatewaySecretKeyService 
{
    @Autowired
    private GatewaySecretKeyMapper gatewaySecretKeyMapper;

    /**
     * 查询商户秘钥管理
     * 
     * @param secretkeyId 商户秘钥管理ID
     * @return 商户秘钥管理
     */
    @Override
    public GatewaySecretKey selectGatewaySecretKeyById(Long secretkeyId)
    {
        return gatewaySecretKeyMapper.selectGatewaySecretKeyById(secretkeyId);
    }

    /**
     * 查询商户秘钥管理列表
     * 
     * @param gatewaySecretKey 商户秘钥管理
     * @return 商户秘钥管理
     */
    @Override
    public List<GatewaySecretKey> selectGatewaySecretKeyList(GatewaySecretKey gatewaySecretKey)
    {
        return gatewaySecretKeyMapper.selectGatewaySecretKeyList(gatewaySecretKey);
    }

    /**
     * 新增商户秘钥管理
     * 
     * @param gatewaySecretKey 商户秘钥管理
     * @return 结果
     */
    @Override
    public int insertGatewaySecretKey(GatewaySecretKey gatewaySecretKey)
    {
        gatewaySecretKey.setCreateTime(DateUtils.getNowDate());
        return gatewaySecretKeyMapper.insertGatewaySecretKey(gatewaySecretKey);
    }

    /**
     * 修改商户秘钥管理
     * 
     * @param gatewaySecretKey 商户秘钥管理
     * @return 结果
     */
    @Override
    public int updateGatewaySecretKey(GatewaySecretKey gatewaySecretKey)
    {
        gatewaySecretKey.setUpdateTime(DateUtils.getNowDate());
        return gatewaySecretKeyMapper.updateGatewaySecretKey(gatewaySecretKey);
    }

    /**
     * 删除商户秘钥管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGatewaySecretKeyByIds(String ids)
    {
        return gatewaySecretKeyMapper.deleteGatewaySecretKeyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商户秘钥管理信息
     * 
     * @param secretkeyId 商户秘钥管理ID
     * @return 结果
     */
    @Override
    public int deleteGatewaySecretKeyById(Long secretkeyId)
    {
        return gatewaySecretKeyMapper.deleteGatewaySecretKeyById(secretkeyId);
    }
}
