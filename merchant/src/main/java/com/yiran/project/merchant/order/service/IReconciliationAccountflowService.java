package com.yiran.project.merchant.order.service;

import java.util.List;

import com.yiran.project.merchant.order.domain.ReconciliationAccountflow;

/**
 * 入账流水Service接口
 * 
 * @author panda
 * @date 2020-04-25
 */
public interface IReconciliationAccountflowService 
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
     * 批量删除入账流水
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteReconciliationAccountflowByIds(String ids);

    /**
     * 删除入账流水信息
     * 
     * @param glideId 入账流水ID
     * @return 结果
     */
    public int deleteReconciliationAccountflowById(Long glideId);

    /**
     * 根据机构订单号查询入账流水
     * @param instOrderNo
     * @return
     */
	public ReconciliationAccountflow selectReconciliationAccountflowByBizNo(String instOrderNo);

	/**
	 * 更新入账流水
	 * @param bizNo
	 * @param compareFlag
	 * @param compareBatchNo
	 * @param billDate
	 */
	public int updateflow(String bizNo, String compareFlag, String compareBatchNo, String billDate);

	public List<ReconciliationAccountflow> selectAccountflowLists(String compareDate, String compareFlag,
			String[] fundCodes,String memberId);
}
