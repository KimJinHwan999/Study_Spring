package com.koreait.core2.controller.request;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestParamController {

	@RequestMapping("/request-param-v1")
	public void requestParamV1(HttpServletRequest request, HttpServletResponse response) 
			throws IOException {
		
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));
		
		System.out.println("username : " + username);
		System.out.println("age : " + age);
		response.getWriter().write("ok"); // throws로 예외 던져줘야 함
		
	}  // requestParamV1 class
	
	/*
	 * @ResponseBody
	 * 	- view 조회를 무시하고, Http message body에 직접 해당 내용 입력 
	 *  - (@ResponsBody 가 있어야 view 리졸버가 조회를 하지 않고 . 
	 *  - (@ResponsBody 없고, String타입이면, 뷰리졸버한테 페이지를 넘겨주는 것이 해당 페이지를 찾아줘버림)
	 * 	- @ResponseBody 없이 리턴타입을 String 받으면 페이지가 view조회를 받고 페이지가 이동됨
	 * 	
	 * @RequestParam
	 * 	- 파라미터 이름으로 바인딩 (HttpServlet request, response 안써도 됨)
	 * 	- form태그의 name속성이 파라미터 이름으로 사용
	 */
	@ResponseBody
	@RequestMapping("/request-param-v2")
	public String requestParamV2(@RequestParam("username") String username,
								 @RequestParam("age") int age) 
			throws IOException {
		
		System.out.println("username : " + username);
		System.out.println("age : " + age);
		return "ok"; // @ResponseBody 어노테이션 + String타입으로 변경 + 보여줄 메세지를 리턴에 담아주기
		
	}  // requestParamV2 class
	
	
	/*
	 * @RequestParam 사용
	 * 	- HTTP 파라미터 이름이 변수 이름과 같으면
	 * 		@RequestParam("변수이름") 생략 가능
	 * ---------------------------------------
	 * http://localhost:9090/request-param-v3?username=test&age=20
	 */
	@ResponseBody
	@RequestMapping("/request-param-v3")
	public String requestParamV3(@RequestParam String username, @RequestParam int age) 
			throws IOException {
		
		System.out.println("username : " + username);
		System.out.println("age : " + age);
		return "ok";
		
	}  // requestParamV3 class
	
	
	/*
	 * @RequestParam
	 * 	- String, int 등 단순 타입이면 @RequestParam 생략 가능
	 * 	- MVC 내부에서 required=false 를 적용한다 (필수 값 여부 -> 5번에서 알아보자)
	 *  - 여기 까지는 비추!
	 */
	@ResponseBody
	@RequestMapping("/request-param-v4")
	public String requestParamV4(String username, Integer age) 
			throws IOException {
		
		System.out.println("username : " + username);
		System.out.println("age : " + age);
		return "ok";
		
	}  // requestParamV4 class
	
	
	/*
	 * required = true : 반드시 파라미터 값이 들어와야 한다.
	 * ----------------------------
	 * @RequestParam -> required
	 * 	/request-param-required					-> username이 없으므로 에러
	 * 	/request-param-required?username=		-> 빈 문자로 통과
	 * 	/request-param-required?username=test	-> null을 int에 입력하는 것이 불가능, 따라서 Integer로 변경해야 함
	 */
	@ResponseBody
	@RequestMapping("/request-param-required")
	public String requestParamRequired(@RequestParam(required = true) String username, 
									   @RequestParam(required = false) Integer age) // int를 안 받아올 때 null 처리 해줘야 하기 때문에 Integer로 받아야 함
									   throws IOException {
		
		System.out.println("username : " + username);
		System.out.println("age : " + age);
		return "ok";
		
	}  // requestParamRequired class
	
	/*
	 * defaultValue
	 * 	- 파라미터값이 없는 경우 defaultValue를 사용하면 기본 값을 적용할 수 있다.
	 * 	- 기본값이 있기 떄문에 required는 의미가 없다.
	 * 	- 빈 문자열에도 적용
	 */
	@ResponseBody
	@RequestMapping("/request-param-default")
	public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username, 
									  @RequestParam(required = false, defaultValue = "-1") Integer age) 
									  throws IOException {
		
		System.out.println("username : " + username);
		System.out.println("age : " + age);
		return "ok";
		
	}  // requestParamDefault class
	
	
	/*
	 * @RequestParam
	 * 	- Map으로 조회하기
	 */
	@ResponseBody
	@RequestMapping("/request-param-map")
	public String requestParamMap(@RequestParam Map<String, Object> paramMap)
									  throws IOException {
		
		System.out.println("username : " + paramMap.get("username"));
		System.out.println("age : " + paramMap.get("age"));
		
		return "ok";
		
	}  // requestParamMap class
	
	@ResponseBody
	@RequestMapping("/model-attribute-v1")
	public String modelAttributeV1(@RequestParam String username, 
								   @RequestParam int age){
		
		HelloData hello = new HelloData();
		hello.setUsername(username);
		hello.setAge(age);
		
		System.out.println("username : " + hello.getUsername());
		System.out.println("age : " + hello.getAge());
		
		return "ok";
		
	}  // modelAttributeV1 class
	
	
	/*
	 * @ModelAttribute
	 * 	- 파라미터를 받아서 필요한 객체를 만들고 그 객체에 값을 넣어주는 과정을 자동화
	 */
	@ResponseBody
	@RequestMapping("/model-attribute-v2")
	public String modelAttributeV2(@ModelAttribute HelloData helloData){
		System.out.println("username : " + helloData.getUsername());
		System.out.println("age : " + helloData.getAge());
		System.out.println("hello : " + helloData.toString());
		
		return "ok";
		
	}  // modelAttributeV2 class
	
	
	/*
	 * @ModelAttribute 생략가능
	 * 	- String, int 와 같은 단순 타입 -> 스프링이 @RequestParam 가 생략되었다고 생각함
	 * 	- 객체 						-> 스프링이 @ModelAttribute 가 생략되었다고 생각함
	 */
	@ResponseBody
	@RequestMapping("/model-attribute-v3")
	public String modelAttributeV3(HelloData helloData){
		System.out.println("username : " + helloData.getUsername());
		System.out.println("age : " + helloData.getAge());
		System.out.println("hello : " + helloData.toString());
		
		return "ok";
		
	}  // modelAttributeV3 class
	
} // RequestParamController class
