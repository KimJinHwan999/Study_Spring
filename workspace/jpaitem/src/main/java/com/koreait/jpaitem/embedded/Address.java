package com.koreait.jpaitem.embedded;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter // Setter로 외부에서 디테일한 세팅을 못하도록 막아버림
public class Address {

	private String city;
	private String street;
	private String zipcode;
	
	public Address(String city, String street, String zipcode) {
		super();
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	
	public Address() {}
	
}
