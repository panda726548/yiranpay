package com.yiran.project.merchant.product.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.project.merchant.product.domain.ProductInfo;
import com.yiran.project.merchant.product.mapper.ProductInfoMapper;
import com.yiran.project.merchant.product.service.IProductInfoService;

/**
 * 产品Service业务层处理
 * 
 * @author panda
 * @date 2020-04-12
 */
@Service
public class ProductInfoServiceImpl implements IProductInfoService 
{
    @Autowired
    private ProductInfoMapper productInfoMapper;

	@Override
	public List<ProductInfo> selectProductInfoList(ProductInfo productInfo) {
		return productInfoMapper.selectProductInfoList(productInfo);
	}

	@Override
	public ProductInfo selectProductInfoByProductCode(String productCode) {
		return productInfoMapper.selectProductInfoByProductCode(productCode);
	}

    
}
