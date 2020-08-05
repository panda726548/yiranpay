package com.yiran.project.merchant.order.domain;

public class EventVO {
	
	private long id;

	private String title;
	
	private String start;
	
	private String url;

	public String getTitle() {
		return title;
	}

	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "EventVO [title=" + title + ", start=" + start + ", url=" + url + "]";
	}
	
	
}
