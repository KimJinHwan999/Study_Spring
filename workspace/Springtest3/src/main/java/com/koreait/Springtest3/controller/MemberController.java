package com.koreait.Springtest3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.Springtest3.member.Member;
import com.koreait.Springtest3.repository.MemoryMemberRepository;

@Controller
public class MemberController {

	private final MemoryMemberRepository memoryMemberRepository;
	
	public MemberController(MemoryMemberRepository memoryMemberRepository) {
		this.memoryMemberRepository = memoryMemberRepository;
	}
	
	@GetMapping("/member/createMember")
	public String join() {
		return "/member/createMember";
	}
	
	@PostMapping("/member/createMember")
	public String createMember(@ModelAttribute Member member, Model model) {
		
		memoryMemberRepository.save(member);
		model.addAttribute("id", member.getId());
		model.addAttribute("genderNum", member.getGenderNum());
		model.addAttribute("name", member.getName());
		model.addAttribute("phoneNum", member.getPhoneNum());
		return "redirect:/";
	}
	
	@RequestMapping("/member/member")
	public String findMember(Model model) {
		model.addAttribute("members", memoryMemberRepository.findAll());
		return "/member/member";
	}
	
}
