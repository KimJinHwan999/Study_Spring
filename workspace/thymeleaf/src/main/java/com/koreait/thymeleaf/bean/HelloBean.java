package com.koreait.thymeleaf.bean;

import org.springframework.stereotype.Component;

@Component("helloBean")
public class HelloBean { // 컨트롤러도, 서비스도, 레파지토리도 아니지만 스프링 영역에 넣고싶다면 Bean을 만들어주기

	public String hello(String data) {
		return "Hello" + data;
	}
}
