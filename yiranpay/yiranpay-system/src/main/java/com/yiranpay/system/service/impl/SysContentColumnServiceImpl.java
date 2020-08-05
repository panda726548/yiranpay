package com.yiranpay.system.service.impl;


import com.yiranpay.common.constant.UserConstants;
import com.yiranpay.common.core.text.Convert;
import com.yiranpay.common.utils.StrUtils;

import com.yiranpay.system.domain.SysContentColumn;
import com.yiranpay.system.mapper.SysContentColumnMapper;
import com.yiranpay.system.service.ISysContentColumnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网站系统栏目 服务层实现
 * 
 * @date 2018-07-26
 */
@Service
public class SysContentColumnServiceImpl implements ISysContentColumnService
{
	private static final Logger logger = LoggerFactory.getLogger(SysContentColumnServiceImpl.class);
	
	@Autowired
	private SysContentColumnMapper columnMapper;

	/**
     * 查询网站系统栏目信息
     * 
     * @param id 网站系统栏目ID
     * @return 网站系统栏目信息
     */
    @Override
	public SysContentColumn selectChannelById(Integer id)
	{
	    return columnMapper.selectChannelById(id);
	}
	
	/**
     * 查询网站系统栏目列表
     * 
     * @param channel 网站系统栏目信息
     * @return 网站系统栏目集合
     */
	@Override
	public List<SysContentColumn> selectChannelList(SysContentColumn channel)
	{

	    return columnMapper.selectChannelList(channel);
	}
	
    /**
     * 新增网站系统栏目
     * 
     * @param channel 网站系统栏目信息
     * @return 结果
     */
	@Override
	public int insertChannel(SysContentColumn channel)
	{
	    return columnMapper.insertChannel(channel);
	}
	
	/**
     * 修改网站系统栏目
     * 
     * @param channel 网站系统栏目信息
     * @return 结果
     */
	@Override
	public int updateChannel(SysContentColumn channel)
	{

	    return columnMapper.updateChannel(channel);
	}
	
	/**
     * 保存网站系统栏目
     * 
     * @param channel 网站系统栏目信息
     * @return 结果
     */
	@Override
	public int saveChannel(SysContentColumn channel)
	{
	    Integer id = channel.getId();
		int rows = 0;
		if (StrUtils.isNotNull(id))
        {

		    rows = columnMapper.updateChannel(channel);
		}
		else
        {

		    rows = columnMapper.insertChannel(channel);
		}
		return rows;
	}
	
	/**
     * 删除网站系统栏目对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteChannelByIds(String ids)
	{
		return columnMapper.deleteChannelByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<Map<String, Object>> selectChannelTree() {
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<SysContentColumn> channelList = columnMapper.selectChannelAll();

        for (SysContentColumn channel : channelList)
        {
            if (UserConstants.DEPT_NORMAL.equals(channel.getStatus().toString()))
            {
                Map<String, Object> channelMap = new HashMap<String, Object>();
                channelMap.put("id", channel.getId());
                channelMap.put("pId", channel.getPid());
                channelMap.put("name", channel.getName());
                channelMap.put("title", channel.getName());
                trees.add(channelMap);
            }
        }
		
		return trees;
	}

	@Override
	public String checkDeptNameUnique(SysContentColumn channel) {
		if (channel.getId() == null)
        {
			channel.setId(-1);
        }
        Integer id = channel.getId();
        SysContentColumn info = columnMapper.checkDeptNameUnique(channel.getName());
        if (StrUtils.isNotNull(info) && StrUtils.isNotNull(info.getId())
                && info.getId() != id)
        {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
	}

	@Override
	public int selectChannelCount(Integer parentId) {
		SysContentColumn channel =new SysContentColumn();
		channel.setPid(parentId);
		return columnMapper.selectChannelCount(channel);
	}

	@Override
	public int deleteChannelById(Integer id) {
		return columnMapper.deleteChannelById(id);
	}
	
}
