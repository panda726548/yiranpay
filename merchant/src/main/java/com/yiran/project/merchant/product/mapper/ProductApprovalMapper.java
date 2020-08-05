package com.yiran.project.merchant.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yiran.project.merchant.product.domain.ProductApproval;

/**
 * 产品申请Mapper接口
 * 
 * @author panda
 * @date 2020-04-12
 */
public interface ProductApprovalMapper 
{
    
    /**
     * 新增产品申请
     * 
     * @param gatewayProductApproval 产品申请
     * @return 结果
     */
    public int insertProductApproval(ProductApproval productApproval);

    

	public ProductApproval selectProductApprova(@Param("productCode")String productCode, 
			@Param("memberNo")String memberNo);
}
