package com.koreait.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// RunAs > java application으로 실행!
public class JpaMain {

	public static void main(String[] args) {
		
		// persistence.xml 파일의 이름이 hello임!
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		
		// transaction 발생
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			// JPQL (쿼리문을 통해 섬세한 쿼리문 날려주기 / JPA와 JPQL 7:3정도 사용 / 동적인 쿼리는 QueryDSL 사용)
			// JPA는 쿼리를 짤 때 Table을 대상으로 쿼리를 짜지 않고,
			// member 객체를 통해서 쿼리를 짠다.
			List<Member> result = em.createQuery("select m from Member as m", Member.class)
									.setFirstResult(5)		// 5번부터
									.setMaxResults(10)		// 10개 가지고 와
									.getResultList();
			
			for(Member member : result) {
				System.out.println("membername : " + member.getName());
			}
			
//			// 이하 JPA
//			// 회원 조회
//			Member findMember = em.find(Member.class, 2L);
//			System.out.println("=====================================");
//			System.out.println("findMember.id : " + findMember.getId());
//			System.out.println("findMember.name : " + findMember.getName());
//			
//			// 회원 수정
//			findMember.setName("UpdateUser");
//			
//			// 회원 삭제
//			em.remove(findMember);
			
//			// 회원 등록
//			Member member = new Member();
//			
//			// 데이터 추가 (id는 PK값이기에 안넣어주면 에러남)
//			member.setId(3L);
//			member.setName("userC");
//			
//			// persist : db에 저장
//			em.persist(member);
//			
//			
//			// 커밋
//			tx.commit();
		} catch (Exception e) {
			
			// 예외 상황 생기면 이전으로 롤백시켜주기
			tx.rollback();
			
		} finally {
			
			// 다 끝나면 닫아주기
			em.close();
			emf.close();
			
		}
		
	}

}
