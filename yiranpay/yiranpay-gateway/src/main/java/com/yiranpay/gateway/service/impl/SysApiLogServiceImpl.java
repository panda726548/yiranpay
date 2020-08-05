package com.yiranpay.gateway.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiranpay.common.core.text.Convert;
import com.yiranpay.gateway.domain.SysApiLog;
import com.yiranpay.gateway.mapper.SysApiLogMapper;
import com.yiranpay.gateway.service.ISysApiLogService;

/**
 * 网关API接口日志Service业务层处理
 * 
 * @author panda
 * @date 2020-04-23
 */
@Service
public class SysApiLogServiceImpl implements ISysApiLogService 
{
    @Autowired
    private SysApiLogMapper sysApiLogMapper;

    /**
     * 查询网关API接口日志
     * 
     * @param id 网关API接口日志ID
     * @return 网关API接口日志
     */
    @Override
    public SysApiLog selectSysApiLogById(Long id)
    {
        return sysApiLogMapper.selectSysApiLogById(id);
    }

    /**
     * 查询网关API接口日志列表
     * 
     * @param sysApiLog 网关API接口日志
     * @return 网关API接口日志
     */
    @Override
    public List<SysApiLog> selectSysApiLogList(SysApiLog sysApiLog)
    {
        return sysApiLogMapper.selectSysApiLogList(sysApiLog);
    }

    /**
     * 新增网关API接口日志
     * 
     * @param sysApiLog 网关API接口日志
     * @return 结果
     */
    @Override
    public int insertSysApiLog(SysApiLog sysApiLog)
    {
    	sysApiLog.setCreatTime(new Date());
        return sysApiLogMapper.insertSysApiLog(sysApiLog);
    }

    /**
     * 修改网关API接口日志
     * 
     * @param sysApiLog 网关API接口日志
     * @return 结果
     */
    @Override
    public int updateSysApiLog(SysApiLog sysApiLog)
    {
        return sysApiLogMapper.updateSysApiLog(sysApiLog);
    }

    /**
     * 删除网关API接口日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysApiLogByIds(String ids)
    {
        return sysApiLogMapper.deleteSysApiLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除网关API接口日志信息
     * 
     * @param id 网关API接口日志ID
     * @return 结果
     */
    @Override
    public int deleteSysApiLogById(Long id)
    {
        return sysApiLogMapper.deleteSysApiLogById(id);
    }
}
