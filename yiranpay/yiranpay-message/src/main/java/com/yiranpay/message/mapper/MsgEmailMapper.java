package com.yiranpay.message.mapper;

import com.yiranpay.message.domain.MsgEmail;

import java.util.List;

/**
 * Email邮件Mapper接口
 * 
 * @author glb
 * @date 2020-04-06
 */
public interface MsgEmailMapper 
{
    /**
     * 查询Email邮件
     * 
     * @param emailId Email邮件ID
     * @return Email邮件
     */
    public MsgEmail selectMsgEmailById(Long emailId);

    /**
     * 查询Email邮件列表
     * 
     * @param msgEmail Email邮件
     * @return Email邮件集合
     */
    public List<MsgEmail> selectMsgEmailList(MsgEmail msgEmail);


    /**
     * 移入回收站
     * @param email
     * @return
     */
    public int moveToRecoveryBin(MsgEmail msgEmail);

    /**
     * 恢复到收件箱
     * @param email
     * @return
     */
    public int moveToInBox(MsgEmail msgEmail);

    /**
     * 批量逻辑删除
     * @param emailIds
     * @return
     */
    public int deleteEmailByIds(Long[] emailIds);

    /**
     * 批量物理删除
     * @param emailIds
     * @return
     */
    public int truncateEmailByIds(Long[] emailIds);

    /**
     * 新增Email邮件
     * 
     * @param msgEmail Email邮件
     * @return 结果
     */
    public int insertMsgEmail(MsgEmail msgEmail);

    /**
     * 修改Email邮件
     * 
     * @param msgEmail Email邮件
     * @return 结果
     */
    public int updateMsgEmail(MsgEmail msgEmail);

    /**
     * 删除Email邮件
     * 
     * @param emailId Email邮件ID
     * @return 结果
     */
    public int deleteMsgEmailById(Long emailId);

    /**
     * 批量删除Email邮件
     * 
     * @param emailIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMsgEmailByIds(String[] emailIds);
}
