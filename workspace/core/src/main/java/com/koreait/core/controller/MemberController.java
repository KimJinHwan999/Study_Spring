package com.koreait.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.core.dto.MemberDTO;

@Controller
public class MemberController {

	// member getmapping 이루어지는 메서드
	// view : thymeleaf/member -> html파일까지 생성할 것
	
	// @RequestMapping() : 전송방식 상관없이 매핑이 됨
	@RequestMapping("member")
	public String GetMember(Model model) {
		MemberDTO member = new MemberDTO(1, "자바학생", "01012345678");
		model.addAttribute("member", member);
		return "thymeleaf/member";
	}
}
