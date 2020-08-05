package com.yiran.project.merchant.product.service;

import java.util.List;

import com.yiran.project.merchant.product.domain.ProductMember;

/**
 * 产品商户关联Service接口
 * 
 * @author panda
 * @date 2020-04-12
 */
public interface IProductMemberService 
{
	public List<ProductMember> selectProductMemberList(ProductMember productMember);

}
