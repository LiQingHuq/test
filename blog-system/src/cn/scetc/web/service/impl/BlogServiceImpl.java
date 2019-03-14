package cn.scetc.web.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.scetc.web.dao.BlogDaoI;
import cn.scetc.web.entity.Blog;
import cn.scetc.web.entity.Blog_Type;
import cn.scetc.web.entity.PageBean;
import cn.scetc.web.service.BlogServiceI;

@Service(value="blogServiceImpl")//<bean id="blogServiceImpl" class="cn.scetc.web.service.impl.BlogServiceImpl"/>
public class BlogServiceImpl implements BlogServiceI {

	@Autowired 
	private BlogDaoI blogDao;//<property id=blogDao ref="blogDaoImpl"/>
	/**
	 * 根据日期月份分组查询 - 月份-当年博客的数量
	 * @return
	 */
	@Override
	@Transactional
	public List<Blog> countTime() {
		return blogDao.countTime();
	}
	@Override
	@Transactional
	public List<Blog> countList() {
		return blogDao.countList();
	}
	
	/**
	 * 分页查询博客
	 * @return
	 */
	@Override
	@Transactional
	public List<Blog> query(int pc, int ps) {
		return blogDao.query(pc,ps);
	}
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	@Override
	@Transactional
	public int getTotal() {
		return blogDao.getTotal();
	}
	
	/**
	 * 通过Id查找实体
	 * @param id
	 * @return
	 */
	@Override
	@Transactional
	public Blog findById(int id) {
		return blogDao.findById(id);
	}
	
	/**
	 * 更新博客信息
	 * @param blog
	 * @return
	 */
	@Override
	@Transactional
	public void update(Blog blog) {
		blogDao.update(blog);
	} 
	
	/**
	 * 添加博客信息
	 * @param blog
	 * @return
	 */
	@Override
	@Transactional
	public void add(Blog blog) {
		blogDao.add(blog);
	}
	
	/**
	 * 删除博客信息
	 * @param id
	 * @return
	 */
	@Override
	@Transactional
	public void delete(Blog blog) {
	   blogDao.delete(blog);
	}
	
	/**
	 * 查询指定的博客类别下的博客数量,
	 * @param typeId
	 * @return
	 */
	@Override
	@Transactional
	public Blog_Type getBlogByTypeId(Integer type_id) {
		return blogDao.getBlogByTypeId(type_id);
	}

	@Override
	public int count(int id) {
		// TODO Auto-generated method stub
		return blogDao.count(id);
	}

	@Override
	public List<Blog> findByTime(Date time) {
		// TODO Auto-generated method stub
		return blogDao.findListByTime(time);
	}

	@Override
	public List<Blog> findByid(int id) {
		// TODO Auto-generated method stub
		return blogDao.findListById(id);
	}

	@Override
	public PageBean<Blog> query1(int pc, int ps) {
		// TODO Auto-generated method stub
		return blogDao.query1(pc,ps);
	}
	@Override
	public int findCountByTypeID(int type_id) {
		// TODO Auto-generated method stub
		return blogDao.findCountByTypeID(type_id);
	}


}
