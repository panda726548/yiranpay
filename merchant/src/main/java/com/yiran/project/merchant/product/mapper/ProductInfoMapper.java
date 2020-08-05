package com.yiran.project.merchant.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yiran.project.merchant.product.domain.ProductInfo;

/**
 * 产品Mapper接口
 * 
 * @author panda
 * @date 2020-04-12
 */
public interface ProductInfoMapper 
{
    /**
     * 查询产品
     * 
     * @param productId 产品ID
     * @return 产品
     */
    public ProductInfo selectGatewayProductInfoById(Long productId);

    /**
     * 查询产品列表
     * 
     * @param gatewayProductInfo 产品
     * @return 产品集合
     */
    public List<ProductInfo> selectProductInfoList(ProductInfo gatewayProductInfo);

    /**
     * 新增产品
     * 
     * @param gatewayProductInfo 产品
     * @return 结果
     */
    public int insertGatewayProductInfo(ProductInfo gatewayProductInfo);

    /**
     * 修改产品
     * 
     * @param gatewayProductInfo 产品
     * @return 结果
     */
    public int updateGatewayProductInfo(ProductInfo gatewayProductInfo);

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

	public ProductInfo checkProductNameUnique(@Param("productName")String productName, @Param("parentId")Long parentId);

	public List<ProductInfo> selectProductMenuAll();

	public int selectCountProductByParentId(Long productId);

	public ProductInfo selectProductInfoByProductCode(String productCode);
}
