package com.yiranpay.message.service.impl;

import com.yiranpay.common.core.text.Convert;
import com.yiranpay.common.utils.DateUtils;
import com.yiranpay.message.domain.MsgSmsConfig;
import com.yiranpay.message.mapper.MsgSmsConfigMapper;
import com.yiranpay.message.service.IMsgSmsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 短息配置Service业务层处理
 * 
 * @author glb
 * @date 2020-04-06
 */
@Service
public class MsgSmsConfigServiceImpl implements IMsgSmsConfigService
{
    @Autowired
    private MsgSmsConfigMapper msgSmsConfigMapper;

    /**
     * 查询短息配置
     * 
     * @param smsConfigId 短息配置ID
     * @return 短息配置
     */
    @Override
    public MsgSmsConfig selectMsgSmsConfigById(Long smsConfigId)
    {
        return msgSmsConfigMapper.selectMsgSmsConfigById(smsConfigId);
    }

    /**
     * 查询短息配置列表
     * 
     * @param msgSmsConfig 短息配置
     * @return 短息配置
     */
    @Override
    public List<MsgSmsConfig> selectMsgSmsConfigList(MsgSmsConfig msgSmsConfig)
    {
        return msgSmsConfigMapper.selectMsgSmsConfigList(msgSmsConfig);
    }

    /**
     * 新增短息配置
     * 
     * @param msgSmsConfig 短息配置
     * @return 结果
     */
    @Override
    public int insertMsgSmsConfig(MsgSmsConfig msgSmsConfig)
    {
        msgSmsConfig.setCreateTime(DateUtils.getNowDate());
        return msgSmsConfigMapper.insertMsgSmsConfig(msgSmsConfig);
    }

    /**
     * 修改短息配置
     * 
     * @param msgSmsConfig 短息配置
     * @return 结果
     */
    @Override
    public int updateMsgSmsConfig(MsgSmsConfig msgSmsConfig)
    {
        msgSmsConfig.setUpdateTime(DateUtils.getNowDate());
        return msgSmsConfigMapper.updateMsgSmsConfig(msgSmsConfig);
    }

    /**
     * 删除短息配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMsgSmsConfigByIds(String ids)
    {
        return msgSmsConfigMapper.deleteMsgSmsConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除短息配置信息
     * 
     * @param smsConfigId 短息配置ID
     * @return 结果
     */
    @Override
    public int deleteMsgSmsConfigById(Long smsConfigId)
    {
        return msgSmsConfigMapper.deleteMsgSmsConfigById(smsConfigId);
    }
}
