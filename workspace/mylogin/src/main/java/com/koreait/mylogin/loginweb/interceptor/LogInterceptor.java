package com.koreait.mylogin.loginweb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LogInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {	// 컨트롤러 호출된 이전

		String requestURI = request.getRequestURI();
		System.out.println("[interceptor] requestURI : " + requestURI);

		return true;		// false -> 진행 x
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception { // 컨트롤러가 호출된 이후

		System.out.println("[interceptor] postHandle");
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		System.out.println("[interceptor] afterCompletion");
		
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	


}
