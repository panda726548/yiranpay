package com.yiran.project.merchant.product.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.common.utils.DateUtils;
import com.yiran.project.merchant.product.domain.ProductMember;
import com.yiran.project.merchant.product.mapper.ProductMemberMapper;
import com.yiran.project.merchant.product.service.IProductMemberService;

/**
 * 产品商户关联Service业务层处理
 * 
 * @author panda
 * @date 2020-04-12
 */
@Service
public class ProductMemberServiceImpl implements IProductMemberService 
{
    @Autowired
    private ProductMemberMapper productMemberMapper;

	@Override
	public List<ProductMember> selectProductMemberList(ProductMember productMember) {
		return productMemberMapper.selectProductMemberList(productMember);
	}

   
}
