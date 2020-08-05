package com.yiran.project.merchant.order.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yiran.framework.aspectj.lang.annotation.Excel;
import com.yiran.framework.web.domain.BaseEntity;

/**
 * 对账下载URL对象 reconciliation_event
 * 
 * @author panda
 * @date 2020-04-30
 */
public class ReconciliationEvent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 事件开始日期 */
    @Excel(name = "事件开始日期")
    private String start;

    /** $column.columnComment */
    @Excel(name = "事件开始日期")
    private String url;

    /** 渠道编号 */
    @Excel(name = "渠道编号")
    private String channelcodes;

    /**
     * 商户ID
     */
    private String memberId;
    
    public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setStart(String start) 
    {
        this.start = start;
    }

    public String getStart() 
    {
        return start;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setChannelcodes(String channelcodes) 
    {
        this.channelcodes = channelcodes;
    }

    public String getChannelcodes() 
    {
        return channelcodes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("start", getStart())
            .append("url", getUrl())
            .append("channelcodes", getChannelcodes())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
