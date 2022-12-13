package com.koreait.jpaitem;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.koreait.jpaitem.embedded.Member;

public class JPAMain7 {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		try {
			List<Member> result =  em.createQuery(
									// Member는 클래스 대소문자 그대로
									// 전체 가져오는 건 * 대신 알리아스 적어주기
									// 알리아스는 필수 (AS는 생략가능)
									"select m from Member m where m.username like '%kim%' ",  
									Member.class).getResultList(); // arraylist로 담아옴
			
			for(Member member : result) {
				System.out.println("member : " + member);
			}
			
			// TypedQuery
			// 타입정보가 Member로 명확할 
			TypedQuery<Member> query = 
					em.createQuery("select m from Member m", Member.class);
			
			// 타입정보가 String.class로 반환이 명확할 때
			TypedQuery<String> query2 = 
					em.createQuery("select m.username from Member m", String.class); // 반환값을 String.class로
			
			// m.username(String), m.age(int) : 반환타입이 명확하지 않을 때
			Query query3= 
					em.createQuery("select m.username, m.age from Member m"); 
			
			// getResultList()
			TypedQuery<Member> query4 = 
					em.createQuery("select m from Member m", Member.class);
			List<Member> resultList = query4.getResultList();
			
			for(Member member : resultList) {
				System.out.println("member : " + member);
			}
			
			// 결과값 하나일 때, getSingleResult()
			TypedQuery<Member> query5 = 
					em.createQuery("select m from Member m where m.id = 10", Member.class);
			Member result2 = query5.getSingleResult();
			
			
			
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
		}finally {
			em.close();
			emf.close();
		}
	}
}












