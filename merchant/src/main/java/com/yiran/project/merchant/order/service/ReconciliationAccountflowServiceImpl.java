package com.yiran.project.merchant.order.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.common.utils.text.Convert;
import com.yiran.project.merchant.order.domain.ReconciliationAccountflow;
import com.yiran.project.merchant.order.mapper.ReconciliationAccountflowMapper;

/**
 * 入账流水Service业务层处理
 * 
 * @author panda
 * @date 2020-04-25
 */
@Service
public class ReconciliationAccountflowServiceImpl implements IReconciliationAccountflowService 
{
    @Autowired
    private ReconciliationAccountflowMapper reconciliationAccountflowMapper;

    /**
     * 查询入账流水
     * 
     * @param glideId 入账流水ID
     * @return 入账流水
     */
    @Override
    public ReconciliationAccountflow selectReconciliationAccountflowById(Long glideId)
    {
        return reconciliationAccountflowMapper.selectReconciliationAccountflowById(glideId);
    }

    /**
     * 查询入账流水列表
     * 
     * @param reconciliationAccountflow 入账流水
     * @return 入账流水
     */
    @Override
    public List<ReconciliationAccountflow> selectReconciliationAccountflowList(ReconciliationAccountflow reconciliationAccountflow)
    {
        return reconciliationAccountflowMapper.selectReconciliationAccountflowList(reconciliationAccountflow);
    }

    /**
     * 新增入账流水
     * 
     * @param reconciliationAccountflow 入账流水
     * @return 结果
     */
    @Override
    public int insertReconciliationAccountflow(ReconciliationAccountflow reconciliationAccountflow)
    {
    	reconciliationAccountflow.setGmtCreate(new Date());
    	reconciliationAccountflow.setGmtModified(new Date());
        return reconciliationAccountflowMapper.insertReconciliationAccountflow(reconciliationAccountflow);
    }

    /**
     * 修改入账流水
     * 
     * @param reconciliationAccountflow 入账流水
     * @return 结果
     */
    @Override
    public int updateReconciliationAccountflow(ReconciliationAccountflow reconciliationAccountflow)
    {
    	reconciliationAccountflow.setGmtModified(new Date());
        return reconciliationAccountflowMapper.updateReconciliationAccountflow(reconciliationAccountflow);
    }

    /**
     * 删除入账流水对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteReconciliationAccountflowByIds(String ids)
    {
        return reconciliationAccountflowMapper.deleteReconciliationAccountflowByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除入账流水信息
     * 
     * @param glideId 入账流水ID
     * @return 结果
     */
    @Override
    public int deleteReconciliationAccountflowById(Long glideId)
    {
        return reconciliationAccountflowMapper.deleteReconciliationAccountflowById(glideId);
    }

	@Override
	public ReconciliationAccountflow selectReconciliationAccountflowByBizNo(String bizNo) {
		
		return reconciliationAccountflowMapper.selectReconciliationAccountflowByBizNo(bizNo);
	}

	@Override
	public int updateflow(String bizNo, String compareFlag, String compareBatchNo, String billDate) {
		
		return reconciliationAccountflowMapper.updateflow(bizNo, compareFlag, compareBatchNo, billDate);
	}

	@Override
	public List<ReconciliationAccountflow> selectAccountflowLists(String compareDate, String compareFlag,
			String[] fundCodes,String memberId) {
		return reconciliationAccountflowMapper.selectAccountflowLists(compareDate, compareFlag,fundCodes,memberId);
	}
}
