package com.yiranpay.system.domain;



import com.yiranpay.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 文章管理表 t_topic
 * 
 * @author yiran
 * @date 2018-07-26
 */
public class SysContentTopic extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 文章编号 */
	private Integer id;

    /** 部门父ID */
    private Integer pid;
	/** 作者 */
	private String author;
	/** 栏目图片id，如果该栏目是图片类型的栏目，就会显示这个id的图片 */
	private Integer channelPicId;
	/** 文章的内容 */
	private String topicContent;
	/** 关键字:通过|来分割不同的关键字 */
	private String keyword;
	/** 文章的发布时间，用来进行排序的 */
	private Date publishDate;
	/** 是否是推荐文章,0表示不推荐，1表示推荐 */
	private Integer recommend;
	/** 文章的状态，默认为0表示未发表，1表示已发布 */
	private Integer status;
	/** 文章的摘要 */
	private String summary;
	/** 文章标题 */
	private String title;
	/** 栏目ID */
	private Integer cid;
	/** 用户ID */
	private Integer uid;
	/** 栏目名称 */
	private String cname;
	/** 文章图片名字 */
	private String newName;
	/** 原文链接 */
	private String yuanwenLike;
	/** 浏览次数 */
	private Integer browseNumber;
	
	private SysContentColumn column;

	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public SysContentColumn getColumn() {
		return column;
	}

	public void setColumn(SysContentColumn column) {
		this.column = column;
	}

	/**
	 * 设置：文章编号
	 */
	public void setId(Integer id) 
	{
		this.id = id;
	}
	
	/**
	 * 获取：文章编号
	 */
	public Integer getId() 
	{
		return id;
	}
	
	/**
	 * 设置：作者
	 */
	public void setAuthor(String author) 
	{
		this.author = author;
	}
	
	/**
	 * 获取：作者
	 */
	public String getAuthor() 
	{
		return author;
	}
	
	/**
	 * 设置：栏目图片id，如果该栏目是图片类型的栏目，就会显示这个id的图片
	 */
	public void setChannelPicId(Integer channelPicId) 
	{
		this.channelPicId = channelPicId;
	}
	
	/**
	 * 获取：栏目图片id，如果该栏目是图片类型的栏目，就会显示这个id的图片
	 */
	public Integer getChannelPicId() 
	{
		return channelPicId;
	}
	
	
	
	
	public String getTopicContent() {
		return topicContent;
	}

	public void setTopicContent(String topicContent) {
		this.topicContent = topicContent;
	}

	/**
	 * 设置：关键字:通过|来分割不同的关键字
	 */
	public void setKeyword(String keyword) 
	{
		this.keyword = keyword;
	}
	
	/**
	 * 获取：关键字:通过|来分割不同的关键字
	 */
	public String getKeyword() 
	{
		return keyword;
	}
	
	/**
	 * 设置：文章的发布时间，用来进行排序的
	 */
	public void setPublishDate(Date publishDate) 
	{
		this.publishDate = publishDate;
	}
	
	/**
	 * 获取：文章的发布时间，用来进行排序的
	 */
	public Date getPublishDate() 
	{
		return publishDate;
	}
	
	/**
	 * 设置：是否是推荐文章,0表示不推荐，1表示推荐
	 */
	public void setRecommend(Integer recommend) 
	{
		this.recommend = recommend;
	}
	
	/**
	 * 获取：是否是推荐文章,0表示不推荐，1表示推荐
	 */
	public Integer getRecommend() 
	{
		return recommend;
	}
	
	/**
	 * 设置：文章的状态，默认为0表示未发表，1表示已发布
	 */
	public void setStatus(Integer status) 
	{
		this.status = status;
	}
	
	/**
	 * 获取：文章的状态，默认为0表示未发表，1表示已发布
	 */
	public Integer getStatus() 
	{
		return status;
	}
	
	/**
	 * 设置：文章的摘要
	 */
	public void setSummary(String summary) 
	{
		this.summary = summary;
	}
	
	/**
	 * 获取：文章的摘要
	 */
	public String getSummary() 
	{
		return summary;
	}
	
	/**
	 * 设置：文章标题
	 */
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	/**
	 * 获取：文章标题
	 */
	public String getTitle() 
	{
		return title;
	}
	
	/**
	 * 设置：栏目ID
	 */
	public void setCid(Integer cid) 
	{
		this.cid = cid;
	}
	
	/**
	 * 获取：栏目ID
	 */
	public Integer getCid() 
	{
		return cid;
	}
	
	/**
	 * 设置：用户ID
	 */
	public void setUid(Integer uid) 
	{
		this.uid = uid;
	}
	
	/**
	 * 获取：用户ID
	 */
	public Integer getUid() 
	{
		return uid;
	}
	
	/**
	 * 设置：栏目名称
	 */
	public void setCname(String cname) 
	{
		this.cname = cname;
	}
	
	/**
	 * 获取：栏目名称
	 */
	public String getCname() 
	{
		return cname;
	}
	
	/**
	 * 设置：文章图片名字
	 */
	public void setNewName(String newName) 
	{
		this.newName = newName;
	}
	
	/**
	 * 获取：文章图片名字
	 */
	public String getNewName() 
	{
		return newName;
	}
	
	/**
	 * 设置：原文链接
	 */
	public void setYuanwenLike(String yuanwenLike) 
	{
		this.yuanwenLike = yuanwenLike;
	}
	
	/**
	 * 获取：原文链接
	 */
	public String getYuanwenLike() 
	{
		return yuanwenLike;
	}
	
	/**
	 * 设置：浏览次数
	 */
	public void setBrowseNumber(Integer browseNumber) 
	{
		this.browseNumber = browseNumber;
	}
	
	/**
	 * 获取：浏览次数
	 */
	public Integer getBrowseNumber() 
	{
		return browseNumber;
	}
	
	
}
