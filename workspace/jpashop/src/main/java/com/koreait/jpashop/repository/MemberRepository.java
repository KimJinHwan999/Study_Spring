package com.koreait.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.jpashop.domain.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

	// @PersistenceContext 	: JPA가 지원해주는 표준 어노테이션
	//						  Spring이 EntityManager를 만들어서 em에다가 주입
//	@PersistenceContext
	// @Autowired			: Spring Boot lib 사용 시 @Autowired 지원한다.
	@Autowired
	private final EntityManager em;
	
	// 생성자 주입
//	private MemberRepository(EntityManager em) {
//		this.em = em;
//	}
	
	// emf 는 굳이 불러올 필요 없음(내부에서 알아서 해줌)
	
	
	//---------------------(Business Logic)----------------------
	// 저장
	
	public void save(Member member) {
		em.persist(member);
	}
	
	// 1건 조회
	
	public Member findOne(Long id) {
//		Member member = em.find(Member.class, id);
//		return member;
		
		return em.find(Member.class, id);
	}
	
	// 여러 건 조회
	
	public List<Member> findAll(){
		return em.createQuery("select m from Member m", Member.class)
				 .getResultList();
	}
	
	// 이름으로 조회
	
	public List<Member> findByName(String name){
		return em.createQuery("select m from Member m where m.name = :name", Member.class)
				 .setParameter("name", name)
				 .getResultList();
	}
	
}

















