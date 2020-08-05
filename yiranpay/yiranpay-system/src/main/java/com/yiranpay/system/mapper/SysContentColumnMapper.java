package com.yiranpay.system.mapper;



import com.yiranpay.system.domain.SysContentColumn;

import java.util.List;

/**
 * 网站系统栏目 数据层
 * 
 * @author yiran
 * @date 2018-07-26
 */
public interface SysContentColumnMapper
{
	/**
     * 查询网站系统栏目信息
     * 
     * @param id 网站系统栏目ID
     * @return 网站系统栏目信息
     */
	public SysContentColumn selectChannelById(Integer id);
	
	/**
     * 查询网站系统栏目列表
     * 
     * @param channel 网站系统栏目信息
     * @return 网站系统栏目集合
     */
	public List<SysContentColumn> selectChannelList(SysContentColumn channel);
	
	/**
     * 新增网站系统栏目
     * 
     * @param channel 网站系统栏目信息
     * @return 结果
     */
	public int insertChannel(SysContentColumn channel);
	
	/**
     * 修改网站系统栏目
     * 
     * @param channel 网站系统栏目信息
     * @return 结果
     */
	public int updateChannel(SysContentColumn channel);
	
	/**
     * 删除网站系统栏目
     * 
     * @param id 网站系统栏目ID
     * @return 结果
     */
	public int deleteChannelById(Integer id);
	
	/**
     * 批量删除网站系统栏目
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteChannelByIds(String[] ids);

	/**
	 * 查询所有的栏目信息
	 * @return
	 */
	public List<SysContentColumn> selectChannelAll();

	public SysContentColumn checkDeptNameUnique(String name);

	public int selectChannelCount(SysContentColumn channel);
	
}