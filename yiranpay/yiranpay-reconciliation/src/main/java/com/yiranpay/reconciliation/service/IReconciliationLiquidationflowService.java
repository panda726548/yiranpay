package com.yiranpay.reconciliation.service;

import com.yiranpay.reconciliation.domain.ReconciliationLiquidationflow;
import java.util.List;

/**
 * 清算流水Service接口
 * 
 * @author panda
 * @date 2020-04-25
 */
public interface IReconciliationLiquidationflowService 
{
    /**
     * 查询清算流水
     * 
     * @param id 清算流水ID
     * @return 清算流水
     */
    public ReconciliationLiquidationflow selectReconciliationLiquidationflowById(Long id);

    /**
     * 查询清算流水列表
     * 
     * @param reconciliationLiquidationflow 清算流水
     * @return 清算流水集合
     */
    public List<ReconciliationLiquidationflow> selectReconciliationLiquidationflowList(ReconciliationLiquidationflow reconciliationLiquidationflow);

    /**
     * 新增清算流水
     * 
     * @param reconciliationLiquidationflow 清算流水
     * @return 结果
     */
    public int insertReconciliationLiquidationflow(ReconciliationLiquidationflow reconciliationLiquidationflow);

    /**
     * 修改清算流水
     * 
     * @param reconciliationLiquidationflow 清算流水
     * @return 结果
     */
    public int updateReconciliationLiquidationflow(ReconciliationLiquidationflow reconciliationLiquidationflow);

    /**
     * 批量删除清算流水
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteReconciliationLiquidationflowByIds(String ids);

    /**
     * 删除清算流水信息
     * 
     * @param id 清算流水ID
     * @return 结果
     */
    public int deleteReconciliationLiquidationflowById(Long id);
}
