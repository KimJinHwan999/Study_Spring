package com.koreait.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@GetMapping("hello")
	public String hello(Model model) { // 데이터를 컨트롤하는게 모델(MVC 패턴의 모델) -> 화면만 담을거면 Model 하지말고, 데이터 담아줄거면 Model로 넘겨주기
		System.out.println("controller 도착");
		model.addAttribute("data", "hello!!!"); // 타임리프는 html을 덮어 씌워주는 역할을 함.
		return "hello"; // 이 주소로 페이지 이동
	}
	
	// http://localhost:9090/hello-mvc?name=SpringMVC
	// @GetMapping("hello-mvc")
	//@RequestParam("") 안에 required 기본 값은 true이기에, 파라미터 안넘겨주면 500에러 뱉어냄
	public String helloMVC(@RequestParam("name") String param, Model model) { // RequestParam("name")으로 요청값 받아와서(get방식으로 넘겼을 때 물음표 이후 파라미터) String param에 담아줌
		model.addAttribute("name", param); // model.addAttribute로 view 단에 넘길건데, param을 "name"이라는 key값에 담아서 넘겨줌(보통은 변수를 맞춰줌)
		return "hello-template";
	}
	
	/* @RequestParam
	 * 	- required : 파라미터값 필수 여부, true -> 필수(default), false -> 필수 아님
	 * 	- defaultValue : 파라미터 값이 없을 경우 기본으로 들어갈 값
	 */
	@GetMapping("hello-mvc")
	// @RequestParam("") 안에 required를 false로 줬기 때문에 파라미터 안 넘겨줘도 500에러 안나옴
	// @RequestParam("") 안에 defaultValue값을 줬기때문에, 파라미터 안 넘겨줬을 때 null이 아니라 required test 뱉어냄
	public String helloMVC2(@RequestParam(value="name", required=false, defaultValue = "required test") 
							String param, Model model) { 
		model.addAttribute("name", param);
		return "hello-template";
	}
}
