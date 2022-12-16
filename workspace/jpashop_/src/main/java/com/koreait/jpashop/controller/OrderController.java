package com.koreait.jpashop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.jpashop.domain.Item;
import com.koreait.jpashop.domain.Member;
import com.koreait.jpashop.service.ItemService;
import com.koreait.jpashop.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {
	
	private final MemberService memberService;
	private final ItemService itemService;
	
	// request : order
	// member 조회, item 조회
	// response : order/orderForm
	@GetMapping("/order")
	public String createForm(Model model) {
		// member 조회
		List<Member> members = memberService.findMembers();
		// item 조회
		List<Item> items = itemService.findItems();
		
		model.addAttribute("members", members);
		model.addAttribute("items", items);
		
		return "order/orderForm";
	}
	
	
}












