package com.yiran.project.merchant.product.service;

import java.util.List;

import com.yiran.project.merchant.product.domain.ProductInfo;

/**
 * 产品Service接口
 * 
 * @author panda
 * @date 2020-04-12
 */
public interface IProductInfoService 
{
	/**
     * 查询产品列表
     * 
     * @param ProductInfo 产品
     * @return 产品集合
     */
    public List<ProductInfo> selectProductInfoList(ProductInfo productInfo);

    /**
     * 根据产品编号获取产品信息
     * @param productCode
     * @return
     */
	public ProductInfo selectProductInfoByProductCode(String productCode);
}
