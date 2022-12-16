package com.koreait.Springtest18.memberDTO;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.ToString;

@Embeddable
@Getter
@ToString
public class Address {

	private String city;
	private String treet;
	private String zipcode;
	
	public Address(String city, String treet, String zipcode) {
		super();
		this.city = city;
		this.treet = treet;
		this.zipcode = zipcode;
	}
	
	protected Address() {}
	
}
