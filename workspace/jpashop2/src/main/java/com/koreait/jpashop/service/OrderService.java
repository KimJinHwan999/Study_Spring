package com.koreait.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koreait.jpashop.domain.Item;
import com.koreait.jpashop.domain.Member;
import com.koreait.jpashop.domain.Order;
import com.koreait.jpashop.domain.OrderItem;
import com.koreait.jpashop.dto.OrderSearch;
import com.koreait.jpashop.repository.ItemRepository;
import com.koreait.jpashop.repository.MemberRepository;
import com.koreait.jpashop.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;
	
	// 주문
	@Transactional
	public Long order(Long memberId, Long itemId, int count) { // <- 준영속상태의 데이터들 (count는 신규데이터)
		// 엔티티 조회
		// 영속성 컨텍스트 영역에 들여다 놓기 위해
		Member member = memberRepository.findOne(memberId);
		Item item = itemRepository.findOne(itemId);
		
		// 주문상품 생성 (OrderItem은 일반 DTO가 아니라 테이블로 봐야 함 -> 관련 비즈니스 로직도 넣어주기)
		// item.getPrice() -> Price 가 중요하다는 걸 강조하기 위해 직접 뺴서 파라미터로 넣어줌!
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
				
		// 주문 생성
		Order order = Order.createOrder(member, orderItem);
		
		// 주문 저장
		orderRepository.save(order);
		
		
		return order.getId();
	}

	public List<Order> findOrders(OrderSearch orderSearch) {
		return orderRepository.findAll(orderSearch);
		
	}
	
	@Transactional
	public void cancelOrder(Long orderId) {
		Order order = orderRepository.findOne(orderId);
		// 취소(update)에 대한 비즈니스 로직 처리 
		order.cancel();
	}
	
	
	

}





























