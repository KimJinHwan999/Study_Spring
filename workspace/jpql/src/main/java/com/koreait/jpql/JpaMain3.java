package com.koreait.jpql;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.jpql.domain.Address;
import com.koreait.jpql.domain.Member;
import com.koreait.jpql.domain.Team;


public class JpaMain3 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		try {
			Member member = new Member();
			member.setUsername("member1");
			member.setAge(10);
			em.persist(member);
			
			em.flush();
			em.clear();

//			em.createQuery("select o.address from Order o", Address.class)
//			  .getResultList();
 			
			// Address는 Entity로 만들어진 class가 아니기 때문에, 테이블 처럼 조회해 올 수 없다
			// Exception
//			em.createQuery("select o.address from Address o", Address.class)
//			  .getResultList();
			
			em.createQuery("select distinct m.username, m.age from Member m")
			  .getResultList();
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
}






























