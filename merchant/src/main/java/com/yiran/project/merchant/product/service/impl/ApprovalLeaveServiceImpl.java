package com.yiran.project.merchant.product.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.common.utils.DateUtils;
import com.yiran.project.merchant.product.domain.ApprovalLeave;
import com.yiran.project.merchant.product.mapper.ApprovalLeaveMapper;
import com.yiran.project.merchant.product.service.IApprovalLeaveService;

/**
 * 产品审批明细Service业务层处理
 * 
 * @author panda
 * @date 2020-04-12
 */
@Service
public class ApprovalLeaveServiceImpl implements IApprovalLeaveService 
{
    @Autowired
    private ApprovalLeaveMapper approvalLeaveMapper;

	@Override
	public int insertApprovalLeave(ApprovalLeave approvalLeave) {
		approvalLeave.setCreateTime(DateUtils.getNowDate());
		return approvalLeaveMapper.insertApprovalLeave(approvalLeave);
	}

	@Override
	public List<ApprovalLeave> selectApprovalLeaveList(ApprovalLeave approvalLeave) {
		return approvalLeaveMapper.selectApprovalLeaveList(approvalLeave);
	}

    
}
