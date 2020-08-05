package com.yiran.project.system.notice.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yiran.project.system.notice.domain.NoticeRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.common.utils.security.ShiroUtils;
import com.yiran.common.utils.text.Convert;
import com.yiran.project.system.notice.mapper.NoticeMapper;
import com.yiran.project.system.notice.domain.Notice;
import com.yiran.project.system.notice.service.INoticeService;

/**
 * 公告 服务层实现
 * 
 * @author yiran
 * @date 2018-06-25
 */
@Service
public class NoticeServiceImpl implements INoticeService
{
    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public Notice selectNoticeById(Long noticeId)
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
    public List<Notice> selectNoticeList(Notice notice)
    {
        NoticeRead noticeRead=new NoticeRead();
        noticeRead.setLoginName(ShiroUtils.getLoginName());
        notice.setNoticeRead(noticeRead);
        return noticeMapper.selectNoticeList(notice);
    }

    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int insertNotice(Notice notice)
    {
        notice.setCreateBy(ShiroUtils.getLoginName());
        return noticeMapper.insertNotice(notice);
    }

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int updateNotice(Notice notice)
    {
        notice.setUpdateBy(ShiroUtils.getLoginName());
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

    @Override
    public String selectNoticeToday() {

        return noticeMapper.selectNoticeToday();
    }

    @Override
    public String selectNoticeNoRead(String loginName) {
        return noticeMapper.noticeNoReadNumber(loginName);
    }

	@Override
	public List<Notice> selectNoticeList6(Notice notice) {
		List<Notice> list = noticeMapper.selectNoticeList6(notice);
		List<Notice> list2 = new ArrayList<Notice>(); 
		if(list.size() > 0){
			for (Notice n : list) {
				if(n.getNoticeTitle().length() > 3){
					String title = n.getNoticeTitle().substring(0,3);
					n.setNoticeTitle(title+"...");
					list2.add(n);
				}else{
					n.setNoticeTitle(n.getNoticeTitle()+"...");
					list2.add(n);
				}
			}
		}
		return list2;
	}


}
