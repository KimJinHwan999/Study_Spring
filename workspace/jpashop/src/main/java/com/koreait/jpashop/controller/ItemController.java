package com.koreait.jpashop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.jpashop.domain.Item;
import com.koreait.jpashop.dto.ItemForm;
import com.koreait.jpashop.service.ItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {
	
	private final ItemService itemService;
	
	// request : items/new;
	// response : items/createItemForm;
	
	@GetMapping(value = "/items/new")
	public String createForm(Model model) {
		ItemForm itemForm = new ItemForm();
		model.addAttribute("form", itemForm);
		return "/items/createItemForm";
	}
	
	// item 데이터 저장 로직
	// request : /items/new
	// response : /
	@PostMapping("/items/new")
	public String create(ItemForm itemForm) {
		Item item = new Item();
		
		item.setName(itemForm.getName());
		item.setPrice(itemForm.getPrice());
		item.setStockQuantity(itemForm.getStockQuantity());
		
		itemService.saveItem(item);
		
		return "redirect:/";
	}
	
	// request : items
	// response : items/itemList
	@GetMapping("/items")
	public String itemList(Model model) {
		List<Item> items = itemService.findItem();
		model.addAttribute("items", items);
		return "/items/itemList";
	}
	
	// request : items/2/edit
	// 1건 조회 -> 결과값
	// response : items/updateItemForm
	@GetMapping("/items/{itemId}/edit")
	public String updateItemForm(@PathVariable("itemId") Long itemId,
							Model model) {
		Item item = itemService.findOne(itemId);
		
		ItemForm form = new ItemForm();
		form.setId(item.getId());
		form.setName(item.getName());
		form.setPrice(item.getPrice());
		form.setStockQuantity(item.getStockQuantity());
		
		model.addAttribute("form", form);
		
		return "/items/updateItemForm";
	}
	
	@PostMapping("/items/{itemId}/edit")
	public String updateItem(@PathVariable Long itemId,
							 @ModelAttribute("form") Item form) {
		/*
		 * item -> 객체는 준 영속상태의 item
		 * 			영속성 컨텍스트의 지원을 받을 수 없고, 데이터를 수정해도 변경 감지 기능은 동작X
		 * 			간단하게 그냥 프론트 단에서 받아온 데이터를 Item 객체에 세팅해 준 것.
		 * 			과거에 select 되었던 경험이 있었기 때문에 준영속상태에 있는 것
		 */
//		Item item = new Item();
//		item.setId(form.getId());
//		item.setName(form.getName());
//		item.setPrice(form.getPrice());
//		item.setStockQuantity(form.getStockQuantity());
		
//		itemService.saveItem(item); -> merge
		itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());
		return "redirect:/items";
		
	}

}

































