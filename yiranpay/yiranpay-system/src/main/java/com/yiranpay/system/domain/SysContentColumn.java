package com.yiranpay.system.domain;


import com.yiranpay.common.core.domain.BaseEntity;

/**
 * 网站系统栏目表 t_channel
 * 
 * @author yiran
 * @date 2018-07-26
 */
public class SysContentColumn extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 栏目主键 */
	private Integer id;
	/** 栏目是否是自定义链接，0表示否，1表示是 */
	private Integer customLink;
	/** 自定义链接地址 */
	private String customLinkUrl;
	/** 是否是首页栏目，0表示否，1表示是 */
	private Integer isIndex;
	/**  是否是首页的顶部导航栏目，0表示否，1表示是 */
	private Integer isTopNav;
	/** 栏目名称 */
	private String name;
	/** 栏目的序号 */
	private Integer orders;
	/** 是否是推荐栏目，0表示否，1表示是 */
	private Integer recommend;
	/** 栏目的状态，0表示启用，1表示停止 */
	private Integer status;
	/** 栏目的类型 */
	private Integer type;
	/** 父类栏目id */
	private Integer pid;
	/** 导航的序号 */
	private Integer navOrder;
	/** 父名称 */
    private String parentName;

	/**
	 * 设置：栏目主键
	 */
	public void setId(Integer id) 
	{
		this.id = id;
	}
	
	/**
	 * 获取：栏目主键
	 */
	public Integer getId() 
	{
		return id;
	}
	
	/**
	 * 设置：栏目是否是自定义链接，0表示否，1表示是
	 */
	public void setCustomLink(Integer customLink) 
	{
		this.customLink = customLink;
	}
	
	/**
	 * 获取：栏目是否是自定义链接，0表示否，1表示是
	 */
	public Integer getCustomLink() 
	{
		return customLink;
	}
	
	/**
	 * 设置：自定义链接地址
	 */
	public void setCustomLinkUrl(String customLinkUrl) 
	{
		this.customLinkUrl = customLinkUrl;
	}
	
	/**
	 * 获取：自定义链接地址
	 */
	public String getCustomLinkUrl() 
	{
		return customLinkUrl;
	}
	
	/**
	 * 设置：是否是首页栏目，0表示否，1表示是
	 */
	public void setIsIndex(Integer isIndex) 
	{
		this.isIndex = isIndex;
	}
	
	/**
	 * 获取：是否是首页栏目，0表示否，1表示是
	 */
	public Integer getIsIndex() 
	{
		return isIndex;
	}
	
	/**
	 * 设置： 是否是首页的顶部导航栏目，0表示否，1表示是
	 */
	public void setIsTopNav(Integer isTopNav) 
	{
		this.isTopNav = isTopNav;
	}
	
	/**
	 * 获取： 是否是首页的顶部导航栏目，0表示否，1表示是
	 */
	public Integer getIsTopNav() 
	{
		return isTopNav;
	}
	
	/**
	 * 设置：栏目名称
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * 获取：栏目名称
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * 设置：栏目的序号
	 */
	public void setOrders(Integer orders) 
	{
		this.orders = orders;
	}
	
	/**
	 * 获取：栏目的序号
	 */
	public Integer getOrders() 
	{
		return orders;
	}
	
	/**
	 * 设置：是否是推荐栏目，0表示否，1表示是
	 */
	public void setRecommend(Integer recommend) 
	{
		this.recommend = recommend;
	}
	
	/**
	 * 获取：是否是推荐栏目，0表示否，1表示是
	 */
	public Integer getRecommend() 
	{
		return recommend;
	}
	
	/**
	 * 设置：栏目的状态，0表示启用，1表示停止
	 */
	public void setStatus(Integer status) 
	{
		this.status = status;
	}
	
	/**
	 * 获取：栏目的状态，0表示启用，1表示停止
	 */
	public Integer getStatus() 
	{
		return status;
	}
	
	/**
	 * 设置：栏目的类型
	 */
	public void setType(Integer type) 
	{
		this.type = type;
	}
	
	/**
	 * 获取：栏目的类型
	 */
	public Integer getType() 
	{
		return type;
	}
	
	/**
	 * 设置：父类栏目id
	 */
	public void setPid(Integer pid) 
	{
		this.pid = pid;
	}
	
	/**
	 * 获取：父类栏目id
	 */
	public Integer getPid() 
	{
		return pid;
	}
	
	/**
	 * 设置：导航的序号
	 */
	public void setNavOrder(Integer navOrder) 
	{
		this.navOrder = navOrder;
	}
	
	/**
	 * 获取：导航的序号
	 */
	public Integer getNavOrder() 
	{
		return navOrder;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	@Override
	public String toString() {
		return "Channel [id=" + id + ", customLink=" + customLink + ", customLinkUrl=" + customLinkUrl + ", isIndex="
				+ isIndex + ", isTopNav=" + isTopNav + ", name=" + name + ", orders=" + orders + ", recommend="
				+ recommend + ", status=" + status + ", type=" + type + ", pid=" + pid + ", navOrder=" + navOrder
				+ ", parentName=" + parentName + "]";
	}
	
	
}
