package com.yiran.project.merchant.order.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.common.utils.DateUtils;
import com.yiran.common.utils.text.Convert;
import com.yiran.project.merchant.order.domain.ReconciliationEvent;
import com.yiran.project.merchant.order.mapper.ReconciliationEventMapper;

/**
 * 对账下载URLService业务层处理
 * 
 * @author panda
 * @date 2020-04-30
 */
@Service
public class ReconciliationEventServiceImpl implements IReconciliationEventService 
{
    @Autowired
    private ReconciliationEventMapper reconciliationEventMapper;

    /**
     * 查询对账下载URL
     * 
     * @param id 对账下载URLID
     * @return 对账下载URL
     */
    @Override
    public ReconciliationEvent selectReconciliationEventById(Long id)
    {
        return reconciliationEventMapper.selectReconciliationEventById(id);
    }

    /**
     * 查询对账下载URL列表
     * 
     * @param reconciliationEvent 对账下载URL
     * @return 对账下载URL
     */
    @Override
    public List<ReconciliationEvent> selectReconciliationEventList(ReconciliationEvent reconciliationEvent)
    {
        return reconciliationEventMapper.selectReconciliationEventList(reconciliationEvent);
    }

    /**
     * 新增对账下载URL
     * 
     * @param reconciliationEvent 对账下载URL
     * @return 结果
     */
    @Override
    public int insertReconciliationEvent(ReconciliationEvent reconciliationEvent)
    {
        reconciliationEvent.setCreateTime(DateUtils.getNowDate());
        reconciliationEvent.setUpdateTime(DateUtils.getNowDate());
        return reconciliationEventMapper.insertReconciliationEvent(reconciliationEvent);
    }

    /**
     * 修改对账下载URL
     * 
     * @param reconciliationEvent 对账下载URL
     * @return 结果
     */
    @Override
    public int updateReconciliationEvent(ReconciliationEvent reconciliationEvent)
    {
        reconciliationEvent.setUpdateTime(DateUtils.getNowDate());
        return reconciliationEventMapper.updateReconciliationEvent(reconciliationEvent);
    }

    /**
     * 删除对账下载URL对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteReconciliationEventByIds(String ids)
    {
        return reconciliationEventMapper.deleteReconciliationEventByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除对账下载URL信息
     * 
     * @param id 对账下载URLID
     * @return 结果
     */
    @Override
    public int deleteReconciliationEventById(Long id)
    {
        return reconciliationEventMapper.deleteReconciliationEventById(id);
    }

	@Override
	public List<ReconciliationEvent> selectReconciliationEventListByMoth(ReconciliationEvent event) {
		return reconciliationEventMapper.selectReconciliationEventListByMoth(event);
	}
}
