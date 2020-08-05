package com.yiranpay.reconciliation.mapper;

import com.yiranpay.reconciliation.domain.ReconciliationAccountflow;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 入账流水Mapper接口
 * 
 * @author panda
 * @date 2020-04-25
 */
public interface ReconciliationAccountflowMapper 
{
    /**
     * 查询入账流水
     * 
     * @param glideId 入账流水ID
     * @return 入账流水
     */
    public ReconciliationAccountflow selectReconciliationAccountflowById(Long glideId);

    /**
     * 查询入账流水列表
     * 
     * @param reconciliationAccountflow 入账流水
     * @return 入账流水集合
     */
    public List<ReconciliationAccountflow> selectReconciliationAccountflowList(ReconciliationAccountflow reconciliationAccountflow);

    /**
     * 新增入账流水
     * 
     * @param reconciliationAccountflow 入账流水
     * @return 结果
     */
    public int insertReconciliationAccountflow(ReconciliationAccountflow reconciliationAccountflow);

    /**
     * 修改入账流水
     * 
     * @param reconciliationAccountflow 入账流水
     * @return 结果
     */
    public int updateReconciliationAccountflow(ReconciliationAccountflow reconciliationAccountflow);

    /**
     * 删除入账流水
     * 
     * @param glideId 入账流水ID
     * @return 结果
     */
    public int deleteReconciliationAccountflowById(Long glideId);

    /**
     * 批量删除入账流水
     * 
     * @param glideIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteReconciliationAccountflowByIds(String[] glideIds);

    
	public ReconciliationAccountflow selectReconciliationAccountflowByBizNo(String bizNo);

	public int updateflow(
			@Param("bizNo")String bizNo, 
			@Param("compareFlag")String compareFlag, 
			@Param("compareBatchNo")String compareBatchNo, 
			@Param("compareDate")String compareDate,
			@Param("isWriteoff")String isWriteoff);
}
