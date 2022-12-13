package com.koreait.jpashop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.jpashop.domain.Address;
import com.koreait.jpashop.domain.Member;
import com.koreait.jpashop.dto.MemberForm;
import com.koreait.jpashop.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;

	@GetMapping("/members/new")
	public String createForm(Model model) {
		// 빈 값이라도 데이터를 만들어 넘겨줘야 타임리프를 사용할 수 있음
		model.addAttribute("memberForm", new MemberForm());
		return "members/createMemberForm";
	}
	
	// Valid -> 벨리데이션 체크
	// BindingResult -> validation 다음에 Binding이 있으면, 에러를 Binding에 담아준다.
	// 예외 처리는 컨트롤러 단에서 한번에 하는 걸 권장 (try - catch문으로)
	@PostMapping("/members/new")
	public String create(@Valid MemberForm form, BindingResult result) throws IllegalAccessException {
		
		// 에러 발생 시
		if(result.hasErrors()) {
			return "members/createMemberForm";
		}
		
		// 정상로직, service
		Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
		Member member = new Member();
		member.setName(form.getName());
		member.setAddress(address);
		
		memberService.join(member);
		
		return "redirect:/";
	}
	
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList";
	}
	
	// request : members
	// response : members/memberList
	
	
}
