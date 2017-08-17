package cn.com.bestv.channeladmin.servlet;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.com.bestv.channeladmin.service.UserService;
import cn.com.bestv.infrastructure.common.IResultInfo;
import cn.com.bestv.infrastructure.common.ResultInfo;
import cn.com.bestv.utility.StringUtils;

/** 
 * @author Vem
 * @date 创建时间：2017年8月16日 下午5:41:40 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
@WebServlet(name="UserServlet", urlPatterns = { "/user/*"},loadOnStartup=1)
public class UserServlet {
	
	private static final Logger logger = Logger.getLogger(UserService.class);
	
	public IResultInfo<Map<String, Object>> loginAction(String [] strPathParameters,HttpServletRequest request,HttpServletResponse response ){
		IResultInfo<Map<String, Object>> ri	=null;
		UserService userService=UserService.getInstance();
		
     	String strUsername=request.getParameter("username");
     	String strPassword=request.getParameter("password");
     	
     	if (!StringUtils.hasText(strUsername) || !StringUtils.hasText(strPassword)){
     		ri=new ResultInfo<Map<String,Object>>("failure",null,"无效的数据");
     	}		
     	
     	ri=(IResultInfo<Map<String,Object>>)userService.login(strUsername, strPassword);
     	
     	HttpSession session=null;
     	Cookie cookie=null;
     	
     	//将管理员信息保存到Session 和Cookie
     	if ("success"==ri.getBusinessCode()){
     		
     		session=request.getSession();
     		logger.info("Login SessionId:"+session.getId());
     		
     		session.setAttribute("me", ri.getResultSet().get(0));
     		
     		cookie = new Cookie("me", ri.getResultSet().get(0).get("id").toString());  
            cookie.setPath("/");  
            cookie.setMaxAge(24*60*60);//1天  
            response.addCookie(cookie);  
     	}
     	
     	return ri;
    }
}
