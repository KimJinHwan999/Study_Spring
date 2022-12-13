package com.koreait.core2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	// 기본 localhost:9090으로 들어오면 여기를 호출
	@GetMapping("/")
	public String home() {
		return "home";
	}
}
