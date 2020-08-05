package com.yiranpay.gateway.service.impl;

import java.util.List;
import com.yiranpay.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiranpay.gateway.mapper.GatewayIpWhiteMapper;
import com.yiranpay.gateway.domain.GatewayIpWhite;
import com.yiranpay.gateway.service.IGatewayIpWhiteService;
import com.yiranpay.common.core.text.Convert;

/**
 * IP白名单Service业务层处理
 * 
 * @author panda
 * @date 2020-01-09
 */
@Service
public class GatewayIpWhiteServiceImpl implements IGatewayIpWhiteService 
{
    @Autowired
    private GatewayIpWhiteMapper gatewayIpWhiteMapper;

    /**
     * 查询IP白名单
     * 
     * @param id IP白名单ID
     * @return IP白名单
     */
    @Override
    public GatewayIpWhite selectGatewayIpWhiteById(Long id)
    {
        return gatewayIpWhiteMapper.selectGatewayIpWhiteById(id);
    }

    /**
     * 查询IP白名单列表
     * 
     * @param gatewayIpWhite IP白名单
     * @return IP白名单
     */
    @Override
    public List<GatewayIpWhite> selectGatewayIpWhiteList(GatewayIpWhite gatewayIpWhite)
    {
        return gatewayIpWhiteMapper.selectGatewayIpWhiteList(gatewayIpWhite);
    }

    /**
     * 新增IP白名单
     * 
     * @param gatewayIpWhite IP白名单
     * @return 结果
     */
    @Override
    public int insertGatewayIpWhite(GatewayIpWhite gatewayIpWhite)
    {
        gatewayIpWhite.setCreateTime(DateUtils.getNowDate());
        return gatewayIpWhiteMapper.insertGatewayIpWhite(gatewayIpWhite);
    }

    /**
     * 修改IP白名单
     * 
     * @param gatewayIpWhite IP白名单
     * @return 结果
     */
    @Override
    public int updateGatewayIpWhite(GatewayIpWhite gatewayIpWhite)
    {
        gatewayIpWhite.setUpdateTime(DateUtils.getNowDate());
        return gatewayIpWhiteMapper.updateGatewayIpWhite(gatewayIpWhite);
    }

    /**
     * 删除IP白名单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGatewayIpWhiteByIds(String ids)
    {
        return gatewayIpWhiteMapper.deleteGatewayIpWhiteByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除IP白名单信息
     * 
     * @param id IP白名单ID
     * @return 结果
     */
    @Override
    public int deleteGatewayIpWhiteById(Long id)
    {
        return gatewayIpWhiteMapper.deleteGatewayIpWhiteById(id);
    }

	@Override
	public GatewayIpWhite selectGatewayIpWhiteByMerchantId(String merchantId) {
		return gatewayIpWhiteMapper.selectGatewayIpWhiteByMerchantId(merchantId);
	}
}
