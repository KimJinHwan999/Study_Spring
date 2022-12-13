package com.koreait.item.domain.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.item.domain.item.DeliveryCode;
import com.koreait.item.domain.item.Item;
import com.koreait.item.domain.item.ItemRepository;
import com.koreait.item.domain.item.ItemType;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor // 얘가 있으면 생성자도 생략 가능(lombok 기능)
						 // final이 붙은 멤버변수만 사용해서 생성자를 자동으로 만들어준다.
public class ItemController {

	private final ItemRepository itemRepository;

	// 생성자가 하나만 있으면 @Autowired 생략 가능
	// @Autowired
	// public ItemController(ItemRepository itemRepository) {
	//   this.itemRepository = itemRepository;
	// }
	
	// @ModelAttribute : 
	// 		Controller를 호출할 때 (어떤 메서드가 호출이 되던간에)
	//		model에 자동으로 해당내용이 담기는게 보장된다.
	@ModelAttribute("regions")
	public Map<String, String> regions(){
		// 순서가 저장되는 HashMap
		Map<String, String> regions = new LinkedHashMap<String, String>();
		regions.put("SEOUL", "서울");
		regions.put("BUSAN", "부산");
		regions.put("JEJU", "제주");
		return regions;
		
		// model.addAttribute는 위에서 자동으로 put하면서 처리됨
		// model.addAttribute("regions", regions);
	}
	
	@ModelAttribute("itemType")
	public ItemType[] itemTypes() {
		// enum에 있는 값을 배열로 넘겨준다.
		return ItemType.values();
	}
	
	@ModelAttribute("deliveryCodes")
	public List<DeliveryCode> deliveryCodes(){
		List<DeliveryCode> deliveryCodes = new ArrayList<DeliveryCode>();
		deliveryCodes.add( new DeliveryCode("FAST", "빠른배송") );
		deliveryCodes.add( new DeliveryCode("NORMAL", "일반배송") );
		deliveryCodes.add( new DeliveryCode("SLOW", "느린배송") );
		return deliveryCodes;
	}
	
	// 위에 설정한 기본 경로로 들어옴 ("/basic/items")
	@GetMapping 
	public String items(Model model) {
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
		return "basic/items";
	}
	
	// /basic/items/아이템의ID
	@GetMapping("/{itemId}")
	public String item(@PathVariable long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/item";
	}
	
	@GetMapping("/add")
	public String addForm(Model model) {
		// 빈 객체 넘겨서 타임리프단에서 빈 객체 받아주기
		model.addAttribute("item", new Item());	
		return "basic/addForm";
	}
	
/* ----------------------------- save 4가지 + 2--------------------------------*/
	
	//@PostMapping("/add")
	public String save(@RequestParam String itemName,
			@RequestParam int price,
			@RequestParam Integer quantity, // null 이 들어올 수 있으면 Integer
			Model model) {
		
		// Item item = new Item(itemName, price, quantity);
		Item item = new Item();
		item.setItemName(itemName);
		item.setPrice(price);
		item.setQuantity(quantity);
		
		itemRepository.save(item);
		
		model.addAttribute("item", item);
		
		return "basic/item";
	}
	
	//@PostMapping("/add")
	// @ModelAttribute("키") 는 model.addAtttibute("키", 값); 동일
	public String saveV2(@ModelAttribute("item") Item item) {

		//@ModelAttribute 가 해주는 역할
//		Item item = new Item();
//		item.setItemName(itemName);
//		item.setPrice(price);
//		item.setQuantity(quantity);
		
		itemRepository.save(item);
		
//		model.addAttribute("item", item);
		
		return "basic/item";
	}
	
	/*
	 * @ModelAttribute에서 name 생략
	 * 	-> 생략 시 model에 저장되는 name은 클래스명 첫 글자만 소문자로 등록	
	 * 		Item -> item
	 */
	//@PostMapping("/add")
	public String saveV3(@ModelAttribute Item item) {
		itemRepository.save(item);
		return "basic/item";
	}
	
	/*
	 * @ModelAttribute도 생략
	 * 	-> 여기까지는 비추!
	 */
	// @PostMapping("/add")
	public String saveV4(Item item) {
		itemRepository.save(item);
		return "basic/item";
	}
	
	
	// redirect로 담아놔서 새로고침 한다고 값이 새롭게 안들어감
	// (saveV4로 하면 등록해놓고 새로고침하면 계속 새 값이 DB에들어가게 됨)
	//@PostMapping("/add")
	public String saveV5(Item item) {
		itemRepository.save(item);
		return "redirect:/basic/items/" + item.getId();
	}
	
	
	/*
	 * "redirect:/basic/items/{itemId}"
	 * 		-> pathVariable			: 	{itemID}
	 * 		-> 나머지는 파라미터로 처리	: 	?status=true
	 */
	// @PostMapping("/add")
	public String saveV6(Item item, RedirectAttributes redirectAttributes) {
		
		System.out.println("item.open = " + item.getOpen());
		System.out.println("item.regions = " + item.getRegions());
		System.out.println("item.itemType = " + item.getItemType());
		
		Item saveItem = itemRepository.save(item);
		redirectAttributes.addAttribute("itemId", saveItem.getId());
		redirectAttributes.addAttribute("status", true);
		
		return "redirect:/basic/items/{itemId}"; // redirect 속성값으로 저장했기 때문에 꺼내쓸 수 있음
	}
	
	/*
	 * BindingResult : 
	 * 		- Item 객체에 값이 잘 담기지 않을 때 BindingResult객체에 값이 담긴다.
	 * 		- 스프링이 제공하는 검증 오류를 보관하는 객체, 검증 오류가 발생하면 여기에 보관
	 * 		- 주의) BindingResult는 검증할 대상 바로 다음에 와야 한다. 순서가 중요!
	 * 		- BindingResult는 Model에 자동으로 포함된다.
	 */
	// @PostMapping("/add")
	public String saveV7(Item item, BindingResult bindingResult, 
						RedirectAttributes redirectAttributes) {

		System.out.println("item.open = " + item.getOpen());
		System.out.println("item.regions = " + item.getRegions());
		System.out.println("item.itemType = " + item.getItemType());
		
		// if( item.getItemName() == null ) { 
		// null 값, 빈값 모두 걸러주기 위해 StringUtil(Spring내에있는) 사용
		// StringUtils.hasText
		// - 값이 있을 경우에는 true, 공백이나 null이 들어올 경우에는 false를 반환하게 된다.
		if( !StringUtils.hasText(item.getItemName() )) {
			// FieldError : field 단위의 error를 처리하는 spring에서 제공해주는 객체
			bindingResult.addError( new FieldError("item", "itemName", "상품 이름은 필수입니다.") );
		}
		
		/*
		 * price
		 * 		- 조건 : null, 1000미만, 1000000 초과 안 됨
		 */
		if(item.getPrice() == null || 
		   item.getPrice() < 1000 || 
		   item.getPrice() > 1000000 ) {
			bindingResult.addError( new FieldError("item", "price", "가격은 1000 ~ 1000000 까지 허용합니다."));
		}
		
		/*
		 * * quantity
		 *  	- 조건 : null, 최대 9999까지만 허용
		 */
		if(item.getQuantity() == null ||
		   item.getQuantity() > 10000) {
			bindingResult.addError( new FieldError("item", "quantity", "수량은 최대 9999까지 허용합니다."));
		}

		
		// 검증에 실패하면 다시 입력 폼으로
		if(bindingResult.hasErrors()) {
			System.out.println("errors = " + bindingResult);
			return "basic/addForm";
		}
		
		
		Item saveItem = itemRepository.save(item);
		redirectAttributes.addAttribute("itemId", saveItem.getId());
		redirectAttributes.addAttribute("status", true);
		
		return "redirect:/basic/items/{itemId}"; // redirect 속성값으로 저장했기 때문에 꺼내쓸 수 있음
	}
	
	
	/*
	 * FieldError param
	 * 	- objectName		: 오류가 발생한 객체 이름
	 * 	- field				: 오류 필드
	 * 	- rejectValue		: 사용자가 입력한 값(거절된 값)
	 * 	- bindingFailure	: 타입 오류와 같은 바인딩 실패인지를 구분
	 * 	- codes				: 메시지 코드
	 * 	- arguments			: 메시지에서 사용하는 인자
	 * 	- defaultMessage	: 기본 오류 메시지
	 */
	
	// @PostMapping("/add")
	public String saveV8(Item item, BindingResult bindingResult, 
						RedirectAttributes redirectAttributes) {

		System.out.println("item.open = " + item.getOpen());
		System.out.println("item.regions = " + item.getRegions());
		System.out.println("item.itemType = " + item.getItemType());
		
		if( !StringUtils.hasText(item.getItemName() )) {
			bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, null, null, "상품 이름은 필수입니다."));
		}
		
		if(item.getPrice() == null || 
		   item.getPrice() < 1000 || 
		   item.getPrice() > 1000000 ) {
			bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, null, null, "가격은 1000원 이상 1000000원이하의 금액을 넣어주세요."));
		}
		
		if(item.getQuantity() == null ||
		   item.getQuantity() > 10000) {
			bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, null, null, "수량은 9999이하로 작성해주세요."));
		}

		
		if(bindingResult.hasErrors()) {
			System.out.println("errors = " + bindingResult);
			return "basic/addForm";
		}
		
		
		Item saveItem = itemRepository.save(item);
		redirectAttributes.addAttribute("itemId", saveItem.getId());
		redirectAttributes.addAttribute("status", true);
		
		return "redirect:/basic/items/{itemId}"; 
	}
	
	@PostMapping("/add")
	public String saveV9(Item item, BindingResult bindingResult, 
						RedirectAttributes redirectAttributes) {

		System.out.println("item.open = " + item.getOpen());
		System.out.println("item.regions = " + item.getRegions());
		System.out.println("item.itemType = " + item.getItemType());
		
		if( !StringUtils.hasText(item.getItemName() )) {
			bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, 
									new String[] {"required.item.itemName", "required.default"}, null, "defaultMessage"));
		}
		
		if(item.getPrice() == null || 
		   item.getPrice() < 1000 || 
		   item.getPrice() > 1000000 ) {
			bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, 
									new String[] {"range.item.price"}, new Object[] {1000, 10000}, null));
		}
		
		if(item.getQuantity() == null ||
		   item.getQuantity() > 10000) {
			bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false,
									new String[] {"max.item.quantity"}, new Object[] {9999}, null));
		}

		
		if(bindingResult.hasErrors()) {
			System.out.println("errors = " + bindingResult);
			return "basic/addForm";
		}
		
		
		Item saveItem = itemRepository.save(item);
		redirectAttributes.addAttribute("itemId", saveItem.getId());
		redirectAttributes.addAttribute("status", true);
		
		return "redirect:/basic/items/{itemId}"; 
	}
/* ----------------------------- save 4가지 방식 + 2--------------------------------*/
	
	@GetMapping("/{itemId}/edit")
	public String editForm( @PathVariable Long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/editForm";
	}
	
	@PostMapping("/{itemId}/edit")
	public String edit(@PathVariable Long itemId,
					   @ModelAttribute Item item) {
		
		itemRepository.update(itemId, item);
		
		// 상세페이지로 다시 돌아가기(form으로 받은 것 다 날리고 / 2번째에 짠 getmapping으로 날아감)
		return "redirect:/basic/items/{itemId}";
		
	}
	
	
	
	
	
	// 테스트용 데이터 추가
	@PostConstruct
	public void init() {
		// System.out.println("초기화 메서드");
		itemRepository.save(new Item("testA", 10000, 10));
		itemRepository.save(new Item("testB", 20000, 20));
	}
	
	// 종료 메서드
	@PreDestroy
	public void destroy() {
		// System.out.println("종료 메서드");
	}
	
} // Controller class
