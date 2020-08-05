package com.yiranpay.gateway.mapper;

import com.yiranpay.gateway.domain.GatewayGatewayApi;
import com.yiranpay.gateway.domain.GatewayProductInfo;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 产品Mapper接口
 * 
 * @author panda
 * @date 2020-04-12
 */
public interface GatewayProductInfoMapper 
{
    /**
     * 查询产品
     * 
     * @param productId 产品ID
     * @return 产品
     */
    public GatewayProductInfo selectGatewayProductInfoById(Long productId);

    /**
     * 查询产品列表
     * 
     * @param gatewayProductInfo 产品
     * @return 产品集合
     */
    public List<GatewayProductInfo> selectGatewayProductInfoList(GatewayProductInfo gatewayProductInfo);

    /**
     * 新增产品
     * 
     * @param gatewayProductInfo 产品
     * @return 结果
     */
    public int insertGatewayProductInfo(GatewayProductInfo gatewayProductInfo);

    /**
     * 修改产品
     * 
     * @param gatewayProductInfo 产品
     * @return 结果
     */
    public int updateGatewayProductInfo(GatewayProductInfo gatewayProductInfo);

    /**
     * 删除产品
     * 
     * @param productId 产品ID
     * @return 结果
     */
    public int deleteGatewayProductInfoById(Long productId);

    /**
     * 批量删除产品
     * 
     * @param productIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteGatewayProductInfoByIds(String[] productIds);

	public GatewayProductInfo checkProductNameUnique(@Param("productName")String productName, @Param("parentId")Long parentId);

	public List<GatewayProductInfo> selectProductMenuAll();

	public int selectCountProductByParentId(Long productId);
}
