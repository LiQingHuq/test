package cn.scetc.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.scetc.web.dao.BloggerDaoI;
import cn.scetc.web.entity.Blogger;
import cn.scetc.web.service.BloggerServiceI;

@Service(value="bloggerServiceImpl")//<bean id="bloggerServiceImpl" class="cn.scetc.web.service.impl.BloggerServiceImpl"/>
public class BloggerServiceImpl implements BloggerServiceI {

	@Autowired 
	private BloggerDaoI bloggerDao;//<property id=blogDao ref="blogDaoImpl"/>

	
	/**
	 * 查询博主信息
	 * @return
	 */
	@Override
	@Transactional
	public Blogger find() {
		// TODO Auto-generated method stub
		return bloggerDao.find();
	}

	/**
	 * 通过用户名查询用户
	 * @param userName
	 * @return
	 */
	@Override
	@Transactional
	public Blogger getByUserName(String userName,String password) {
		// TODO Auto-generated method stub
		return bloggerDao.getByUserName(userName,password);
	}

	/**
	 * 更新博主信息
	 * @param blogger
	 * @return
	 */
	@Override
	@Transactional
	public void update(Blogger blogger) {
		// TODO Auto-generated method stub
		bloggerDao.update(blogger);
	}

}
