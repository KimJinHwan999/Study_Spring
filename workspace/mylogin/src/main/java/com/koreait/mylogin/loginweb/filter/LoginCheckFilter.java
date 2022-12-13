package com.koreait.mylogin.loginweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.PatternMatchUtils;

import com.koreait.mylogin.loginweb.session.SessionConst;

public class LoginCheckFilter implements Filter{
	
	private static final String[] whitelist = {"/", "/members/add", "/login", "/logout", "/css/*"}; // 제외할 URL
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestURI = httpRequest.getRequestURI();
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		System.out.println("인증 체크 필터 시작 " );
		
		if(isLoginCheckPath(requestURI)) {
			System.out.println("인증 체크 로직 실행 : " + requestURI);
			HttpSession session = httpRequest.getSession(false);
			
			if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
				System.out.println("미 인증 사용자 요청 ");
				// 로그인으로 redirect (사용자가 입력한 requestURI를 redirect에 기억해놓기
				// 이게 나중에 컨트롤러로 넘어가서 해당 requestURI가 있다면, 거기서 상품페이지로 넘겨주기
				httpResponse.sendRedirect("/login?redirectURL="+requestURI);
				// 미인증 사용자는 다음으로 진행하지 않고 끝낸다.
				return;
			}
		}
		
		// 다음 단계로 넘어간다.
		chain.doFilter(request, response);
	}
		/*
		 * 화이트 리스트의 경우 인증 체크 x
		 * simpleMatch: 파라미터 문자열이 특정 패턴에 매칭되는지를 검사함.
		 */
		private boolean isLoginCheckPath(String requestURI) {
			return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
		}
	}


