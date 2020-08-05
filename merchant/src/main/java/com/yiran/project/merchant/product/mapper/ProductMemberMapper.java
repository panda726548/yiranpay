package com.yiran.project.merchant.product.mapper;

import java.util.List;

import com.yiran.project.merchant.product.domain.ProductMember;

/**
 * 产品商户关联Mapper接口
 * 
 * @author panda
 * @date 2020-04-12
 */
public interface ProductMemberMapper 
{

    /**
     * 查询产品商户关联列表
     * 
     * @param gatewayProductMember 产品商户关联
     * @return 产品商户关联集合
     */
    public List<ProductMember> selectProductMemberList(ProductMember productMember);

    
}
