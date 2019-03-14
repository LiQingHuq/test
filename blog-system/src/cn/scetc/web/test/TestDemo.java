package cn.scetc.web.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.scetc.web.entity.Blogger;
import cn.scetc.web.hibernateutils.HibernateUtils;
import cn.scetc.web.service.impl.BloggerServiceImpl;

public class TestDemo {
	
	     @Test
		 public void fun() {
			 SessionFactory sessionFactory=null;
			 Session session=null;
			 Transaction transaction=null;
			 try {
				 sessionFactory= HibernateUtils.getSessionFactory();
				 session=sessionFactory.openSession();
				  transaction=session.beginTransaction();
				  		  	  		  	   	
				  Blogger b=new Blogger();
				  b.setBlogger_context("李青华爱李承敏");
				  b.setBlogger_name("liSi");
				  b.setBlogger_nickname("小咖");
				  b.setBlogger_password("123");
				  b.setBlogger_sign("fighting");
				  
				  session.save(b);
		  	   	  		  	   	 
				transaction.commit();
			 }catch(Exception e) {
				 transaction.rollback();
				 throw new RuntimeException(e);
			 }finally {
				 session.close();
			}			
	
		 }
	 
	 @Test
	 public void fun1() {
		 
		 BloggerServiceImpl sb=new BloggerServiceImpl();		
		 Blogger blogger= sb.find();
		 System.out.println(blogger);
		 	
	 }
	 
}

