package com.yiranpay.gateway.service;

import java.util.List;

import com.yiranpay.gateway.domain.SysApiLog;

/**
 * 网关API接口日志Service接口
 * 
 * @author panda
 * @date 2020-04-23
 */
public interface ISysApiLogService 
{
    /**
     * 查询网关API接口日志
     * 
     * @param id 网关API接口日志ID
     * @return 网关API接口日志
     */
    public SysApiLog selectSysApiLogById(Long id);

    /**
     * 查询网关API接口日志列表
     * 
     * @param sysApiLog 网关API接口日志
     * @return 网关API接口日志集合
     */
    public List<SysApiLog> selectSysApiLogList(SysApiLog sysApiLog);

    /**
     * 新增网关API接口日志
     * 
     * @param sysApiLog 网关API接口日志
     * @return 结果
     */
    public int insertSysApiLog(SysApiLog sysApiLog);

    /**
     * 修改网关API接口日志
     * 
     * @param sysApiLog 网关API接口日志
     * @return 结果
     */
    public int updateSysApiLog(SysApiLog sysApiLog);

    /**
     * 批量删除网关API接口日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysApiLogByIds(String ids);

    /**
     * 删除网关API接口日志信息
     * 
     * @param id 网关API接口日志ID
     * @return 结果
     */
    public int deleteSysApiLogById(Long id);
}
