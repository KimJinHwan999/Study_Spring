package com.koreait.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koreait.jpashop.domain.Member;
import com.koreait.jpashop.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
	
	// @Autowired (하나만 있을 땐 없어도 됨)
	private final MemberRepository memberRepository;
	
	// 회원가입
	@Transactional
	public Long join(Member member) throws IllegalAccessException {
		// 중복회원 검증 로직 추가
		validateMemberCheck(member);
		memberRepository.save(member);
		return member.getId();
	}
	
	// 예외처리는 서비스 영역에서 정리 -> try catch문은 컨트롤러단으로 날리기
	// 다른곳에서 쓸 일 없는 메서드라면 private로 잠궈줘도 됨
	private void validateMemberCheck(Member member) throws IllegalAccessException {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		
		if( !findMembers.isEmpty() ) {
			throw new IllegalAccessException("이미 존재하는 회원입니다.");
		}
		
	}
	
	// findMembers
	// @Transactional(readOnly = true)
	// 읽기 전용이면 비용을 아낀다.
	// @Transactional(readOnly = true)
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}

	// 회원 단건 조회
	// @Transactional(readOnly = true)
	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}
}
