package com.yiranpay.gateway.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiranpay.gateway.mapper.GatewayCardBinMapper;
import com.yiranpay.gateway.domain.GatewayCardBin;
import com.yiranpay.gateway.service.IGatewayCardBinService;
import com.yiranpay.common.core.text.Convert;

/**
 * 卡BinService业务层处理
 * 
 * @author panda
 * @date 2020-01-06
 */
@Service
public class GatewayCardBinServiceImpl implements IGatewayCardBinService 
{
    @Autowired
    private GatewayCardBinMapper gatewayCardBinMapper;

    /**
     * 查询卡Bin
     * 
     * @param binId 卡BinID
     * @return 卡Bin
     */
    @Override
    public GatewayCardBin selectGatewayCardBinById(Long binId)
    {
        return gatewayCardBinMapper.selectGatewayCardBinById(binId);
    }

    /**
     * 查询卡Bin列表
     * 
     * @param gatewayCardBin 卡Bin
     * @return 卡Bin
     */
    @Override
    public List<GatewayCardBin> selectGatewayCardBinList(GatewayCardBin gatewayCardBin)
    {
        return gatewayCardBinMapper.selectGatewayCardBinList(gatewayCardBin);
    }

    /**
     * 新增卡Bin
     * 
     * @param gatewayCardBin 卡Bin
     * @return 结果
     */
    @Override
    public int insertGatewayCardBin(GatewayCardBin gatewayCardBin)
    {
        return gatewayCardBinMapper.insertGatewayCardBin(gatewayCardBin);
    }

    /**
     * 修改卡Bin
     * 
     * @param gatewayCardBin 卡Bin
     * @return 结果
     */
    @Override
    public int updateGatewayCardBin(GatewayCardBin gatewayCardBin)
    {
        return gatewayCardBinMapper.updateGatewayCardBin(gatewayCardBin);
    }

    /**
     * 删除卡Bin对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGatewayCardBinByIds(String ids)
    {
        return gatewayCardBinMapper.deleteGatewayCardBinByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除卡Bin信息
     * 
     * @param binId 卡BinID
     * @return 结果
     */
    @Override
    public int deleteGatewayCardBinById(Long binId)
    {
        return gatewayCardBinMapper.deleteGatewayCardBinById(binId);
    }
}
