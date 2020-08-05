package com.yiranpay.system.mapper;

import com.yiranpay.system.domain.SysContentTopic;

import java.util.List;

/**
 * 文章管理 数据层
 * 
 * @author yiran
 * @date 2018-07-26
 */
public interface SysContentTopicMapper
{
	/**
     * 查询文章管理信息
     * 
     * @param id 文章管理ID
     * @return 文章管理信息
     */
	public SysContentTopic selectTopicById(Integer id);
	
	/**
     * 查询文章管理列表
     * 
     * @param topic 文章管理信息
     * @return 文章管理集合
     */
	public List<SysContentTopic> selectTopicList(SysContentTopic topic);
	
	/**
     * 新增文章管理
     * 
     * @param topic 文章管理信息
     * @return 结果
     */
	public int insertTopic(SysContentTopic topic);
	
	/**
     * 修改文章管理
     * 
     * @param topic 文章管理信息
     * @return 结果
     */
	public int updateTopic(SysContentTopic topic);
	
	/**
     * 删除文章管理
     * 
     * @param id 文章管理ID
     * @return 结果
     */
	public int deleteTopicById(Integer id);
	
	/**
     * 批量删除文章管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTopicByIds(String[] ids);
	
}