package cn.scetc.web.action;

import java.util.ArrayList;
import java.util.List;

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
import cn.scetc.web.entity.Blog_Type;
import cn.scetc.web.entity.Blog_Type2;
import cn.scetc.web.hibernateutils.ResponseUtil;
import cn.scetc.web.service.Blog_TypeServiceI;
@Namespace("/")
@Results({
	@Result(name="modify",location="/admin/modifyBlog.jsp"),
})
public class Blog_TypeAction extends ActionSupport{
   
	 private Blog_Type blog_Type;
	 
	public Blog_Type getBlog_Type() {
		return blog_Type;
	}
	public void setBlog_Type(Blog_Type blog_Type) {
		this.blog_Type = blog_Type;
	}
	
	@Autowired
	 private Blog_TypeServiceI blog_TypeServiceI;
	 
	
	public String blog_TypeList() {
		
		return NONE;
	}
	/**
	 * 删除博客类型
	 * @return
	 * @throws Exception 
	 */
	
	@Action("/blog_Type")
	public String delete() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		String blog_type_ids=request.getParameter("ids");
		String[] ids=blog_type_ids.split(",");
		   for(int i=0;i<ids.length;i++) {
			   blog_TypeServiceI.delete(blog_TypeServiceI.findById(Integer.parseInt(ids[i].trim())));
	         }
		   JSONObject jsonObject=new JSONObject();
		   jsonObject.put("success", true);
		   ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		   return NONE;
	}

	
	/**
	 * 修改博客类别信息
	 * @param blogType
	 * @return
	 * @throws Exception 
	 */
	public String update() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		int id=Integer.parseInt(request.getParameter("type_id"));
		String name=request.getParameter("type_name");
		int num=Integer.parseInt(request.getParameter("orderNum"));		
	    Blog_Type blog_Type1=new Blog_Type();	
	    
		blog_Type1.setType_id(id);
		blog_Type1.setType_name(name);
		blog_Type1.setOrderNum(num);	
		
		System.out.println("你好blog_type："+	blog_Type1.getType_id());		
		System.out.println("你好blog_type："+	blog_Type1.getType_name());
		System.out.println("你好blog_type："+	blog_Type1.getOrderNum());

		 JSONObject result=new JSONObject();
		if(blog_Type1!=null) {			
			blog_TypeServiceI.update(blog_Type1);		   
	 		result.put("result", true);
	 		ResponseUtil.write(ServletActionContext.getResponse(), result);
		}
      return NONE; 
	}
	/**
	 * 添加评论
	 * @param blogType
	 * @return
	 * @throws Exception 
	 */
	public String add() throws Exception{
		
		HttpServletRequest request=ServletActionContext.getRequest();		
		String name=request.getParameter("type_name");
		int num=Integer.parseInt(request.getParameter("orderNum"));		
		   Blog_Type blog_Type1=new Blog_Type();						
			blog_Type1.setType_name(name);
			blog_Type1.setOrderNum(num);						
			System.out.println("你好blog_type："+	blog_Type1.getType_name());
			System.out.println("你好blog_type："+	blog_Type1.getOrderNum());
		 JSONObject result=new JSONObject();		 
		if(blog_Type1!=null) {
			blog_TypeServiceI.add(blog_Type1);		   
	 		result.put("result", true);
	 		ResponseUtil.write(ServletActionContext.getResponse(), result);
	 		
     	}
      return NONE; 
	}

	
	
	
	/**
	 * 通过id查询博客类型
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public String  findById() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		Integer blog_type_id=Integer.parseInt(request.getParameter("blog_type_id"));
		Blog_Type bt=blog_TypeServiceI.findById(blog_type_id);
		request.setAttribute("Blog_Type", bt.getType_name());
		ResponseUtil.write(ServletActionContext.getResponse(), bt);
		return NONE;
	}
	
	/**
	 * 查询添加博客类型集合
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public String findList() throws Exception {			
		HttpServletRequest request=ServletActionContext.getRequest();		
		   List<Blog_Type> blog_TypeList=blog_TypeServiceI.countList();
           request.setAttribute("typeList", blog_TypeList);                  
           HttpServletResponse response=ServletActionContext.getResponse();
        //  ResponseUtil.write(response, request);
	    //	request.getRequestDispatcher("/admin/writeBlog.jsp").forward(request, response);	
         //  ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	/**
	 * 博客修改内容
	 * @return
	 * @throws Exception
	 */
	public String findList1() throws Exception {			
		HttpServletRequest request=ServletActionContext.getRequest();		
		   List<Blog_Type> blog_TypeList=blog_TypeServiceI.countList();
           request.setAttribute("typeList", blog_TypeList);                  
         //  HttpServletResponse response=ServletActionContext.getResponse();
        //  ResponseUtil.write(response, request);
//request.getRequestDispatcher("/admin/modifyBlog.jsp").forward(request, response);		            
		return null;
	}
	
	
	
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
		// TODO Auto-generated method stub
		
	    /*
	     * 1.获取页面传递的pc当前页面页码
	     * 2.给定ps的值
	     * 3.使用ps和pc调用service方法，得到pageBean，保存到request域中
	     * 4.转发到list.jsp
	     */
		HttpServletRequest request=ServletActionContext.getRequest();
		JSONObject result=new JSONObject();
		int pc=getPC();//得到pc
		int ps=10;//给定每页显示数量ps
		int size=blog_TypeServiceI.getTotal();
		List<Blog_Type> pb=blog_TypeServiceI.query(pc, ps);
		if(pb!=null) {	//处理没有数据的异常
		List<Blog_Type2> bt2=new ArrayList<Blog_Type2>();
		
		for (Blog_Type bt : pb) {
			Blog_Type2 blog_Type2=new Blog_Type2(bt.getType_id(), bt.getType_name(), bt.getOrderNum());
			bt2.add(blog_Type2);
		}		
			result.put("rows", bt2);
			result.put("total",size);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		}
		return NONE;
	}
	
	/**
	 * 通过查询博客类型集合
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public String find() throws Exception {			
		HttpServletRequest request=ServletActionContext.getRequest();		
		   List<Blog_Type> blog_TypeList=blog_TypeServiceI.countList();		   
           request.setAttribute("typeList", blog_TypeList);                  
           HttpServletResponse response=ServletActionContext.getResponse();          
		return NONE;
	}
	
    
}
