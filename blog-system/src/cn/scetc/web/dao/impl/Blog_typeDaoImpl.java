package cn.scetc.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.scetc.web.dao.Blog_TypeDaoI;
import cn.scetc.web.entity.Blog_Type;
import cn.scetc.web.entity.PageBean;

@Repository
public class Blog_typeDaoImpl implements Blog_TypeDaoI {

	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * 查询所有博客类型 以及对应的博客数量，返回博客集合
	 * @return
	 */
	@Override
	public List<Blog_Type> countList() {
		// TODO Auto-generated method stub
		List<Blog_Type> blog_Types=hibernateTemplate.find("from Blog_Type");
		return blog_Types;
	}

	/**
	 * 通过id查询博客类型，返回博客对象
	 * @param id
	 * @return
	 */
	@Override
	public Blog_Type findById(Integer id) {
		// TODO Auto-generated method stub
		return hibernateTemplate.get(Blog_Type.class, id);
	}

	/**
	 * 分页查询博客类别信息，
	 * @param map
	 * @return
	 */
	public List<Blog_Type> query(int pc, int ps) {
	
		PageBean<Blog_Type> pb=new PageBean<Blog_Type>();
		pb.setPc(pc);
		pb.setPs(ps);
		int totalRecord=getTotal();
		pb.setTr(totalRecord);
		pb.setTp(pb.getTp());
	       //设置显示到页面的数据的集合
		int begin=(pc-1)*ps;
		
		  String hql="from Blog_Type";
		  List<Blog_Type> beanlist=(List<Blog_Type>)hibernateTemplate.executeFind(new HibernateCallback() {

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
		// TODO Auto-generated method stub
		String hql="select count(*) from Blog_Type";
		return ((Long) hibernateTemplate.iterate(hql).next()).intValue();
	}


	/**
	 * 添加博客类别信息，没有返回参数
	 * @param blogType
	 * @return
	 */
	@Override
	public void add(Blog_Type blogType) {
		// TODO Auto-generated method stub
       hibernateTemplate.save(blogType);
	}

	/**
	 * 修改博客类别信息
	 * @param blogType
	 * @return
	 */
	@Override
	public void update(Blog_Type blogType) {
		// TODO Auto-generated method stub
		hibernateTemplate.update(blogType);
	}

	/**
	 * 删除博客类别信息
	 * @param id
	 * @return
	 */
	@Override
	public void delete(Blog_Type blogType) {
		// TODO Auto-generated method stub
		hibernateTemplate.delete(blogType);
	}

}
