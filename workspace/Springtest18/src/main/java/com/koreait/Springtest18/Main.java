package com.koreait.Springtest18;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.Springtest18.memberDTO.Address;
import com.koreait.Springtest18.memberDTO.DateTime;
import com.koreait.Springtest18.memberDTO.Member;
import com.koreait.Springtest18.memberDTO.Position;


public class Main {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		try {
			
			Member member = new Member();
			Address addr = new Address("역삼역", "역삼길", "123");
			DateTime dateTime = new DateTime(LocalDateTime.now(), LocalDateTime.now());
			
			member.setUsername("테스트데이터");
			member.setAge(30);
			member.setAddress(addr);
			member.setDateTime(dateTime);
			member.setPosition(Position.MANAGER);
			
			em.persist(member);
			
			List<Member> members = em.createQuery("select m from Member m", Member.class)
									 .getResultList();
			
			for(Member m : members) {
				System.out.println(m.toString());
			}
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
}
