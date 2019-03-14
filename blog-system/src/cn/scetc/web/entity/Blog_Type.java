package cn.scetc.web.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Entity
public class Blog_Type {
private Integer type_id;
private String type_name;
private Integer orderNum;


private int count;
public int getCount() {
	return count;
}

public void setCount(int count) {
	this.count = count;
}
private Set<Blog> blogs=new HashSet<Blog>();
public Integer getType_id() {
	return type_id;
}
public void setType_id(Integer type_id) {
	this.type_id = type_id;
}
public String getType_name() {
	return type_name;
}
public void setType_name(String type_name) {
	this.type_name = type_name;
}
public Integer getOrderNum() {
	return orderNum;
}
public void setOrderNum(Integer orderNum) {
	this.orderNum = orderNum;
}
public Set<Blog> getBlogs() {
	return blogs;
}
public void setBlogs(Set<Blog> blogs) {
	this.blogs = blogs;
}

}
