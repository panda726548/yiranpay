package com.yiran.project.merchant.product.service;

import java.util.List;

import com.yiran.project.merchant.product.domain.ProductApproval;

/**
 * 产品申请Service接口
 * 
 * @author panda
 * @date 2020-04-12
 */
public interface IProductApprovalService 
{

	ProductApproval selectProductApprova(String productCode, String memberNo);

	int insertProductApproval(ProductApproval approval);
   
}
