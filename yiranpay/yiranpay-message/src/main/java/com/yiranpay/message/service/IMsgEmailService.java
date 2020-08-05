package com.yiranpay.message.service;

import com.yiranpay.message.domain.MsgEmail;
import com.yiranpay.system.domain.SysUser;

import java.util.List;

/**
 * Email邮件Service接口
 * 
 * @author glb
 * @date 2020-04-06
 */
public interface IMsgEmailService 
{
    /**
     * 查询Email邮件
     * 
     * @param emailId Email邮件ID
     * @return Email邮件
     */
    public MsgEmail selectMsgEmailById(Long emailId);


    /**
     * 发送内部邮件
     * @param msgEmail
     * @return
     */
    public int sendWithInside(MsgEmail msgEmail, SysUser user);

    /**
     * 发送外部邮件
     * @param msgEmail
     * @return
     */
    public int sendWithOutside(MsgEmail msgEmail, SysUser user);

    /**
     * 移入回收站
     * @param msgEmail
     * @return
     */
    public int moveToRecoveryBin(MsgEmail msgEmail);

    /**
     * 恢复到收件箱
     * @param msgEmail
     * @return
     */
    public int moveToInBox(MsgEmail msgEmail);

    /**
     * 保存至草稿
     * @param msgEmail
     * @return
     */
    public int saveToRough(MsgEmail msgEmail, SysUser user);

    /**
     * 查询Email邮件列表
     * 
     * @param msgEmail Email邮件
     * @return Email邮件集合
     */
    public List<MsgEmail> selectMsgEmailList(MsgEmail msgEmail);

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
     * 批量删除Email邮件
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMsgEmailByIds(String ids);

    /**
     * 删除Email邮件信息
     * 
     * @param emailId Email邮件ID
     * @return 结果
     */
    public int deleteMsgEmailById(Long emailId);

    /**
     * 批量物理删除
     * @param ids
     * @return
     */
    public int truncateEmailByIds(String ids);
}
