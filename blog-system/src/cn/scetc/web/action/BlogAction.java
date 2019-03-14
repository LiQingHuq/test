package cn.scetc.web.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.scetc.web.entity.Blog;
import cn.scetc.web.entity.Blog2;
import cn.scetc.web.entity.Blog_Type;
import cn.scetc.web.entity.Blog_Type2;
import cn.scetc.web.entity.Blogger;
import cn.scetc.web.entity.Comment;
import cn.scetc.web.entity.PageBean;
import cn.scetc.web.hibernateutils.ResponseUtil;
import cn.scetc.web.service.BlogServiceI;
import cn.scetc.web.service.Blog_TypeServiceI;
import cn.scetc.web.service.BloggerServiceI;
import cn.scetc.web.service.CommentServiceI;

@Namespace("/")
@Results({
	@Result(name="writeBlog",location="/admin/writeBlog.jsp"),
	@Result(name="list",location="/foreground/blog/list.jsp"),
	@Result(name="view",location="/foreground/blog/view.jsp"),
	@Result(name="userMain",location="/userMain.jsp"),
	@Result(name="modifyBlog",location="/admin/modifyBlog.jsp"),
	@Result(name="list2",location="/foreground/blog/list2.jsp"),
})
public class BlogAction extends ActionSupport implements ModelDriven<Blog> {
    
	 private Blog blog;
	 @Autowired
	 private BlogServiceI blogServiceI;
	 @Autowired
	 private Blog_TypeServiceI blog_TypeServiceI;
	 @Autowired
	 private CommentServiceI commentServiceI;
	  @Autowired
	 private BloggerServiceI bloggerServiceI;
	 @Override
	 public Blog getModel() {
		// TODO Auto-generated method stub
		return blog;
	}	
	
	/**
	 * 添加博客，写博客
	 * @return
	 * @throws Exception 
	 */
	 @Action("/blog")
	public String add() throws Exception {		
	   HttpServletRequest request=ServletActionContext.getRequest();
		
	   Blog blog1=new Blog();
		
	   String blog_title=request.getParameter("blog_title");
	   String  blog_context=request.getParameter("blog_content");
	   String blog_summary=request.getParameter("blog_summary");
	   int type_id=Integer.parseInt(request.getParameter("type_id"));
	   int blog_click=0;
	   Date blog_time=new Date();
	   
	   blog1.setBlog_title(blog_title);
	   blog1.setBlog_Type(blog_TypeServiceI.findById(type_id));
	   blog1.setBlog_context(blog_context);
	   blog1.setBlog_summary(blog_summary);
	   blog1.setBlog_click(blog_click);
	   blog1.setBlog_time(blog_time);
	   
	   if(blog1!=null) {
        blogServiceI.add(blog1);
        JSONObject result=new JSONObject();
        result.put("result", true);
        ResponseUtil.write(ServletActionContext.getResponse(), result);
	   }
		return NONE;
	}

	
	
	/**
	 * 修改博客
	 * @param blog
	 * @return
	 * @throws Exception 
	 */
     public String update() throws Exception {
    	 HttpServletRequest request=ServletActionContext.getRequest();
 		
  	   Blog blog1=new Blog();
  		
  	   String  blog_ids=request.getParameter("id");
  	 Pattern pattern = Pattern.compile("[0-9]*"); 
     Matcher isNum = pattern.matcher(blog_ids);
     if( !isNum.matches() ){
         return null; 
     } 
       int blog_id=Integer.parseInt(blog_ids);
 
  	   String blog_title=request.getParameter("blog_title");
  	   String  blog_context=request.getParameter("blog_content");
  	   String blog_summary=request.getParameter("blog_summary");
  	   int type_id=Integer.parseInt(request.getParameter("type_id"));
  	   int blog_click=0;
  	   
  	   Date blog_time=new Date();
  	   
  	   blog1.setBlog_id(blog_id);
  	   blog1.setBlog_title(blog_title);
  	   blog1.setBlog_Type(blog_TypeServiceI.findById(type_id));
  	   blog1.setBlog_context(blog_context);
  	   blog1.setBlog_summary(blog_summary);
  	   blog1.setBlog_click(blog_click);
  	   blog1.setBlog_time(blog_time);
  	   
  	   if(blog1!=null) {
          blogServiceI.update(blog1);
          JSONObject result=new JSONObject();
          result.put("result", blog1);
        
  	   }
  		return NONE;
     }
     /**
      * 删除博客
      * @param blog_id
      * @return
     * @throws Exception 
      */
     public String delete() throws Exception {
    	 HttpServletRequest request=ServletActionContext.getRequest();
         String blog_ids=request.getParameter("ids");
         String[] idsStr=blog_ids.split(",");
         for(int i=0;i<idsStr.length;i++) {        	        	
             blogServiceI.delete(blogServiceI.findById(Integer.parseInt(idsStr[i].trim())));
         }
        JSONObject result=new JSONObject();
            result.put("success", true);
 		ResponseUtil.write(ServletActionContext.getResponse(), result);
    	 return NONE;
     }
     
     /**
      * 查询博客
      * @return
      */
     public int getPC() {
 		/*
 		 * 1.得到当前页码pc，如果pc不存在，那么pc=1
 		 * 2.如果排除存在，转换成int类类型即可
 		 */
 		HttpServletRequest request=ServletActionContext.getRequest();
 		String value=request.getParameter("page");
 		if(value==null||value.isEmpty()) {
 			return 1;
 		}
 		return Integer.parseInt(value);
 	 }
 	public String findAll() throws Exception {		
 	    /*
 	     * 1.获取页面传递的pc当前页面页码
 	     * 2.给定ps的值
 	     * 3.使用ps和pc调用service方法，得到pageBean，保存到request域中
 	     * 4.转发到list.jsp
 	     */
 		JSONObject result=new JSONObject();
 		HttpServletRequest request=ServletActionContext.getRequest();
 		int pc=getPC();//得到pc
 		int ps=10;//给定每页显示数量ps
 		int size=blogServiceI.getTotal();
 		List<Blog> pb=blogServiceI.query(pc, ps);
 		if(pb!=null) {	 			//处理没有数据异常
 		List<Blog2> blog2s=new ArrayList<Blog2>(); 		 		 
 	    for(Blog blog : pb) { 		
		  Blog2 b2=new Blog2(blog.getBlog_id(),blog.getBlog_title(), blog.getBlog_context(), blog.getBlog_click(), blog.getBlog_summary(), blog.getBlog_time(),blog.getBlog_Type().getType_name());
          blog2s.add(b2);          
 	    } 	 		 	
 		result.put("rows", blog2s);
 	    result.put("total",size);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
 		}
 		return NONE;
 	}
            
      
      /**
       * 查询所有的指定博客数量 显示    
       * @return
     * @throws Exception 
       */
      
 	
      public String getBlogByTypeId() throws Exception {
    	  HttpServletRequest request=ServletActionContext.getRequest();
    	  String  blog_ids=request.getParameter("id");
    	  	 Pattern pattern = Pattern.compile("[0-9]*"); 
    	     Matcher isNum = pattern.matcher(blog_ids);
    	     if( !isNum.matches() ){
    	         return null; 
    	     } 
    	  int blog_id=Integer.parseInt(blog_ids);   	      	
  		  Blog b=blogServiceI.findById(blog_id);	  		  
  		  JSONObject result=new JSONObject();
  		  result.put("blog_content", b.getBlog_context());
  		  result.put("blog_title", b.getBlog_title());
  		  result.put("blog_summary", b.getBlog_summary());
  		  result.put("type_id", b.getBlog_Type().getType_name());
  		  request.setAttribute("blog", result);
  		  ResponseUtil.write(ServletActionContext.getResponse(), result);
   	      return null;
  		 // return null;    	      	      	  
      }
      
      
      
      
    
          /**
           * 单一博客内容查询
           * @return
           */
      public String getBlogByTypeIdForId() {
    	  HttpServletRequest request=ServletActionContext.getRequest();
    	  
    	  Integer id=Integer.parseInt(request.getParameter("blog_id"));   
    	  
    	  List<Comment> commentList=commentServiceI.countList(id);    	  
  		  Blog b=blogServiceI.findById(id);	  
  		  int click=b.getBlog_click()+1;
  		  b.setBlog_click(click);
  	        blogServiceI.update(b);
  		  request.setAttribute("blog", b);
  		  request.setAttribute("commentList", commentList);
  		 
    	  return "view";
      }
      
      
      
      
      /**
       * 查询日期类型的集合
       * @return
     * @throws Exception 
       */
      public String getListByTypeIdForTime() throws Exception {
    	  HttpServletRequest request=ServletActionContext.getRequest();
    	  String str=request.getParameter("blog_time");
    	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	   ParsePosition pos = new ParsePosition(0);
    	 // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	   Date strtodate = formatter.parse(str, pos);    	    	   
    	   
  		  List<Blog> b=blogServiceI.findByTime(strtodate);	
  		  
  		if(b!=null) {	 			//处理没有数据异常
  	   		List<Blog2> blog2s=new ArrayList<Blog2>(); 		 		 
  	   	    for(Blog blog : b) { 		
  	  		  Blog2 b2=new Blog2(blog.getBlog_id(),blog.getBlog_title(), blog.getBlog_context(), blog.getBlog_click(), blog.getBlog_summary(), blog.getBlog_time(),blog.getBlog_Type().getType_name());
  	  		  blog2s.add(b2);          
  	   	    } 	   	   	      	   	   	   
  		  request.setAttribute("blogList1", blog2s);
  		}
    	  return "list2";
      }
      
      
      /**
       * 查询类型的集合
       * @return
       */
      public String getListByTypeIdForId() {
    	  HttpServletRequest request=ServletActionContext.getRequest();
    	  
    	  int id=Integer.parseInt(request.getParameter("type_id"));
    	  
  		  List<Blog> b=blogServiceI.findByid(id);	  		  
    		if(b!=null) {	 			//处理没有数据异常
    	   		List<Blog2> blog2s=new ArrayList<Blog2>(); 		 		 
    	   	    for(Blog blog : b) { 		
    	  		  Blog2 b2=new Blog2(blog.getBlog_id(),blog.getBlog_title(), blog.getBlog_context(), blog.getBlog_click(), blog.getBlog_summary(), blog.getBlog_time(),blog.getBlog_Type().getType_name());
    	  		  blog2s.add(b2);          
    	   	    } 	    	   	       
  		  request.setAttribute("blogList1", blog2s);
    		}
    	  return "list2";
      }
      
      
      
      
      /**
       * 下载
       * @return
       * @throws IOException
       */
      public String load() throws IOException {
    		String filename="F:/blog-system.rar";	
    		/*
    		 * 俩个头
    		 */

    		 String contentType = new MimetypesFileTypeMap().getContentType(filename);
    		//String contentType=this.getServletActionContext().getMimeType(filename);//通过文件名称获取mine类型
    		String contentDisposition ="attachment;filename=a.rar";
    		FileInputStream in=new  FileInputStream(filename);
    		HttpServletResponse response=ServletActionContext.getResponse();
    		/*
    		 * 设置响应头
    		 */
    		response.setHeader("Content-Type", contentType);
    		response.setHeader("Content-Disposition", contentDisposition);
    		/*
    		 * 设置流
    		 */
    		ServletOutputStream sot=response.getOutputStream();
    		IOUtils.copy(in, sot);
    		in.close();
    		return null;
      }
      
      /**
       * 页面数据显示老版本,,,,,,,,放弃
       * @return
       * @throws Exception
       */
      public String find() throws Exception {		    	    		
   		HttpServletRequest request=ServletActionContext.getRequest();   		   		
   		List<Blog> pb=blogServiceI.countList();
   		if(pb!=null) {	 			//处理没有数据异常
   		List<Blog2> blog2s=new ArrayList<Blog2>(); 		 		 
   	    for(Blog blog : pb) { 		
  		  Blog2 b2=new Blog2(blog.getBlog_id(),blog.getBlog_title(), blog.getBlog_context(), blog.getBlog_click(), blog.getBlog_summary(), blog.getBlog_time(),blog.getBlog_Type().getType_name());
  		// int count=blogServiceI.count(blog.getBlog_Type().getType_id());
  	    // request.setAttribute("id", count); 
  		  blog2s.add(b2);          
   	    } 	 
   	    
     	 List<Blog_Type> blog_TypeList=blog_TypeServiceI.countList();		   
     	 Blogger blogger_main=bloggerServiceI.find();         
           
   	     request.setAttribute("typeList", blog_TypeList);       	 	   	   
   	     request.setAttribute("blogger", blogger_main);
 		 request.setAttribute("blogList", blog2s);  
 		 request.setAttribute("blogList1", blog2s);  
 		    	
 		 //分页查询
// 		    int pc=getPC1();//得到pc
//   		    int ps=10;//给定每页显示数量ps
//    		PageBean<Blog> p=blogServiceI.query1(pc, ps);
//    		if(pb!=null) {	 			//处理没有数据异常
//    		List<Blog2> blog2s1=new ArrayList<Blog2>(); 		 		 
//    	    for(Blog blog : p.getBeanList()) { 		
//   		        Blog2 b3=new Blog2(blog.getBlog_id(),blog.getBlog_title(), blog.getBlog_context(), blog.getBlog_click(), blog.getBlog_summary(), blog.getBlog_time(),blog.getBlog_Type().getType_name());
//   		        blog2s1.add(b3);          
//    	      } 	 	
//    	    request.setAttribute("blogList1", blog2s1);  
//    	    request.setAttribute("page", p);  
//    		}
   		}
   		return "userMain";   		
   	}
      
      /**
       * pagebean查询新版本
       * @return
       * @throws Exception
       */
      public String findAll1() throws Exception {		
   	    /*
   	     * 1.获取页面传递的pc当前页面页码
   	     * 2.给定ps的值
   	     * 3.使用ps和pc调用service方法，得到pageBean，保存到request域中
   	     * 4.转发到list.jsp
   	     */
    	JSONObject result=new JSONObject();
   		HttpServletRequest request=ServletActionContext.getRequest();
   		int pc=this.getPC();//得到pc
   		int ps=10;//给定每页显示数量ps   		
   		int size=blogServiceI.getTotal();//一共有多少记录
   		int pageTotal=size/10;   		
   		
   		Map<Object, Object> m=new  HashMap<Object, Object>();
   		
   		if(size%10!=0) {
   			pageTotal=pageTotal+1;
   		}
   		m.put("pageTotal", pageTotal);
   		m.put("pc", pc);
   		List<Blog> pb=blogServiceI.query(pc, ps);
   		if(pb!=null) {	 			//处理没有数据异常
   			
   		List<Blog2> blog2s=new ArrayList<Blog2>(); 		 		 
   	    for(Blog blog : pb) { 		
  		  Blog2 b2=new Blog2(blog.getBlog_id(),blog.getBlog_title(), blog.getBlog_context(), blog.getBlog_click(), blog.getBlog_summary(), blog.getBlog_time(),blog.getBlog_Type().getType_name());
            blog2s.add(b2);          
   	    }
     	 List<Blog_Type> blog_TypeList=blog_TypeServiceI.countList();		   
 	     Blogger blogger_main=bloggerServiceI.find();   
 	 
 	
	     Blog_Type bt=new Blog_Type();
	     List<Blog> p=blogServiceI.countList();
	     List<Blog2> blog2=new ArrayList<Blog2>(); 	
	  
	   //  m.put("count", blogServiceI.count(p.listIterator().getBlog_Type().getType_id()));
	     //request.setAttribute("typeList", m);
	  	    for(Blog blog : p) { 		   	    
	  		  Blog2 b2=new Blog2(blog.getBlog_id(),blog.getBlog_title(), blog.getBlog_context(), blog.getBlog_click(), blog.getBlog_summary(), blog.getBlog_time(),blog.getBlog_Type().getType_name());	  	     	  	     	 	  	     	 	  		 	  		  	  		  	  		
	  		  blog2.add(b2);          
	   	    } 	 
	   	    
	   	  
	         request.setAttribute("typeList", blog_TypeList);   	         
		     request.setAttribute("blogger", blogger_main);
		     request.setAttribute("blogList1", blog2s);
		     request.setAttribute("blogList", blog2); 
   	    
   		
   		request.setAttribute("pageSize", m);
   		
  		//ResponseUtil.write(ServletActionContext.getResponse(), result);
   		
   		}
   		
   		return "userMain"; 		
   	} 
      
      
      /**
       * 
       */
}
