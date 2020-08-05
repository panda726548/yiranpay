package com.yiranpay.message.service;

import com.yiranpay.message.domain.MsgSmsConfig;

import java.util.List;

/**
 * 短息配置Service接口
 * 
 * @author glb
 * @date 2020-04-06
 */
public interface IMsgSmsConfigService 
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
     * 批量删除短息配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMsgSmsConfigByIds(String ids);

    /**
     * 删除短息配置信息
     * 
     * @param smsConfigId 短息配置ID
     * @return 结果
     */
    public int deleteMsgSmsConfigById(Long smsConfigId);
}
