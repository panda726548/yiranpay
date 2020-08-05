package com.yiranpay.message.service.impl;

import com.yiranpay.common.core.text.Convert;
import com.yiranpay.common.utils.DateUtils;
import com.yiranpay.message.domain.MsgSmsTemplate;
import com.yiranpay.message.mapper.MsgSmsTemplateMapper;
import com.yiranpay.message.service.IMsgSmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 短息模板Service业务层处理
 * 
 * @author glb
 * @date 2020-04-06
 */
@Service
public class MsgSmsTemplateServiceImpl implements IMsgSmsTemplateService
{
    @Autowired
    private MsgSmsTemplateMapper msgSmsTemplateMapper;

    /**
     * 查询短息模板
     * 
     * @param smsTemplateId 短息模板ID
     * @return 短息模板
     */
    @Override
    public MsgSmsTemplate selectMsgSmsTemplateById(Long smsTemplateId)
    {
        return msgSmsTemplateMapper.selectMsgSmsTemplateById(smsTemplateId);
    }

    /**
     * 查询短息模板列表
     * 
     * @param msgSmsTemplate 短息模板
     * @return 短息模板
     */
    @Override
    public List<MsgSmsTemplate> selectMsgSmsTemplateList(MsgSmsTemplate msgSmsTemplate)
    {
        return msgSmsTemplateMapper.selectMsgSmsTemplateList(msgSmsTemplate);
    }

    /**
     * 新增短息模板
     * 
     * @param msgSmsTemplate 短息模板
     * @return 结果
     */
    @Override
    public int insertMsgSmsTemplate(MsgSmsTemplate msgSmsTemplate)
    {
        msgSmsTemplate.setCreateTime(DateUtils.getNowDate());
        return msgSmsTemplateMapper.insertMsgSmsTemplate(msgSmsTemplate);
    }

    /**
     * 修改短息模板
     * 
     * @param msgSmsTemplate 短息模板
     * @return 结果
     */
    @Override
    public int updateMsgSmsTemplate(MsgSmsTemplate msgSmsTemplate)
    {
        return msgSmsTemplateMapper.updateMsgSmsTemplate(msgSmsTemplate);
    }

    /**
     * 删除短息模板对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMsgSmsTemplateByIds(String ids)
    {
        return msgSmsTemplateMapper.deleteMsgSmsTemplateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除短息模板信息
     * 
     * @param smsTemplateId 短息模板ID
     * @return 结果
     */
    @Override
    public int deleteMsgSmsTemplateById(Long smsTemplateId)
    {
        return msgSmsTemplateMapper.deleteMsgSmsTemplateById(smsTemplateId);
    }
}
