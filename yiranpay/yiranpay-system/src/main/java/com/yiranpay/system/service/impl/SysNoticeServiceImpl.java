package com.yiranpay.system.service.impl;

import java.util.List;

import com.yiranpay.common.constant.ShiroConstants;
import com.yiranpay.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiranpay.common.core.text.Convert;
import com.yiranpay.system.domain.SysNotice;
import com.yiranpay.system.mapper.SysNoticeMapper;
import com.yiranpay.system.service.ISysNoticeService;

/**
 * 公告 服务层实现
 * 
 * @author yiranpay
 * @date 2018-06-25
 */
@Service
public class SysNoticeServiceImpl implements ISysNoticeService
{
    @Autowired
    private SysNoticeMapper noticeMapper;

    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public SysNotice selectNoticeById(Long noticeId)
    {
        return noticeMapper.selectNoticeById(noticeId);
    }

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    @Override
    public List<SysNotice> selectNoticeList(SysNotice notice)
    {
        return noticeMapper.selectNoticeList(notice);
    }

    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int insertNotice(SysNotice notice)
    {
        if ("1".equals(notice.getStatus())){
            notice.setPublishDate(DateUtils.getDate());
        }
        return noticeMapper.insertNotice(notice);
    }

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int updateNotice(SysNotice notice)
    {
        return noticeMapper.updateNotice(notice);
    }

    /**
     * 删除公告对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(String ids)
    {
        return noticeMapper.deleteNoticeByIds(Convert.toStrArray(ids));
    }

    /**
     * 发布公告
     * @param notice
     * @return
     */
    @Override
    public int publish(SysNotice notice){
        notice.setStatus("1");
        return noticeMapper.publish(notice);
    }
}
