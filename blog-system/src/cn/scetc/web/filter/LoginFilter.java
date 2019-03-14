package cn.scetc.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.scetc.web.entity.Blogger;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/admin/main.jsp")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res=(HttpServletResponse)response;
		HttpServletRequest req=(HttpServletRequest)request;
		
		Blogger blogger=(Blogger)req.getSession().getAttribute("blogger_session");
		
		if(blogger!=null) {			
				chain.doFilter(request, response);			
		}else {
			res.sendRedirect("../Login/AdminLogin.html");
		}		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
