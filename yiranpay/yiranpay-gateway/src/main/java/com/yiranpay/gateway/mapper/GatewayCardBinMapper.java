package com.yiranpay.gateway.mapper;

import com.yiranpay.gateway.domain.GatewayCardBin;
import java.util.List;

/**
 * 卡BinMapper接口
 * 
 * @author panda
 * @date 2020-01-06
 */
public interface GatewayCardBinMapper 
{
    /**
     * 查询卡Bin
     * 
     * @param binId 卡BinID
     * @return 卡Bin
     */
    public GatewayCardBin selectGatewayCardBinById(Long binId);

    /**
     * 查询卡Bin列表
     * 
     * @param gatewayCardBin 卡Bin
     * @return 卡Bin集合
     */
    public List<GatewayCardBin> selectGatewayCardBinList(GatewayCardBin gatewayCardBin);

    /**
     * 新增卡Bin
     * 
     * @param gatewayCardBin 卡Bin
     * @return 结果
     */
    public int insertGatewayCardBin(GatewayCardBin gatewayCardBin);

    /**
     * 修改卡Bin
     * 
     * @param gatewayCardBin 卡Bin
     * @return 结果
     */
    public int updateGatewayCardBin(GatewayCardBin gatewayCardBin);

    /**
     * 删除卡Bin
     * 
     * @param binId 卡BinID
     * @return 结果
     */
    public int deleteGatewayCardBinById(Long binId);

    /**
     * 批量删除卡Bin
     * 
     * @param binIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteGatewayCardBinByIds(String[] binIds);
}
