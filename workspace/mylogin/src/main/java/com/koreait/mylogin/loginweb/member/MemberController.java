package com.koreait.mylogin.loginweb.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("members")
@RequiredArgsConstructor	// 레파지토리 생성자 안만들어도 되게끔 해줌
public class MemberController {

	private final MemberRepository memberRepository;
	
	
	/*	실습2.
	 *  members -> 공통
     *	Member의 빈 객체를 model에 넘겨줘야한다.
	 *	addMemberForm.html -> th:object & th:field
	 *	취소 -> home.html로 이동 url 입력
	 */
	
	/*
	 * model.addAttribute("member", new Member());
	 * 		-> @ModelAttribute("member")Member member
	 */
	@GetMapping("/add")
	public String addForm(@ModelAttribute("member")Member member) {
		// model.addAttribute("member", new Member());
		return "members/addMemberForm";
	}
	
	// 저장, 홈 화면으로 이동
	@PostMapping("/add")
	public String save(@ModelAttribute Member member) {
		memberRepository.save(member);
		return "redirect:/";
	}
}
