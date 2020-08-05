package com.yiran.project.merchant.order.service;

import java.util.List;

import com.yiran.project.merchant.order.domain.ReconciliationEvent;

/**
 * 对账下载URLService接口
 * 
 * @author panda
 * @date 2020-04-30
 */
public interface IReconciliationEventService 
{
    /**
     * 查询对账下载URL
     * 
     * @param id 对账下载URLID
     * @return 对账下载URL
     */
    public ReconciliationEvent selectReconciliationEventById(Long id);

    /**
     * 查询对账下载URL列表
     * 
     * @param reconciliationEvent 对账下载URL
     * @return 对账下载URL集合
     */
    public List<ReconciliationEvent> selectReconciliationEventList(ReconciliationEvent reconciliationEvent);

    /**
     * 新增对账下载URL
     * 
     * @param reconciliationEvent 对账下载URL
     * @return 结果
     */
    public int insertReconciliationEvent(ReconciliationEvent reconciliationEvent);

    /**
     * 修改对账下载URL
     * 
     * @param reconciliationEvent 对账下载URL
     * @return 结果
     */
    public int updateReconciliationEvent(ReconciliationEvent reconciliationEvent);

    /**
     * 批量删除对账下载URL
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteReconciliationEventByIds(String ids);

    /**
     * 删除对账下载URL信息
     * 
     * @param id 对账下载URLID
     * @return 结果
     */
    public int deleteReconciliationEventById(Long id);

    /**
     * 获取当月所有的记录
     * @param event
     * @return
     */
	public List<ReconciliationEvent> selectReconciliationEventListByMoth(ReconciliationEvent event);
}
