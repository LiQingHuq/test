package cn.scetc.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.scetc.web.dao.CommentDaoI;
import cn.scetc.web.entity.Blog;
import cn.scetc.web.entity.Blog_Type;
import cn.scetc.web.entity.Comment;
import cn.scetc.web.entity.PageBean;

@Repository
public class CommentDaoImpl implements CommentDaoI {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 添加评论
	 * @param comment
	 * @return
	 */
	@Override
	public int add(Comment comment) {
		// TODO Auto-generated method stub
		return (Integer) hibernateTemplate.save(comment);
	}
	
	/**
	 * 修改评论
	 * @param comment
	 * @return
	 */
	@Override
	public void update(Comment comment) {
		// TODO Auto-generated method stub
       hibernateTemplate.update(comment);
	}

	/**
	 * 分页查询用户评论信息，
	 * @param map
	 * @return
	 */
	public List<Comment> query(int pc, int ps) {
		// TODO Auto-generated method stub
		PageBean<Comment> pb=new PageBean<Comment>();
		pb.setPc(pc);
		pb.setPs(ps);
		int totalRecord=getTotal();
		pb.setTr(totalRecord);
		pb.setTp(pb.getTp());
	       //设置显示到页面的数据的集合
		int begin=(pc-1)*ps;
		
		  String hql="from Comment";
		  List<Comment> beanlist=(List<Comment>)hibernateTemplate.executeFind(new HibernateCallback() {

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
		String hql="select count(*) from Comment";
		return ((Long)hibernateTemplate.iterate(hql).next()).intValue();
	}

	/**
	 * 删除评论信息
	 * @param id
	 * @return
	 */
	@Override
	public void delete(Comment comment) {
		// TODO Auto-generated method stub
		hibernateTemplate.delete(comment);;
	}
	/**
	 * 通过id查询博客类型，返回博客对象
	 * @param id
	 * @return
	 */
	@Override
	public Comment findById(int id) {
		// TODO Auto-generated method stub
		return hibernateTemplate.get(Comment.class, id);
	}

	@Override
	public List<Comment> countList(int id) {
		// TODO Auto-generated method stub
		List<Comment> comment=hibernateTemplate.find("from Comment where blog_id=?",id);
		return comment;
	}

}
