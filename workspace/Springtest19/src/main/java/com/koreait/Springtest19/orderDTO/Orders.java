package com.koreait.Springtest19.orderDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.koreait.Springtest19.memberDTO.Member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter 
public class Orders {

	@Id @GeneratedValue
	@Column(name = "ORDER_ID")
	private Long orderId;
	private LocalDateTime orderDate;
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	public Orders(LocalDateTime orderDate, String status, Member member) {
		super();
		this.orderDate = orderDate;
		this.status = status;
		this.member = member;
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", orderDate=" + orderDate + ", status=" + status + "]";
	}
	
	
}
