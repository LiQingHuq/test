package cn.scetc.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.scetc.web.dao.BloggerDaoI;
import cn.scetc.web.entity.Blogger;

@Repository
public class BloggerDaoImpl implements BloggerDaoI {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * 查询博主信息,博主信息只有一条，返回博客对象
	 * @return
	 */
	@Override
	public Blogger find() {
		// TODO Auto-generated method stub
		return (Blogger) hibernateTemplate.find("from Blogger").get(0);
	}

	/**
	 * 通过用户名查询用户
	 * @param userName
	 * @return
	 */
	@Override
	public Blogger getByUserName(String userName,String password) {
			List<Blogger> lb=hibernateTemplate.find("from Blogger where blogger_name=? and blogger_password=?",userName,password);		
           if(lb.size()>0)
			return (Blogger)lb.get(0);
           else {
        	   return null;
           }
	}

	/**
	 * 更新博主信息
	 * @param blogger
	 * @return
	 */
	@Override
	public void update(Blogger blogger) {
		// TODO Auto-generated method stub
		 hibernateTemplate.update(blogger);
		
	}

	@Override
	public void updatePassword(String passowrd) {
		// TODO Auto-generated method stub
		
	}	

}
