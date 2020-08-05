package com.yiranpay.message.mapper;

import com.yiranpay.message.domain.MsgSendRecord;

import java.util.List;

/**
 * 短信发送记录Mapper接口
 * 
 * @author glb
 * @date 2020-04-06
 */
public interface MsgSendRecordMapper 
{
    /**
     * 查询短信发送记录
     * 
     * @param sendRecordId 短信发送记录ID
     * @return 短信发送记录
     */
    public MsgSendRecord selectMsgSendRecordById(Long sendRecordId);

    /**
     * 查询短信发送记录列表
     * 
     * @param msgSendRecord 短信发送记录
     * @return 短信发送记录集合
     */
    public List<MsgSendRecord> selectMsgSendRecordList(MsgSendRecord msgSendRecord);

    /**
     * 新增短信发送记录
     * 
     * @param msgSendRecord 短信发送记录
     * @return 结果
     */
    public int insertMsgSendRecord(MsgSendRecord msgSendRecord);

    /**
     * 修改短信发送记录
     * 
     * @param msgSendRecord 短信发送记录
     * @return 结果
     */
    public int updateMsgSendRecord(MsgSendRecord msgSendRecord);

    /**
     * 删除短信发送记录
     * 
     * @param sendRecordId 短信发送记录ID
     * @return 结果
     */
    public int deleteMsgSendRecordById(Long sendRecordId);

    /**
     * 批量删除短信发送记录
     * 
     * @param sendRecordIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMsgSendRecordByIds(String[] sendRecordIds);

    /**
     * 获取某个手机号每天发送短信次数
     * @param msgSendRecord 短信发送记录
     * @return 结果
     */
    public int getSendSmSCount(MsgSendRecord msgSendRecord);
}
