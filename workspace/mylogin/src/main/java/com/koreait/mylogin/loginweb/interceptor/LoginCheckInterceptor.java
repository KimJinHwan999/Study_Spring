package com.koreait.mylogin.loginweb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.koreait.mylogin.loginweb.session.SessionConst;

public class LoginCheckInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String requestURI = request.getRequestURI();
		System.out.println("[interceptor] : " + requestURI);
		HttpSession session = request.getSession(false);
		
		if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
			System.out.println("미인증 사용자 요청");
			// 로그인으로 redirect
			response.sendRedirect("/login?redirectURL=" + requestURI);
			return false;
		}
		
		return true;
	}
}
