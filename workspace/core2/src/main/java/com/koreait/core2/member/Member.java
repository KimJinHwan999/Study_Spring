package com.koreait.core2.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

// @Entity : jpa가 관리하는 class
@Entity 
public class Member {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySequence")
	@SequenceGenerator(name = "mySequence", sequenceName = "member_seq", allocationSize = 1)
	private int id;
	// @Column(name="username") // JPA는 DB에서 name을 username으로 컨트롤 해줌
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
