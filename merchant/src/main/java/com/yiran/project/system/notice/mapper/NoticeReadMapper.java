package com.yiran.project.system.notice.mapper;

import com.yiran.project.system.notice.domain.Notice;
import com.yiran.project.system.notice.domain.NoticeRead;

import java.util.List;

/**
 * 公告 数据层
 * 
 * @author yiran
 */
public interface NoticeReadMapper
{


    /**
     * 新增已读记录
     * 
     * @param noticeRead
     * @return 结果
     */
    public int insertNoticeRead(NoticeRead noticeRead);

    public NoticeRead selectRead(NoticeRead noticeRead);



}