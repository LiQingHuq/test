package cn.scetc.web.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.scetc.web.entity.Blogger;
import cn.scetc.web.hibernateutils.ResponseUtil;
import cn.scetc.web.service.Blog_TypeServiceI;
import cn.scetc.web.service.BloggerServiceI;

@Namespace("/")
@Results({
	@Result(name="LoginSuccess",type="redirect",location="/admin/main.jsp"),
	@Result(name="LoginFailed",type="redirect",location="/Login/AdminLogin.html"),
	@Result(name="info",type="redirect",location="/foreground/blogger/info.jsp"),
})
public class BloggerAction extends ActionSupport implements ModelDriven<Blogger> {
    private Blogger blogger;
     @Autowired
    private BloggerServiceI bloggerServiceI;
     @Autowired
     private Blog_TypeServiceI blog_TypeServiceI; 
	@Override
	public Blogger getModel() {
		// TODO Auto-generated method stub
		return blogger;
	}
	/**
	 * 查询博主
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws Exception 
	 */
	@Action("/blogger")
    public String find() throws ServletException, IOException, Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
    	Blogger b=bloggerServiceI.find();    	
    	request.setAttribute("blogger", b);
    	return "info";
    }

    /**
     * 修改博主信息
     * @throws Exception 
     * 
     */
	
    public String update() throws Exception {
    	
    		
    		    	
    		/*
        	 * 0.获取reques对象
        	 * 1.查询博主
        	 * 2.将返回的信息存放到域对象中
        	 */
    	     
          	HttpServletRequest request=ServletActionContext.getRequest();
          	          	
        	String  blogger_username=request.getParameter("blogger_username");
        	String blogger_nickname=request.getParameter("blogger_nickname");
        	String blogger_sign=request.getParameter("blogger_sign");
        	String imageFile=request.getParameter("blogger_sign");
        	String blogger_context=request.getParameter("blogger_context");
        	Blogger blogger1=bloggerServiceI.find();
        	
        	blogger1.setBlogger_context(blogger_context);
        	blogger1.setBlogger_id(1);        	
        	blogger1.setBlogger_sign(blogger_sign);
        	blogger1.setBlogger_name(blogger_username);
        	blogger1.setBlogger_nickname(blogger_nickname);
        	/*
    		 * 工厂
    		 */
               DiskFileItemFactory diskFileItemFactory=new DiskFileItemFactory();
            /*
             * 解析器
             */
               ServletFileUpload sf=new ServletFileUpload(diskFileItemFactory);        
             	/*
            	 * 解析器解析request
            	 */
    			List<FileItem> fi=sf.parseRequest(request);
    			FileItem f0=fi.get(0);
    			/*
    			 * 遍历			
    			 */
    			  File file=new File("F:\\blog_system\\blog-system\\WebContent\\jswork\\images\\2.jpg");
    			  f0.write(file);
    			  		    
    		    JSONObject result=new JSONObject();    			
    			bloggerServiceI.update(blogger1);		   
    	 		result.put("result", true);
    	 	//	ResponseUtil.write(ServletActionContext.getResponse(), result);
    		
          return "info"; 
    }
    
    /**
     * 查询博主用户名和密码用于登陆
     */
    public String login() {
    	/*
    	 * 1.得到request对象
    	 * 2.request获取传入参数
    	 * 3.调用方法
    	 * 4.request域获取session域对象
    	 * 5.将结果放在session域中
    	 */
    	HttpServletRequest request=ServletActionContext.getRequest();
    	HttpSession session=request.getSession();
    	Map< String, String> error=new HashMap<String, String>();
    	String username=request.getParameter("username");
    	String password=request.getParameter("password");
    	if(bloggerServiceI.getByUserName(username,password)!=null) {    	
    		        Blogger blogger_session=bloggerServiceI.getByUserName(username,password);
    	            session.setAttribute("blogger_session", blogger_session);
    	                	             	          
    	    return "LoginSuccess";    	
    	}else if(bloggerServiceI.getByUserName(username,password)==null){
    		error.put("error", "用户名或密码错误重新登陆");
    		request.setAttribute("loginFiled",error );    		
    		return "LoginFailed";
    	}
		return "LoginFailed";
    	
    }
    /**
     * 退出管理系统
     * @return
     */
    public String layout() {    	
    	return "LoginFailed";
    }
    /**
     * 修改密码
     * @return
     * @throws Exception
     */
    public String updatePassword() throws Exception {
    	HttpServletRequest request=ServletActionContext.getRequest();
		int id=1;
      String passsword= request.getParameter("newPassword");
	    Blogger blogger1=bloggerServiceI.find();	
	
		blogger1.setBlogger_password(passsword);
		 JSONObject result=new JSONObject();
		if(blogger1!=null) {			
			bloggerServiceI.update(blogger1);		   
	 		result.put("result", true);
	 		ResponseUtil.write(ServletActionContext.getResponse(), result);
		}
    	return NONE;
    }
}
