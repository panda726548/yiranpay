package com.yiran.project.merchant.product.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.common.utils.DateUtils;
import com.yiran.common.utils.text.Convert;
import com.yiran.project.merchant.product.domain.ProductApproval;
import com.yiran.project.merchant.product.mapper.ProductApprovalMapper;
import com.yiran.project.merchant.product.service.IProductApprovalService;

/**
 * 产品申请Service业务层处理
 * 
 * @author panda
 * @date 2020-04-12
 */
@Service
public class ProductApprovalServiceImpl implements IProductApprovalService 
{
	@Autowired
	private ProductApprovalMapper productApprovalMapper;

	@Override
	public ProductApproval selectProductApprova(String productCode, String memberNo) {
		return productApprovalMapper.selectProductApprova(productCode, memberNo);
	}

	@Override
	public int insertProductApproval(ProductApproval approval) {
		approval.setCreateTime(DateUtils.getNowDate());
		return productApprovalMapper.insertProductApproval(approval);
	}
    
}
