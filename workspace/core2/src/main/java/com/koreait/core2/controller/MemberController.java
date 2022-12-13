package com.koreait.core2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.core2.member.Member;
import com.koreait.core2.member.MemberFormDTO;
import com.koreait.core2.service.MemberService;

@Controller
public class MemberController { 
	
	// Controller가 Service에 의존한다 (서비스의 기능을 가져다가 쓰기 때문에)
	// Service도 Repository에 의존한다
	// MC(Member Controller) <- MS(Member Service) <- MMR(Memory Member Repository)
	// Service는 여러 Controller에서 가져다 쓸 수 있기 때문에 Spring Container에 등록을 해야 한다.
	
	/* MemberService mService = new MemberService(); */ 
	
	// 스프링스럽게 작업하기
	// service는 Spring Container에 하나만 생성 민 등록해서  같이 공유해서 쓸 수 있다.
	private final MemberService memberService; // 교체될 일이 없기 때문에 잠궈버리기
	
	/*
	 *  생성자 주입
	 *  	- 가장 권장
	 */
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	/*	다른 방법 1.
	 *  필드 주입(Field Injection)
	 *  	- final 키워드를 사용할 수 없어, 컨트롤러와 서비스 순환참조가 발생할 수 있다.
	 *  	- 잘 사용하면 순환참조 발생 안 시킬 수 있지만 권장하는 방법 아님.
	 */
	/* @Autowired private MemberService memberService; */
	
	
	
	/*	다른 방법 2.
	 *  수정자 주입(Setter Injection)
	 *  	- public으로 노출이 되기 때문에 다른 곳에서 주입이 가능하다.
	 */
	/* private MemberService memberService;
	
	@Autowired
	public void setMember(MemberService memberService) {
		this.memberService = memberService;
	} */
	
	@GetMapping(value = "/members/new") // value =  는 생략 가능
	public String createForm() {
		return "members/createMemberForm";
	}
	
	@PostMapping(value = "/members/new")
	public String create(MemberFormDTO form) {
		Member member = new Member();
		member.setName(form.getName());
		
		memberService.join(member);
		
		// 홈 화면으로 돌린다.
		return "redirect:/"; // 회원가입 때 입력 한 정보 날리기 위해 리다이렉트로 요청.(컨트롤러를 뒤져서 "/" 웰컴페이지 요청을 찾아 감)
		// return "forward:/"; // forward일 때는 굳이 쓸 필요 없음
	}
	
	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList";
	}
	
	
	
}
