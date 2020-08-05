package com.yiranpay.system.service;


import com.yiranpay.system.domain.SysContentTopic;

import java.util.List;

/**
 * 文章管理 服务层
 * 
 * @author yiran
 * @date 2018-07-26
 */
public interface ISysContentTopicService
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
     * 保存文章管理
     * 
     * @param topic 文章管理信息
     * @return 结果
     */
	public int saveTopic(SysContentTopic topic);
	
	
	/**
     * 删除文章管理信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTopicByIds(String ids);

	public int deleteTopicById(Integer id);
	
}
