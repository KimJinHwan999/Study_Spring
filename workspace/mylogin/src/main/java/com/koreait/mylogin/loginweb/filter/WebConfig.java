package com.koreait.mylogin.loginweb.filter;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.koreait.mylogin.loginweb.interceptor.LogInterceptor;
import com.koreait.mylogin.loginweb.interceptor.LoginCheckInterceptor;

@Component
public class WebConfig implements WebMvcConfigurer{ // 인터셉터 사용 위해 상속받기 + 메서드 오버라이드
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor( new LogInterceptor() )
				.order(1)
				.addPathPatterns("/**")		// 인터셉터를 지정할 URL 패턴을 정할 수 있음
				.excludePathPatterns("/error");
		
		registry.addInterceptor( new LoginCheckInterceptor() )
				.order(2)
				.addPathPatterns("/**")		// 인터셉터를 지정할 URL 패턴을 정할 수 있음
				.excludePathPatterns("/", "/members/add", "/login", "/logout", "/css/**");
	}
	
	
	
	
	// @Bean
	public FilterRegistrationBean<Filter> logFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean 
										= new FilterRegistrationBean<Filter>();
		filterRegistrationBean.setFilter(new LogFilter()); 	// LogFilter 등록
		filterRegistrationBean.setOrder(1);					// 어떤 필터를 먼저 등록할 지 순서 정하기
		filterRegistrationBean.addUrlPatterns("/*"); 		// 모든 경로에 적용하기
		
		return filterRegistrationBean;						// 필터가 Bean에 등록 완료
	}
	

	// @Bean 
	public FilterRegistrationBean<Filter> loginCheckFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean 
										= new FilterRegistrationBean<Filter>();
		filterRegistrationBean.setFilter(new LoginCheckFilter()); 
		filterRegistrationBean.setOrder(2);
		filterRegistrationBean.addUrlPatterns("/*"); 		
		
		return filterRegistrationBean;						// 필터가 Bean에 등록 완료
	}
	
	
}
