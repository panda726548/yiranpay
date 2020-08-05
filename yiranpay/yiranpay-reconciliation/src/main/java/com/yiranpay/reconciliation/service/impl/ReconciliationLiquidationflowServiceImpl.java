package com.yiranpay.reconciliation.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiranpay.reconciliation.mapper.ReconciliationLiquidationflowMapper;
import com.yiranpay.reconciliation.domain.ReconciliationLiquidationflow;
import com.yiranpay.reconciliation.service.IReconciliationLiquidationflowService;
import com.yiranpay.common.core.text.Convert;

/**
 * 清算流水Service业务层处理
 * 
 * @author panda
 * @date 2020-04-25
 */
@Service
public class ReconciliationLiquidationflowServiceImpl implements IReconciliationLiquidationflowService 
{
    @Autowired
    private ReconciliationLiquidationflowMapper reconciliationLiquidationflowMapper;

    /**
     * 查询清算流水
     * 
     * @param id 清算流水ID
     * @return 清算流水
     */
    @Override
    public ReconciliationLiquidationflow selectReconciliationLiquidationflowById(Long id)
    {
        return reconciliationLiquidationflowMapper.selectReconciliationLiquidationflowById(id);
    }

    /**
     * 查询清算流水列表
     * 
     * @param reconciliationLiquidationflow 清算流水
     * @return 清算流水
     */
    @Override
    public List<ReconciliationLiquidationflow> selectReconciliationLiquidationflowList(ReconciliationLiquidationflow reconciliationLiquidationflow)
    {
        return reconciliationLiquidationflowMapper.selectReconciliationLiquidationflowList(reconciliationLiquidationflow);
    }

    /**
     * 新增清算流水
     * 
     * @param reconciliationLiquidationflow 清算流水
     * @return 结果
     */
    @Override
    public int insertReconciliationLiquidationflow(ReconciliationLiquidationflow reconciliationLiquidationflow)
    {
        return reconciliationLiquidationflowMapper.insertReconciliationLiquidationflow(reconciliationLiquidationflow);
    }

    /**
     * 修改清算流水
     * 
     * @param reconciliationLiquidationflow 清算流水
     * @return 结果
     */
    @Override
    public int updateReconciliationLiquidationflow(ReconciliationLiquidationflow reconciliationLiquidationflow)
    {
        return reconciliationLiquidationflowMapper.updateReconciliationLiquidationflow(reconciliationLiquidationflow);
    }

    /**
     * 删除清算流水对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteReconciliationLiquidationflowByIds(String ids)
    {
        return reconciliationLiquidationflowMapper.deleteReconciliationLiquidationflowByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除清算流水信息
     * 
     * @param id 清算流水ID
     * @return 结果
     */
    @Override
    public int deleteReconciliationLiquidationflowById(Long id)
    {
        return reconciliationLiquidationflowMapper.deleteReconciliationLiquidationflowById(id);
    }
}
