package cn.scetc.web.entity;

import java.util.Date;

import javax.persistence.Entity;
@Entity
public class Comment {
	private Integer comment_id;
	private String comment_context;
	private Date comment_time;
	private String comment_ip;
	private Integer blog_id;
	public Integer getComment_id() {
		return comment_id;
	}
	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}
	public String getComment_context() {
		return comment_context;
	}
	public void setComment_context(String comment_context) {
		this.comment_context = comment_context;
	}
	public Date getComment_time() {
		return comment_time;
	}
	public void setComment_time(Date comment_time) {
		this.comment_time = comment_time;
	}
	public String getComment_ip() {
		return comment_ip;
	}
	public void setComment_ip(String comment_ip) {
		this.comment_ip = comment_ip;
	}
	public Integer getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(Integer blog_id) {
		this.blog_id = blog_id;
	}
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comment(Integer comment_id, String comment_context, Date comment_time, String comment_ip, Integer blog_id) {
		super();
		this.comment_id = comment_id;
		this.comment_context = comment_context;
		this.comment_time = comment_time;
		this.comment_ip = comment_ip;
		this.blog_id = blog_id;
	}
	@Override
	public String toString() {
		return "Comment [comment_id=" + comment_id + ", comment_context=" + comment_context + ", comment_time="
				+ comment_time + ", comment_ip=" + comment_ip + ", blohg_id=" + blog_id + "]";
	}
	
}
