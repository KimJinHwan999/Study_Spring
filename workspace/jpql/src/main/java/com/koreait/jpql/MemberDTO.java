package com.koreait.jpql;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 특정 결과값만을 받아오는 DTO
@Getter @Setter @ToString
public class MemberDTO {

	private String username;
	private int age;
	
	public MemberDTO(String username, int age) {
		super();
		this.username = username;
		this.age = age;
	}
	
	
}
