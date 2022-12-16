package com.koreait.jpashop.domain;

import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable
@Getter
public class Address {

	private String city;
	private String street;
	private String zipcode;
	
	// 파라미터가 있는 생성자로 제작.
	public Address(String city, String street, String zipcode) {
		super();
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	
	// 기본생성자 생성 (외부에선 만들지 못하게 protected로 잠궈둠 -> 오로지 파라미터가 있는 생성자로만 생성할 수 있도록)
	// jpa스펙 상 만들어 놓은 기본 생성자
	// 함부로 new 를 통해서 생성하지 못하도록 한다.
	protected Address(){}
	
}
