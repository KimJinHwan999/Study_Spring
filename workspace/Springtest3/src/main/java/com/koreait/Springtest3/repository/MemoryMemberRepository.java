package com.koreait.Springtest3.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.koreait.Springtest3.member.Member;

@Repository
public class MemoryMemberRepository implements MemberRepository{

	private static Map<Integer, Member> store = new HashMap<Integer, Member>();
	private static int sequence = 0;
	
	@Override
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}
	@Override
	public List<Member> findAll() {
		return new ArrayList<Member>(store.values());
	}
	

	
	

}
