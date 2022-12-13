package com.koreait.jpashop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.jpashop.domain.Item;
import com.koreait.jpashop.domain.Member;
import com.koreait.jpashop.dto.OrderForm;
import com.koreait.jpashop.service.ItemService;
import com.koreait.jpashop.service.MemberService;

import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequiredArgsConstructor
public class OrderController {
	
	@Autowired
	private final ItemService itemService;
	@Autowired
	private final MemberService memberService;
	
	// request : order
	// member 조회, item 조회
	// response : order/orderForm
	
	
	@GetMapping("/order")
	public String createForm(Model model) {
		// member 조회
		List<Member> members = memberService.findMembers();
		// item 조회
		List<Item> items = itemService.findItem();
		
		model.addAttribute("members", members);
		model.addAttribute("items", items);
		
		return "order/orderForm";
	}
	
	
	@PostMapping("/order")
	public String createForm(OrderForm orderForm_, Model model) {
		OrderForm orderForm = new OrderForm();
		
		orderForm.setMemberId(orderForm_.getMemberId());
		orderForm.setItemId(orderForm_.getItemId());
		orderForm.setCount(orderForm_.getCount());
		
		Member member =  memberService.findOne(orderForm.getMemberId());
		Item item = itemService.findOne(orderForm.getItemId());
		
		model.addAttribute("member", member);
		model.addAttribute("item", item);
		model.addAttribute("count", orderForm.getCount());
		
		return "redirect:/";
	}

}
