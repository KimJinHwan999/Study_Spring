package com.koreait.core2.repository;

import java.util.List;

import com.koreait.core2.member.Member;

public interface MemberRepository { // DAO역할

	// 회원 저장
	Member save(Member member);
	
	// 전체 찾기
	List<Member> findAll();
}
