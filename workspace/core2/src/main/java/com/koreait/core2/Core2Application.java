package com.koreait.core2; // 메인 메서드와 동일 패키지 또는 하위 패키지만 스프링이 뒤짐
						   // 쭉 뒤지면서 어노테이션이 있으면 연결고리 만들고 싱글톤 빈으로 등록을 해줌

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Core2Application {

	public static void main(String[] args) {
		SpringApplication.run(Core2Application.class, args);
	}

}
