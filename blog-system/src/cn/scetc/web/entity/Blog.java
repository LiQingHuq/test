package cn.scetc.web.entity;

import java.util.Date;

import javax.persistence.Entity;
@Entity
public class Blog {

	private Integer blog_id;
	private String blog_title;
	private String blog_context;
	private Integer blog_click;
	private String blog_summary;
	private Date blog_time;
    private Blog_Type blog_Type;
	public Blog(Integer blog_id2, String blog_title2, String blog_context2, Integer blog_click2, String blog_summary2,
			Date blog_time2, Blog_Type blog_Type) {
		// TODO Auto-generated constructor stub
	}
	public Blog() {
		// TODO Auto-generated constructor stub
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
	public Blog_Type getBlog_Type() {
		return blog_Type;
	}
	public void setBlog_Type(Blog_Type blog_Type) {
		this.blog_Type = blog_Type;
	}
    
}
