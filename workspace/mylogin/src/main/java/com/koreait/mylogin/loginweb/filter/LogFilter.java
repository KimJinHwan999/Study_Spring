package com.koreait.mylogin.loginweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

// 1. javax 의 servlet 필터 가져오기
public class LogFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request; // 2. 형변환으로 클래스 바꿔주기
		String requestURI = httpServletRequest.getRequestURI(); // 3. 필터 요청이 오는지 확인
		
		
		System.out.println("requestURI : " + requestURI); // 4. 체인 양쪽으로 넘겨주는지받는지 확인
		chain.doFilter(request, response); // 체인을 걸어줘야함!!		
		System.out.println("responseURI : " + requestURI); // 4. 체인 양쪽으로 넘겨주는지받는지 확인
	}

}
