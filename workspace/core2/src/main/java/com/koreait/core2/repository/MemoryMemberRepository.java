package com.koreait.core2.repository;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.koreait.core2.member.Member;



@Repository
public class MemoryMemberRepository implements MemberRepository{
	
	// 메모리 사용 (DB없어서 데이터 임시로 메모리에 저장 // 컨트롤러 -> 서비스 -> 레파지토리(디비가 없어서.. 메모리에 저장하고 서비스로 돌아감) -> 디비
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
