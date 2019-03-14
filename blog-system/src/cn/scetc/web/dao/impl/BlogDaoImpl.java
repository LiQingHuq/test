package cn.scetc.web.dao.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.scetc.web.dao.BlogDaoI;
import cn.scetc.web.entity.Blog;
import cn.scetc.web.entity.Blog_Type;
import cn.scetc.web.entity.PageBean;

@Repository
public class BlogDaoImpl implements BlogDaoI {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * 根据日期月份分组查询 - 月份-当年博客的数量
	 * @return
	 */
	@Override
	public List<Blog> countList() {
		// TODO Auto-generated method stub
		List<Blog> blogs=hibernateTemplate.find("from Blog");
		return blogs;
	}
	
	/**
	 * 分页查询博客
	 * @return
	 */
	@Override
	public List<Blog> query(int pc,int ps){
		// TODO Auto-generated method stub
				PageBean<Blog> pb=new PageBean<Blog>();
				pb.setPc(pc);//设置当前页码
				pb.setPs(ps);//当前每页显示数量
				
				int totalRecord=getTotal();//总记录数
				
				pb.setTr(totalRecord);//设置总记录数
				pb.setTp(pb.getTp());//设置一共有多少页码
			       //设置显示到页面的数据的集合
				
				int begin=(pc-1)*ps;
				
				  String hql="from Blog";
				  List<Blog> beanlist=(List<Blog>)hibernateTemplate.executeFind(new HibernateCallback() {

					@Override
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						//使用session对象
		                Query query = session.createQuery(hql);
		                query.setFirstResult(begin);
		                query.setMaxResults(ps);
		                return query.list();
					}
				  }
				  );
				  if(beanlist!=null && beanlist.size()>0){
						return beanlist;
					}else {
						return null;
					}
	}

	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	@Override
	public int getTotal() {
		String hql="select count(*) from Blog";
		return ((Long) hibernateTemplate.iterate(hql).next()).intValue();
	}
	
	
	
	
	/**
	 * 通过Id查找,返回博客的对象
	 * @param id
	 * @return
	 */
	@Override
	public Blog findById(int id) {
		return hibernateTemplate.get(Blog.class, id);
	}

	/**
	 * 更新博客信息
	 * @param blog
	 * @return
	 */
	@Override
	public void update(Blog blog) {
		// TODO Auto-generated method stub
		hibernateTemplate.update(blog);;
	}

	/**
	 * 添加博客信息
	 * @param blog
	 * @return
	 */
	@Override
	public void add(Blog blog) {
		// TODO Auto-generated method stub
	   hibernateTemplate.save(blog);
	}

	/**
	 * 删除博客信息
	 * @param id
	 * @return
	 */
	@Override
	public void delete(Blog blog) {
		// TODO Auto-generated method stub
		if(blog!=null)
		hibernateTemplate.delete(blog);
	}

	/**
	 * 查询指定的博客类别下的博客数量,通过type_id查询
	 * @param typeId
	 * @return
	 */
	@Override
	public Blog_Type getBlogByTypeId(Integer type_id) {
		// TODO Auto-generated method stub
		
		return hibernateTemplate.get(Blog_Type.class, type_id);
	}

	@Override
	public int count(int id) {
		String hql="select count(*) from Blog where type_id=?";
		return ((Long) hibernateTemplate.iterate(hql,id).next()).intValue();
	}

	@Override
	public List<Blog> findListByTime(Date time) {			
       String hql="from Blog where blog_time=?";
		return hibernateTemplate.find(hql,time);
	}
	@Override
	public List<Blog> findListById(int id) {
		 String hql="from Blog where type_id=?";
		return hibernateTemplate.find(hql,id);
	}

	@Override
	public PageBean<Blog> query1(int pc, int ps) {
		PageBean<Blog> pb=new PageBean<Blog>();
		pb.setPc(pc);//设置当前页码
		pb.setPs(ps);//当前每页显示数量
		
		int totalRecord=getTotal();//总记录数
		
		pb.setTr(totalRecord);//设置总记录数
		pb.setTp(pb.getTp());//设置一共有多少页码
	       //设置显示到页面的数据的集合
		
		int begin=(pc-1)*ps;
		
		  String hql="from Blog";
		  List<Blog> beanlist=(List<Blog>)hibernateTemplate.executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				//使用session对象
                Query query = session.createQuery(hql);
                query.setFirstResult(begin);
                query.setMaxResults(ps);
                return query.list();
			}
		  }
		  );
		  pb.setBeanList(beanlist);
		return pb;
			
}

	@Override
	public List<Blog> countTime() {
    String hql="FROM Blog group BY  DATE_FORMAT(blog_time,'%Y-%m-%d')='2017-12-15'";
		return hibernateTemplate.find(hql);
	}

	@Override
	public int findCountByTypeID(int type_id) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Blog where type_id=?";
		return ((Long) hibernateTemplate.iterate(hql,type_id).next()).intValue();
	}

}
