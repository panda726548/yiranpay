package com.yiranpay.system.service.impl;


import com.yiranpay.common.core.text.Convert;
import com.yiranpay.common.utils.StrUtils;

import com.yiranpay.system.domain.SysContentTopic;
import com.yiranpay.system.domain.SysUser;
import com.yiranpay.system.mapper.SysContentTopicMapper;
import com.yiranpay.system.service.ISysContentTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章管理 服务层实现
 * 
 * @author yiran
 * @date 2018-07-26
 */
@Service
public class SysContentTopicServiceImpl implements ISysContentTopicService
{
	@Autowired
	private SysContentTopicMapper topicMapper;

	/**
     * 查询文章管理信息
     * 
     * @param id 文章管理ID
     * @return 文章管理信息
     */
    @Override
	public SysContentTopic selectTopicById(Integer id)
	{
	    return topicMapper.selectTopicById(id);
	}
	
	/**
     * 查询文章管理列表
     * 
     * @param topic 文章管理信息
     * @return 文章管理集合
     */
	@Override
	public List<SysContentTopic> selectTopicList(SysContentTopic topic)
	{
	    return topicMapper.selectTopicList(topic);
	}
	
    /**
     * 新增文章管理
     * 
     * @param topic 文章管理信息
     * @return 结果
     */
	@Override
	public int insertTopic(SysContentTopic topic)
	{
	    return topicMapper.insertTopic(topic);
	}
	
	/**
     * 修改文章管理
     * 
     * @param topic 文章管理信息
     * @return 结果
     */
	@Override
	public int updateTopic(SysContentTopic topic)
	{
	    return topicMapper.updateTopic(topic);
	}
	
	/**
     * 保存文章管理
     * 
     * @param topic 文章管理信息
     * @return 结果
     */
	@Override
	public int saveTopic(SysContentTopic topic)
	{
	    Integer id = topic.getId();
		int rows = 0;
		if (StrUtils.isNotNull(id))
        {
		    rows = topicMapper.updateTopic(topic);
		}
		else
        {
		    rows = topicMapper.insertTopic(topic);
		}
		return rows;
	}
	
	/**
     * 删除文章管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTopicByIds(String ids)
	{
		return topicMapper.deleteTopicByIds(Convert.toStrArray(ids));
	}

	@Override
	public int deleteTopicById(Integer id) {
		// TODO Auto-generated method stub
		return topicMapper.deleteTopicById(id);
	}
	
}
