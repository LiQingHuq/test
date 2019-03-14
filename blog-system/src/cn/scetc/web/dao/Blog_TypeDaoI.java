package cn.scetc.web.dao;

import java.util.List;
import java.util.Map;

import cn.scetc.web.entity.Blog_Type;
import cn.scetc.web.entity.PageBean;

public interface Blog_TypeDaoI {
	/**
	 * 查询所有博客类型 以及对应的博客数量
	 * @return
	 */
	public List<Blog_Type> countList();
	
	/**
	 * 通过id查询博客类型
	 * @param id
	 * @return
	 */
	public Blog_Type findById(Integer id);
	
	/**
	 * 分页查询博客类别信息
	 * @param map
	 * @return
	 */
	public List<Blog_Type> query(int pc, int ps);
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public int getTotal();
	
	/**
	 * 添加博客类别信息
	 * @param blogType
	 * @return
	 */
	public void add(Blog_Type blogType);
	
	/**
	 * 修改博客类别信息
	 * @param blogType
	 * @return
	 */
	public void update(Blog_Type blogType);
	
	/**
	 * 删除博客类别信息
	 * @param blogType
	 * @return
	 */
	public void delete(Blog_Type blogType);
}
