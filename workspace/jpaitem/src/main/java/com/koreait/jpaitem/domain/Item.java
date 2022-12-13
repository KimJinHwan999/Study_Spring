package com.koreait.jpaitem.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter @Setter
public class Item {

	@Id @GeneratedValue	// 전략 안 넣으면 알아서 DBMS 전략대로.. (오라클은 어떤 전략도 넣지 않으면 자동증가 컬럼으로 넣어 줌)
	@Column(name = "ITEM_ID")
	private Long id;
	private String name;
	private int price;
	private int stockQuantity;
	
	@OneToMany(mappedBy = "item")
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	public void addOrderItem(OrderItem orderItem) {
		orderItem.setItem(this);
		this.orderItems.add(orderItem);
	}
}
