package cn.scetc.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.scetc.web.dao.Blog_TypeDaoI;
import cn.scetc.web.entity.Blog_Type;
import cn.scetc.web.entity.PageBean;
import cn.scetc.web.service.Blog_TypeServiceI;

@Service(value="blog_TypeServiceImpl")//<bean id="blog_TypeServiceImpl" class="cn.scetc.web.service.impl.Blog_TypeServiceImpl"/>
public class Blog_TypeServiceImpl implements Blog_TypeServiceI {
	@Autowired 
	private Blog_TypeDaoI blog_TypeDaoI;//<property id=blogDao ref="blogDaoImpl"/>

	/**
	 * 查询所有博客类型 以及对应的博客数量
	 * @return
	 */
	@Override
	@Transactional
	public List<Blog_Type> countList() {
		// TODO Auto-generated method stub
		return blog_TypeDaoI.countList();
	}
	
	/**
	 * 通过id查询博客类型
	 * @param id
	 * @return
	 */
	@Override
	@Transactional
	public Blog_Type findById(Integer id) {
		// TODO Auto-generated method stub
		return blog_TypeDaoI.findById(id);
	}

	/**
	 * 分页查询博客类别信息
	 * @param map
	 * @return
	 */
	@Override
	@Transactional
	public List<Blog_Type> query(int pc, int ps) {
		// TODO Auto-generated method stub
		return (List<Blog_Type>) blog_TypeDaoI.query( pc,  ps);
	}

	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	@Override
	@Transactional
	public int getTotal() {
		// TODO Auto-generated method stub
		return blog_TypeDaoI.getTotal();
	}

	/**
	 * 添加博客类别信息
	 * @param blogType
	 * @return
	 */
	@Override
	@Transactional
	public void add(Blog_Type blogType) {
		// TODO Auto-generated method stub
		blog_TypeDaoI.add(blogType);
	}

	/**
	 * 修改博客类别信息
	 * @param blogType
	 * @return
	 */
	@Override
	@Transactional
	public void update(Blog_Type blogType) {
		// TODO Auto-generated method stub
		blog_TypeDaoI.update(blogType);
	}

	/**
	 * 删除博客类别信息
	 * @param id
	 * @return
	 */
	@Override
	public void delete(Blog_Type blogType) {
		// TODO Auto-generated method stub
		blog_TypeDaoI.delete(blogType);
	}

}
