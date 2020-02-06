package com.github.dge1992.commonserver.intercepter;

import com.alibaba.fastjson.JSON;
import com.github.dge1992.common.domain.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 董感恩
 * @date 2020-02-06 15:58:32
 * @desc 用户上下文拦截器
 **/
public class UserContextInterceptor extends HandlerInterceptorAdapter {
	    
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		//放过认证服务
		if (request.getServletPath().equals("/auth")) {
			return true;
		}
		User user = getUser(request);
		if(user == null || !verify(user,request)) {
			response.setHeader("Content-Type", "application/json");
			String jsonStr = JSON.toJSONString("no permission access service, please check");
			response.getWriter().write(jsonStr);
			response.getWriter().flush();
			response.getWriter().close();
			throw new PermissionException("no permission access service, please check");
		}
		UserContextHolder.set(user);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3){
		// DOING NOTHING
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3){
		UserContextHolder.remove();
	}
	
	private User getUser(HttpServletRequest request){
		String userStr = request.getHeader("x-user");
		return JSON.parseObject(userStr, User.class);
	}

	private boolean verify(User user, HttpServletRequest request) {
		String url = request.getHeader("x-user-serviceName");
		List<String> permissionServices = user.getPermissionServices();
		for (String permissionService:permissionServices) {
			if(permissionService.equals(url)){
				return true;
			}
		}
		return false;
	}
	
	static class PermissionException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		public PermissionException(String msg) {
	        super(msg);
	    }
	}
}
