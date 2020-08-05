package com.yiran.project.system.notice.domain;

/**
 * 公告已读标记
 */
public class NoticeRead {
    private long readId;
    private long noticeId;
    private String loginName;
    private String createTime;
    private String createBy;
    private String checkUp;

    public long getReadId() {
        return readId;
    }

    public void setReadId(long readId) {
        this.readId = readId;
    }

    public long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(long noticeId) {
        this.noticeId = noticeId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCheckUp() {
        return checkUp;
    }

    public void setCheckUp(String checkUp) {
        this.checkUp = checkUp;
    }
}
