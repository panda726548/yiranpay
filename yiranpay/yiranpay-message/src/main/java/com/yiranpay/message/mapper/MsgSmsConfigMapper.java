package com.yiranpay.message.mapper;

import com.yiranpay.message.domain.MsgSmsConfig;

import java.util.List;

/**
 * 短息配置Mapper接口
 * 
 * @author glb
 * @date 2020-04-06
 */
public interface MsgSmsConfigMapper 
{
    /**
     * 查询短息配置
     * 
     * @param smsConfigId 短息配置ID
     * @return 短息配置
     */
    public MsgSmsConfig selectMsgSmsConfigById(Long smsConfigId);

    /**
     * 查询短息配置列表
     * 
     * @param msgSmsConfig 短息配置
     * @return 短息配置集合
     */
    public List<MsgSmsConfig> selectMsgSmsConfigList(MsgSmsConfig msgSmsConfig);

    /**
     * 新增短息配置
     * 
     * @param msgSmsConfig 短息配置
     * @return 结果
     */
    public int insertMsgSmsConfig(MsgSmsConfig msgSmsConfig);

    /**
     * 修改短息配置
     * 
     * @param msgSmsConfig 短息配置
     * @return 结果
     */
    public int updateMsgSmsConfig(MsgSmsConfig msgSmsConfig);

    /**
     * 删除短息配置
     * 
     * @param smsConfigId 短息配置ID
     * @return 结果
     */
    public int deleteMsgSmsConfigById(Long smsConfigId);

    /**
     * 批量删除短息配置
     * 
     * @param smsConfigIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMsgSmsConfigByIds(String[] smsConfigIds);

    /**
     * 根据key获取短信配置对象
     * @param smsKey
     * @return
     */
    public MsgSmsConfig selectSmsConfigBySmsKey(String smsKey);
}
