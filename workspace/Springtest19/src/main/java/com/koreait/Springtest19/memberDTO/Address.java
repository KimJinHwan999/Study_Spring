package com.koreait.Springtest19.memberDTO;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.ToString;

@Embeddable
@Getter
@ToString
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
	
	protected Address() {}
	
}
