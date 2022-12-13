package com.koreait.Springtest3.repository;

import java.util.List;

import com.koreait.Springtest3.member.Member;

public interface MemberRepository {

	Member save(Member member);
	
	List<Member> findAll();
}
