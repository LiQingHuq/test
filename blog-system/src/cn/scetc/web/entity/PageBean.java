package cn.scetc.web.entity;

import java.util.List;

public class PageBean<T>{
   private int pc;//当前页码pagecode
   private int tp;//总页数，totalpage
   private int tr;//总记录数totalrecord
   private int ps;//每页记录数pagesize
   private List<T> beanList;//当前页的记录
   
public int getPc() {
	return pc;
}
public void setPc(int pc) {
	this.pc = pc;
}
/**
 * 计算总页数
 * @return
 */
public int getTp() { 
	int tp=tr/ps;
	return tr%ps==0?tp:tp+1;
}
public void setTp(int tp) {
	this.tp = tp;
}
public int getTr() {
	return tr;
}
public void setTr(int tr) {
	this.tr = tr;
}
public int getPs() {
	return ps;
}
public void setPs(int ps) {
	this.ps = ps;
}
public List<T> getBeanList() {
	return beanList;
}
public void setBeanList(List<T> beanList) {
	this.beanList = beanList;
}
public PageBean(int pc, int tr, int ps,int tp, List<T> beanList) {
	super();
	this.pc = pc;
	this.tp = tp;
	this.tr = tr;
	this.ps = ps;
	this.beanList = beanList;
}
public PageBean() {
	super();
	// TODO Auto-generated constructor stub
}
   
}
