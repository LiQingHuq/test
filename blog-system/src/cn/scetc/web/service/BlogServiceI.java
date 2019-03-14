package cn.scetc.web.service;

import java.util.Date;
import java.util.List;
import cn.scetc.web.entity.Blog;
import cn.scetc.web.entity.Blog_Type;
import cn.scetc.web.entity.PageBean;

public interface BlogServiceI {
	/**
	 * 根据日期月份分组查询 - 月份-当年博客的数量
	 * @return
	 */
	public int findCountByTypeID(int  type_id);
	public List<Blog> countList();
	public int  count(int id);
	/**
	 * 分页查询博客
	 * @return
	 */
	public List<Blog> query(int pc, int ps);
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public int getTotal();
	
	/**
	 * 通过Id查找实体
	 * @param id
	 * @return
	 */
	public Blog findById(int id);
	
	/**
	 * 更新博客信息
	 * @param blog
	 * @return
	 */
	public void update(Blog blog); 
	
	
	/**
	 * 添加博客信息
	 * @param blog
	 * @return
	 */
	public void add(Blog blog);
	
	/**
	 * 删除博客信息
	 * @param id
	 * @return
	 */
	public void delete(Blog blog);
	
	/**
	 * 查询指定的博客类别下的博客数量,
	 * @param typeId
	 * @return
	 */
	public Blog_Type getBlogByTypeId(Integer typeId);
	public List<Blog> findByTime(Date string);
	public List<Blog> findByid(int id);
	public PageBean<Blog> query1(int pc, int ps);
	public List<Blog> countTime();
}
