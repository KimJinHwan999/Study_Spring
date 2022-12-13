package com.koreait.core2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koreait.core2.member.Member;
import com.koreait.core2.repository.MemberRepository;
import com.koreait.core2.repository.MemoryMemberRepository;

@Service
@Transactional
public class MemberService {
	/* MemberRepository memberRepository = new MemoryMemberRepository(); // 인터페이스 = 구현체 */
	
	private final MemberRepository memberRepository;
	
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	// 회원가입
	public int join(Member member) {
		memberRepository.save(member);
		return member.getId();
	}
	
	// 전체 회원 조회
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
}
