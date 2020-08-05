package com.yiranpay.message.service.impl;

import com.yiranpay.common.core.text.Convert;
import com.yiranpay.common.utils.DateUtils;
import com.yiranpay.message.domain.MsgSendRecord;
import com.yiranpay.message.mapper.MsgSendRecordMapper;
import com.yiranpay.message.service.IMsgSendRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 短信发送记录Service业务层处理
 * 
 * @author glb
 * @date 2020-04-06
 */
@Service
public class MsgSendRecordServiceImpl implements IMsgSendRecordService
{
    @Autowired
    private MsgSendRecordMapper msgSendRecordMapper;

    /**
     * 查询短信发送记录
     * 
     * @param sendRecordId 短信发送记录ID
     * @return 短信发送记录
     */
    @Override
    public MsgSendRecord selectMsgSendRecordById(Long sendRecordId)
    {
        return msgSendRecordMapper.selectMsgSendRecordById(sendRecordId);
    }

    /**
     * 查询短信发送记录列表
     * 
     * @param msgSendRecord 短信发送记录
     * @return 短信发送记录
     */
    @Override
    public List<MsgSendRecord> selectMsgSendRecordList(MsgSendRecord msgSendRecord)
    {
        return msgSendRecordMapper.selectMsgSendRecordList(msgSendRecord);
    }

    /**
     * 新增短信发送记录
     * 
     * @param msgSendRecord 短信发送记录
     * @return 结果
     */
    @Override
    public int insertMsgSendRecord(MsgSendRecord msgSendRecord)
    {
        msgSendRecord.setCreateTime(DateUtils.getNowDate());
        return msgSendRecordMapper.insertMsgSendRecord(msgSendRecord);
    }

    /**
     * 修改短信发送记录
     * 
     * @param msgSendRecord 短信发送记录
     * @return 结果
     */
    @Override
    public int updateMsgSendRecord(MsgSendRecord msgSendRecord)
    {
        msgSendRecord.setUpdateTime(DateUtils.getNowDate());
        return msgSendRecordMapper.updateMsgSendRecord(msgSendRecord);
    }

    /**
     * 删除短信发送记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMsgSendRecordByIds(String ids)
    {
        return msgSendRecordMapper.deleteMsgSendRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除短信发送记录信息
     * 
     * @param sendRecordId 短信发送记录ID
     * @return 结果
     */
    @Override
    public int deleteMsgSendRecordById(Long sendRecordId)
    {
        return msgSendRecordMapper.deleteMsgSendRecordById(sendRecordId);
    }

    @Override
    public int getSendSmSCount(MsgSendRecord msgSendRecord) {
        return msgSendRecordMapper.getSendSmSCount(msgSendRecord);
    }
}
