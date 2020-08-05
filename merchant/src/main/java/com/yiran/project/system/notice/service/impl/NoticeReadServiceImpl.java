package com.yiran.project.system.notice.service.impl;

import com.yiran.common.utils.security.ShiroUtils;
import com.yiran.common.utils.text.Convert;
import com.yiran.project.system.notice.domain.Notice;
import com.yiran.project.system.notice.domain.NoticeRead;
import com.yiran.project.system.notice.mapper.NoticeMapper;
import com.yiran.project.system.notice.mapper.NoticeReadMapper;
import com.yiran.project.system.notice.service.INoticeReadService;
import com.yiran.project.system.notice.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公告 服务层实现
 * 
 * @author yiran
 * @date 2018-06-25
 */
@Service
public class NoticeReadServiceImpl implements INoticeReadService
{
    @Autowired
    private NoticeReadMapper noticeReadMapper;

    @Override
    public void read(long noticeId){

        NoticeRead noticeRead= new NoticeRead();
        noticeRead.setLoginName(ShiroUtils.getLoginName());
        noticeRead.setNoticeId(noticeId);
        NoticeRead read=noticeReadMapper.selectRead(noticeRead);
        if (read==null || ("0").equals(read.getCheckUp())){
            noticeRead.setCheckUp("1");

            noticeRead.setCreateBy(ShiroUtils.getLoginName());
            noticeReadMapper.insertNoticeRead(noticeRead);
        }



    }

}
