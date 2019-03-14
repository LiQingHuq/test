package cn.scetc.web.entity;

import java.util.Date;

public class Blog2 {

	private Integer blog_id;
	private String blog_title;
	private String blog_context;
	private Integer blog_click;
	private String blog_summary;
	private Date blog_time;   
	private String type_name;
	
	private int count;
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Blog2(Integer blog_id, String blog_title, String blog_context, Integer blog_click, String blog_summary,
			Date blog_time,String type_name) {
		super();
		this.blog_id = blog_id;
		this.blog_title = blog_title;
		this.blog_context = blog_context;
		this.blog_click = blog_click;
		this.blog_summary = blog_summary;
		this.blog_time = blog_time;
		this.type_name=type_name;
	}
	
	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public Integer getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(Integer blog_id) {
		this.blog_id = blog_id;
	}
	public String getBlog_title() {
		return blog_title;
	}
	public void setBlog_title(String blog_title) {
		this.blog_title = blog_title;
	}
	public String getBlog_context() {
		return blog_context;
	}
	public void setBlog_context(String blog_context) {
		this.blog_context = blog_context;
	}
	public Integer getBlog_click() {
		return blog_click;
	}
	public void setBlog_click(Integer blog_click) {
		this.blog_click = blog_click;
	}
	public String getBlog_summary() {
		return blog_summary;
	}
	public void setBlog_summary(String blog_summary) {
		this.blog_summary = blog_summary;
	}
	public Date getBlog_time() {
		return blog_time;
	}
	public void setBlog_time(Date blog_time) {
		this.blog_time = blog_time;
	}	  
}
