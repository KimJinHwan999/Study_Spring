package com.koreait.Springtest18.memberDTO;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	
	@Embedded
	private Address address;
	
	private int age;
	
	@Embedded
	private DateTime dateTime;
	
	@Enumerated(EnumType.STRING)
	private Position position;
	
	private String username;
	
	
}
