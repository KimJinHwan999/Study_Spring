package com.koreait.Springtest19.memberDTO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.koreait.Springtest19.orderDTO.Orders;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString
public class Member {
	
	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long memberId;
	
	private String name;
	
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy = "member")
	private List<Orders> orders = new ArrayList<Orders>();
	
	
	
}
