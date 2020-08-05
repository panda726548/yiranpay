package com.yiranpay.message.mapper;

import com.yiranpay.message.domain.MsgSmsTemplate;
import java.util.List;

/**
 * 短息模板Mapper接口
 * 
 * @author glb
 * @date 2020-04-06
 */
public interface MsgSmsTemplateMapper 
{
    /**
     * 查询短息模板
     * 
     * @param smsTemplateId 短息模板ID
     * @return 短息模板
     */
    public MsgSmsTemplate selectMsgSmsTemplateById(Long smsTemplateId);

    /**
     * 查询短息模板列表
     * 
     * @param msgSmsTemplate 短息模板
     * @return 短息模板集合
     */
    public List<MsgSmsTemplate> selectMsgSmsTemplateList(MsgSmsTemplate msgSmsTemplate);

    /**
     * 查询短息模板
     *
     * @param smsTemplateCode 模版编码
     * @return 短息模板
     */
    public MsgSmsTemplate selectMsgSmsTemplateByCode(String smsTemplateCode);

    /**
     * 新增短息模板
     * 
     * @param msgSmsTemplate 短息模板
     * @return 结果
     */
    public int insertMsgSmsTemplate(MsgSmsTemplate msgSmsTemplate);

    /**
     * 修改短息模板
     * 
     * @param msgSmsTemplate 短息模板
     * @return 结果
     */
    public int updateMsgSmsTemplate(MsgSmsTemplate msgSmsTemplate);

    /**
     * 删除短息模板
     * 
     * @param smsTemplateId 短息模板ID
     * @return 结果
     */
    public int deleteMsgSmsTemplateById(Long smsTemplateId);

    /**
     * 批量删除短息模板
     * 
     * @param smsTemplateIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMsgSmsTemplateByIds(String[] smsTemplateIds);
}
