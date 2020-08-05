package com.yiranpay.system.service;



import com.yiranpay.system.domain.SysContentColumn;

import java.util.List;
import java.util.Map;

/**
 * 网站系统栏目 服务层
 * 
 * @author yiran
 * @date 2018-07-26
 */
public interface ISysContentColumnService
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
     * @param column 网站系统栏目信息
     * @return 网站系统栏目集合
     */
	public List<SysContentColumn> selectChannelList(SysContentColumn column);
	
	/**
     * 新增网站系统栏目
     * 
     * @param column 网站系统栏目信息
     * @return 结果
     */
	public int insertChannel(SysContentColumn column);
	
	/**
     * 修改网站系统栏目
     * 
     * @param column 网站系统栏目信息
     * @return 结果
     */
	public int updateChannel(SysContentColumn column);
	
	/**
     * 保存网站系统栏目
     * 
     * @param column 网站系统栏目信息
     * @return 结果
     */
	public int saveChannel(SysContentColumn column);
	
	
	/**
     * 删除网站系统栏目信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteChannelByIds(String ids);
	/**
	 * 删除栏目
	 * @param id
	 * @return
	 */
	public int deleteChannelById(Integer id);

	/**
	 * 查询栏目管理树
	 * @return 返回所有的栏目
	 */
	public List<Map<String, Object>> selectChannelTree();

	public String checkDeptNameUnique(SysContentColumn column);
	
	/**
     * 查询栏目人数
     * 
     * @param parentId 父部门ID
     * @return 结果
     */
    public int selectChannelCount(Integer parentId);
	
}
