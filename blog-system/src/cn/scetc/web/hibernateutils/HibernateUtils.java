package cn.scetc.web.hibernateutils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static Configuration configuration=null;
	private static SessionFactory factory=null;
  static {
	  configuration=new Configuration();
	  configuration.configure();
	  factory=configuration.buildSessionFactory();	  
  }
  public static SessionFactory getSessionFactory() {
	  return factory;
  }
  public static Session getSession() 
  {
	return ((SessionFactory) factory).getCurrentSession();
	  
  }  
  public static void main(String[] args) {
	
}
}
