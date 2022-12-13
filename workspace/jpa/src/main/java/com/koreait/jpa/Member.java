package com.koreait.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

// 클래스를 기점으로 DB단에 적용되도록 함. (보통은 오라클에 DB를 중심으로 만들지만..)
// JPA 관리 대상으로 넣어야함 (@Entity)
/*
 * 객체와 테이블을 생성하고 매핑하기 
 * @Entity 	: JPA가 관리할 객체
 * @Id 		: 데이터베이스 PK와 매핑
 * 
 *  create table Member(
 *  	id		number primary key,
 *  	name	varchar2(200)	
 *  );
 */
@Entity 
@Getter @Setter
//@Table(name = "MBR") -> 테이블 이름 Member가 아니라 MBR로 만들거야!
public class Member {
	
	@Id
	private Long id;
	
	@Column(unique = true, length = 10)
	private String name;
	
	// 컬럼명 지정
	@Column(name = "myage")
	private int age;
	
	// 날짜 타입 매핑
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	// 날짜타입 매핑
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;	// 가장 마지막 로그인 한 날짜 보여줄 때 사용
	
	// 매핑 무시
	@Transient
	private int temp;
	
}
