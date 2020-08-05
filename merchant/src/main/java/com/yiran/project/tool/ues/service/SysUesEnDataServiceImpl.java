package com.yiran.project.tool.ues.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.common.utils.DateUtils;
import com.yiran.common.utils.text.Convert;
import com.yiran.project.tool.ues.domain.SysUesEnData;
import com.yiran.project.tool.ues.mapper.SysUesEnDataMapper;

/**
 * UES数据加密Service业务层处理
 * 
 * @author panda
 * @date 2020-03-03
 */
@Service
public class SysUesEnDataServiceImpl implements ISysUesEnDataService 
{
    @Autowired
    private SysUesEnDataMapper sysUesEnDataMapper;

    /**
     * 查询UES数据加密
     * 
     * @param id UES数据加密ID
     * @return UES数据加密
     */
    @Override
    public SysUesEnData selectSysUesEnDataById(Long id)
    {
        return sysUesEnDataMapper.selectSysUesEnDataById(id);
    }

    /**
     * 查询UES数据加密列表
     * 
     * @param sysUesEnData UES数据加密
     * @return UES数据加密
     */
    @Override
    public List<SysUesEnData> selectSysUesEnDataList(SysUesEnData sysUesEnData)
    {
        return sysUesEnDataMapper.selectSysUesEnDataList(sysUesEnData);
    }

    /**
     * 新增UES数据加密
     * 
     * @param sysUesEnData UES数据加密
     * @return 结果
     */
    @Override
    public int insertSysUesEnData(SysUesEnData sysUesEnData)
    {
        sysUesEnData.setCreateTime(DateUtils.getNowDate());
        return sysUesEnDataMapper.insertSysUesEnData(sysUesEnData);
    }

    /**
     * 修改UES数据加密
     * 
     * @param sysUesEnData UES数据加密
     * @return 结果
     */
    @Override
    public int updateSysUesEnData(SysUesEnData sysUesEnData)
    {
        sysUesEnData.setUpdateTime(DateUtils.getNowDate());
        return sysUesEnDataMapper.updateSysUesEnData(sysUesEnData);
    }

    /**
     * 删除UES数据加密对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysUesEnDataByIds(String ids)
    {
        return sysUesEnDataMapper.deleteSysUesEnDataByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除UES数据加密信息
     * 
     * @param id UES数据加密ID
     * @return 结果
     */
    @Override
    public int deleteSysUesEnDataById(Long id)
    {
        return sysUesEnDataMapper.deleteSysUesEnDataById(id);
    }

	@Override
	public SysUesEnData selectUesEnDataByTicket(String ticket) {
		return sysUesEnDataMapper.selectUesEnDataByTicket(ticket);
	}

	@Override
	public SysUesEnData selectSysUesEnDataByPassword(String password) {
		return sysUesEnDataMapper.selectSysUesEnDataByPassword(password);
	}
}
