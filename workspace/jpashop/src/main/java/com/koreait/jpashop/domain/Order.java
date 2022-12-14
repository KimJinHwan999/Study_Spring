package com.koreait.jpashop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "orders")
public class Order {

	@Id @GeneratedValue
	@Column(name = "order_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	private LocalDateTime orderDate;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	// 주문 상태 (ORDER, CANCEL) -> Enum 처리
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	//------------ 연관관계 메서드----------------
	public void setMember(Member member) {
		this.member = member;
		member.getOrders().add(this);
		// this의 값이 궁금하면 디버깅 걸어서 값을 관찰하기
	}
	
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}
	//------------ 연관관계 메서드 끝 ----------------
	
	//------------ 비즈니스 로직 ----------------
	
	public static Order createOrder(Member member, OrderItem... orderItems) {
		Order order = new Order();
		order.setMember(member);
		
		for(OrderItem orderItem : orderItems) {
			order.addOrderItem(orderItem);
		}
		
		order.setOrderStatus(OrderStatus.ORDER);
		order.setOrderDate(LocalDateTime.now());
		
		return order;
		
	}
	
	// 주문취소
	public void cancel() {
		this.setOrderStatus(OrderStatus.CANCEL);
		for (OrderItem orderItem : orderItems) {
			orderItem.cancel();
		}
	}
	
	//------------ 비즈니스 로직 끝 ----------------
	
	
	
}





























