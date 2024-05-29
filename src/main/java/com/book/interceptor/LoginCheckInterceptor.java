package com.book.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.book.utils.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("preHandle run");
		String url = request.getRequestURI().toString();
		System.out.println(url);
		
		if(url.contains("login")|| url.contains("regist")||url.contains("Image")) {
			return true;
			
		}
		HttpSession session = request.getSession();
		String jwt=null;
		if(session.getAttribute("token")!=null) {
			 jwt = session.getAttribute("token").toString();
		}
		if(!StringUtils.hasLength(jwt)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}
		try {
			JwtUtils.parseJWT(jwt);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}
		jwt = request.getSession().getAttribute("token").toString();
		return true;
			//HandlerInterceptor.super.preHandle(request, response, handler);
	
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		
		//HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
		System.out.println("postHandle run");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		//HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
		System.out.println("afterCompletion run");
	}


}
