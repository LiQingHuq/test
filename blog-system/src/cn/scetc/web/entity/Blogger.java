package cn.scetc.web.entity;

import javax.persistence.Entity;

@Entity
public class Blogger {
private Integer blogger_id;
private String blogger_name;
private String blogger_nickname;
private String blogger_password;
private String blogger_context;
private String blogger_image;
private String blogger_sign;

public Integer getBlogger_id() {
	return blogger_id;
}
public void setBlogger_id(Integer blogger_id) {
	this.blogger_id = blogger_id;
}
public String getBlogger_name() {
	return blogger_name;
}
public void setBlogger_name(String blogger_name) {
	this.blogger_name = blogger_name;
}
public String getBlogger_nickname() {
	return blogger_nickname;
}
public void setBlogger_nickname(String blogger_nickname) {
	this.blogger_nickname = blogger_nickname;
}
public String getBlogger_password() {
	return blogger_password;
}
public void setBlogger_password(String blogger_password) {
	this.blogger_password = blogger_password;
}
public String getBlogger_context() {
	return blogger_context;
}
public void setBlogger_context(String blogger_context) {
	this.blogger_context = blogger_context;
}
public String getBlogger_image() {
	return blogger_image;
}
public void setBlogger_image(String blogger_image) {
	this.blogger_image = blogger_image;
}
public String getBlogger_sign() {
	return blogger_sign;
}
public void setBlogger_sign(String blogger_sign) {
	this.blogger_sign = blogger_sign;
}
public Blogger() {
	super();
	// TODO Auto-generated constructor stub
}
public Blogger(Integer blogger_id, String blogger_name, String blogger_nickname, String blogger_password,
		String blogger_context, String blogger_image, String blogger_sign) {
	super();
	this.blogger_id = blogger_id;
	this.blogger_name = blogger_name;
	this.blogger_nickname = blogger_nickname;
	this.blogger_password = blogger_password;
	this.blogger_context = blogger_context;
	this.blogger_image = blogger_image;
	this.blogger_sign = blogger_sign;
}
@Override
public String toString() {
	return "Blogger [blogger_id=" + blogger_id + ", blogger_name=" + blogger_name + ", blogger_nickname="
			+ blogger_nickname + ", blogger_password=" + blogger_password + ", blogger_context=" + blogger_context
			+ ", blogger_image=" + blogger_image + ", blogger_sign=" + blogger_sign + "]";
}

}
