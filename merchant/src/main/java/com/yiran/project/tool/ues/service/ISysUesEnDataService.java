package com.yiran.project.tool.ues.service;

import java.util.List;

import com.yiran.project.tool.ues.domain.SysUesEnData;

/**
 * UES数据加密Service接口
 * 
 * @author panda
 * @date 2020-03-03
 */
public interface ISysUesEnDataService 
{
    /**
     * 查询UES数据加密
     * 
     * @param id UES数据加密ID
     * @return UES数据加密
     */
    public SysUesEnData selectSysUesEnDataById(Long id);

    /**
     * 查询UES数据加密列表
     * 
     * @param sysUesEnData UES数据加密
     * @return UES数据加密集合
     */
    public List<SysUesEnData> selectSysUesEnDataList(SysUesEnData sysUesEnData);

    /**
     * 新增UES数据加密
     * 
     * @param sysUesEnData UES数据加密
     * @return 结果
     */
    public int insertSysUesEnData(SysUesEnData sysUesEnData);

    /**
     * 修改UES数据加密
     * 
     * @param sysUesEnData UES数据加密
     * @return 结果
     */
    public int updateSysUesEnData(SysUesEnData sysUesEnData);

    /**
     * 批量删除UES数据加密
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUesEnDataByIds(String ids);

    /**
     * 删除UES数据加密信息
     * 
     * @param id UES数据加密ID
     * @return 结果
     */
    public int deleteSysUesEnDataById(Long id);

    /**
     * 根据票据获取对象
     * @param ticket
     * @return
     */
	public SysUesEnData selectUesEnDataByTicket(String ticket);

	/**
	 * 根据密码获取对象
	 * @param password
	 * @return
	 */
	public SysUesEnData selectSysUesEnDataByPassword(String password);
}
