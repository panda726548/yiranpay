package com.yiranpay.message.service.impl;

import com.yiranpay.common.core.text.Convert;
import com.yiranpay.common.utils.DateUtils;
import com.yiranpay.message.config.EmailConfig;
import com.yiranpay.message.domain.MsgEmail;
import com.yiranpay.message.mapper.MsgEmailMapper;
import com.yiranpay.message.service.IMsgEmailService;
import com.yiranpay.message.util.EmailUtil;
import com.yiranpay.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Email邮件Service业务层处理
 * 
 * @author glb
 * @date 2020-04-06
 */
@Service
public class MsgEmailServiceImpl implements IMsgEmailService
{
    @Autowired
    private MsgEmailMapper msgEmailMapper;

    @Autowired
    private EmailConfig emailConfig;

    /**
     * 查询Email邮件
     * 
     * @param emailId Email邮件ID
     * @return Email邮件
     */
    @Override
    public MsgEmail selectMsgEmailById(Long emailId)
    {
        return msgEmailMapper.selectMsgEmailById(emailId);
    }

    /**
     *  发送内部邮件
     *  SysUser 当前登录用户
     */
    @Override
    public int sendWithInside(MsgEmail msgEmail, SysUser user) {
        int count = 0;
        String [] toUserIds = msgEmail.getToUserIds().split(",");
        for(String toUserId : toUserIds){
            msgEmail.setFromUser(user.getUserId());
            msgEmail.setToUser(Long.valueOf(toUserId));
            msgEmail.setCreateBy(user.getUserName());
            msgEmail.setSendStatus(0);
            count += msgEmailMapper.insertMsgEmail(msgEmail);
        }
        return count;
    }

    /**
     * 发送外部邮件
     * @param msgEmail
     * user 当前登录用户
     * @return
     */
    @Override
    public int sendWithOutside(MsgEmail msgEmail,SysUser user) {
        int count = 0;
        String [] toUserEmails = msgEmail.getToUserEmails().split(",");
        //发送邮件
        boolean isSend = EmailUtil.sendHtmlEmail(msgEmail.getEmailSubject(),toUserEmails,msgEmail.getEmailContent(),emailConfig);
        for(String toUserEmail : toUserEmails){
            msgEmail.setFromUser(user.getUserId());
            msgEmail.setToUserEmail(toUserEmail);
            msgEmail.setCreateBy(user.getUserName());
            msgEmail.setSendStatus(isSend?0:1);
            count += msgEmailMapper.insertMsgEmail(msgEmail);
        }
        return count;
    }

    /**
     * 移入回收站
     * @param msgEmail
     * @return
     */
    @Override
    public int moveToRecoveryBin(MsgEmail msgEmail) {
        return msgEmailMapper.moveToRecoveryBin(msgEmail);
    }

    /**
     * 保存至草稿
     * @param msgEmail
     * @return
     */
    @Override
    public int saveToRough(MsgEmail msgEmail, SysUser user) {
        msgEmail.setFromUser(user.getUserId());
        msgEmail.setCreateBy(user.getUserName());
        msgEmail.setSendStatus(1);
        return msgEmailMapper.insertMsgEmail(msgEmail);
    }

    /**
     * 恢复到收件箱
     * @param msgEmail
     * @return
     */
    @Override
    public int moveToInBox(MsgEmail msgEmail) {
        return msgEmailMapper.moveToInBox(msgEmail);
    }

    /**
     * 查询Email邮件列表
     * 
     * @param msgEmail Email邮件
     * @return Email邮件
     */
    @Override
    public List<MsgEmail> selectMsgEmailList(MsgEmail msgEmail)
    {
        return msgEmailMapper.selectMsgEmailList(msgEmail);
    }

    /**
     * 新增Email邮件
     * 
     * @param msgEmail Email邮件
     * @return 结果
     */
    @Override
    public int insertMsgEmail(MsgEmail msgEmail)
    {
        msgEmail.setCreateTime(DateUtils.getNowDate());
        return msgEmailMapper.insertMsgEmail(msgEmail);
    }

    /**
     * 修改Email邮件
     * 
     * @param msgEmail Email邮件
     * @return 结果
     */
    @Override
    public int updateMsgEmail(MsgEmail msgEmail)
    {
        msgEmail.setUpdateTime(DateUtils.getNowDate());
        return msgEmailMapper.updateMsgEmail(msgEmail);
    }

    /**
     * 删除Email邮件对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMsgEmailByIds(String ids)
    {
        return msgEmailMapper.deleteMsgEmailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除Email邮件信息
     * 
     * @param emailId Email邮件ID
     * @return 结果
     */
    @Override
    public int deleteMsgEmailById(Long emailId)
    {
        return msgEmailMapper.deleteMsgEmailById(emailId);
    }

    /**
     * 批量物理删除
     * @param ids
     * @return
     */
    @Override
    public int truncateEmailByIds(String ids) {
        Long[] emailIds = Convert.toLongArray(ids);
        return msgEmailMapper.truncateEmailByIds(emailIds);
    }
}
