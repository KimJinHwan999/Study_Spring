package com.koreait.jpaitem;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.jpaitem.embedded.Address;
import com.koreait.jpaitem.embedded.Member;
import com.koreait.jpaitem.embedded.Period;

public class JPAMain6 {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		// JPAmain5는 사람마다 다른 주소 객체를 넣어줬다. (m1 => addr / m2 => copyaddr)
		// JPAmain6은 애초에 같은 주소 객체를 넣어줬다. (m1 => addr <= m2)
		// 만약 m1만 수정해주고 싶다면 그제서야 m1만 newAddr로 갈아끼워주면 됨.
		try {
			Address addr = new Address("서울", "역삼", "123");
			
			Member member = new Member();
			member.setUsername("user1");
			member.setAddress(addr);
			em.persist(member);
						
			Member member2 = new Member();
			member2.setUsername("user2");
			// user1 과 user2가 같은 addr을 가지고 있다.
			member2.setAddress(addr);
			em.persist(member2);
			
			// user1의 주소만 newCity로 변경하고 싶다
			// copyAddr로 그냥 새로운 객체 만들어 member2에 넣어주기
			Address newAddr = new Address("newCity", "역삼", "123");
			member.setAddress(newAddr);
			em.persist(member);
			
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
		}finally {
			em.close();
			emf.close();
		}
	}
}
