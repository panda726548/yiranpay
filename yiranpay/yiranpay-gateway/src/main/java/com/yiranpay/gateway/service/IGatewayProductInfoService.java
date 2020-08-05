package com.yiranpay.gateway.service;

import com.yiranpay.common.core.domain.Ztree;
import com.yiranpay.gateway.domain.GatewayGatewayApi;
import com.yiranpay.gateway.domain.GatewayProductInfo;
import java.util.List;

/**
 * 产品Service接口
 * 
 * @author panda
 * @date 2020-04-12
 */
public interface IGatewayProductInfoService 
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
     * 批量删除产品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGatewayProductInfoByIds(String ids);

    /**
     * 删除产品信息
     * 
     * @param productId 产品ID
     * @return 结果
     */
    public int deleteGatewayProductInfoById(Long productId);

    /**
     * 检查产品名是否存在
     * @param gatewayGatewayApi
     * @return
     */
	public String checkProductNameUnique(GatewayProductInfo gatewayProductInfo);

	public GatewayProductInfo selectProductMenuById(Long productId);

	public List<Ztree> productMenuTreeData();

	public GatewayProductInfo selectGatewayGatewayProductById(Long parentId);

	public int selectCountProductByParentId(Long productId);
}
