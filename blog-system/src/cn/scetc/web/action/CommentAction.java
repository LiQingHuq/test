package cn.scetc.web.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import cn.scetc.web.entity.Comment;
import cn.scetc.web.hibernateutils.ResponseUtil;
import cn.scetc.web.service.CommentServiceI;

@Namespace("/")
@Results({
	@Result(name="Success",type="redirect",location="/suc.jsp"),
	@Result(name="fail",type="redirect",location="/test.jsp")
})
public class CommentAction extends ActionSupport implements ModelDriven<Comment>{
	
	@Autowired
	private CommentServiceI commentServiceI;
	 
	private Comment comment;
	@Override
	public Comment getModel() {
		// TODO Auto-generated method stub
		return comment;
	}
	/**
	 * 添加评论方法
	 * @return
	 * @throws Exception 
	 */
	@Action("/comment")
	public String add() throws Exception {
		
		HttpServletResponse response=ServletActionContext.getResponse();	
		HttpServletRequest request=ServletActionContext.getRequest();	
		
		int id=Integer.parseInt(request.getParameter("blog_id"));	
		String comment_text=request.getParameter("comment_content");
		 Map< String, String> error=new HashMap<String, String>();		 
		
		String verfycode=request.getParameter("verfycode");
		  String yzmform=(String) request.getSession().getAttribute("text");
		  if(comment_text==null||comment_text.trim().isEmpty()){
	           error.put("verfycode", "请输入评论内容");
	          }
          if(!verfycode.equalsIgnoreCase(yzmform)){
       	   error.put("verfycode", "验证码错误，请重新输入！");
          }
          request.setAttribute("error", error);   
      	  request.setAttribute("id", id); 
          if(error!=null&&error.size()>0){
         	 request.getRequestDispatcher("/test.jsp").forward(request, response);    
         	 return null;
          }
          
			//获取ip
          String ip = request.getHeader("x-forwarded-for");
          if(null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
        	 ip = request.getHeader("Proxy-Client-IP");
        	  }
if(null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
        	  ip = request.getHeader("WL-Proxy-Client-IP");
        	  }
if(null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
        	  ip = request.getHeader("X-Real-IP");
        	  }
if(null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
        	  ip = request.getRemoteAddr();
        	  }
        	
	    Date time=new Date();
	    
		   Comment co=new Comment();	
		   
		   co.setBlog_id(id);
		   co.setComment_context(comment_text);
		   co.setComment_ip(ip);
		   co.setComment_time(time);
		   					
			System.out.println("你好blog_type："+	co.getComment_context());
			System.out.println("你好blog_type："+	co.getComment_ip());
			
		// JSONObject result=new JSONObject();		 
		if(co!=null) {
			commentServiceI.add(co);		   
	 	//	result.put("result", true);
	 	//	ResponseUtil.write(ServletActionContext.getResponse(), result);
     	}
		 request.setAttribute("id", id);
		 request.getRequestDispatcher("/suc.jsp").forward(request, response);
      return null; 
	}
	
	
	/**
	 * 删除评论的方法
	 * @return
	 * @throws Exception 
	 */
	public String delete() throws Exception {
		/*
		 * 1.获取request对象,ServletActionContext是ActionContext子类
		 * 2.获取传入id
		 * 3.service.delete(id)
		 */
		HttpServletRequest request=ServletActionContext.getRequest();
		String comment_ids=request.getParameter("ids");
		String[] ids=comment_ids.split(",");
		for(int i=0;i<ids.length;i++) {
	      	commentServiceI.delete(commentServiceI.findById(Integer.parseInt(ids[i].trim())));
	 	}
		  JSONObject result=new JSONObject();
		  result.put("success", true);
		   ResponseUtil.write(ServletActionContext.getResponse(), result);
		return NONE;
	}
	
	/**
     * 查询评论
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
		HttpServletRequest request=ServletActionContext.getRequest();
		int pc=getPC();//得到pc
		int ps=10;//给定每页显示数量ps
		int size=commentServiceI.getTotal();
		List<Comment> pb=commentServiceI.query(pc, ps);
	if(pb!=null) {
		JSONObject result=new JSONObject();
		result.put("rows",pb);
		result.put("total",size);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
	}
		return NONE;
	}
           
	/**
	 * 图片生成
	 * @return
	 * @throws Exception
	 */
     public String ImgCode() throws Exception {
    	 HttpServletRequest request=ServletActionContext.getRequest();
    	 HttpServletResponse response=ServletActionContext.getResponse();
    	 //c创建图片验证类
 		ImgCode ic=new ImgCode();
 		//得到图片类
 	    BufferedImage bi=ic.getImage();//跑死我了
 	    //把得到的验证码文本存放到session域中
 		request.getSession().setAttribute("text",ic.getText());
 		//把图片通过流的方式，响应给客户端
 		ImgCode.output(bi, response.getOutputStream());
 		return null;
     }
     
     public String find() throws Exception {		    	    		
    		HttpServletRequest request=ServletActionContext.getRequest(); 
    		int id=Integer.parseInt(request.getParameter("blog_id"));
    		List<Comment> pb=commentServiceI.countList(id);
    		if(pb!=null) {	 			//处理没有数据异常    		
    		request.setAttribute("commentList", pb);      	 		
    		}
    		return NONE;
    		
    	}
}
