package com.koreait.Springtest19;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.Springtest19.memberDTO.Address;
import com.koreait.Springtest19.memberDTO.Member;
import com.koreait.Springtest19.orderDTO.Orders;


public class Main {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		try {
			
			Member member = new Member();
			Address addr = new Address("서울", "거꾸로해도 역삼역", "123");
			
			member.setName("member");
			member.setAddress(addr);
			em.persist(member);
			
			for(int i = 0; i<10; i++) {
				Orders order = new Orders(LocalDateTime.now(), "접수" + i, member);
				em.persist(order);
			}
			
			
			List<Member> members = em.createQuery("select m from Member m", Member.class)
									 .getResultList();
			List<Orders> orders = em.createQuery("select o from Orders o", Orders.class)
					 				.getResultList();
			
			for(Member m : members) {
				System.out.println("Member = Member [id=" + m.getMemberId() + ", name=" + m.getName() + ", city=" + m.getAddress().getCity() + 
						", street=" + m.getAddress().getStreet() + ", zipcode=" + m.getAddress().getZipcode() + "]");
			}
			for(Orders o : orders) {
				System.out.println("Id = Order [id=" + o.getOrderId() + ", orderDate=" + o.getOrderDate() + ", status=" + o.getStatus() + "]");
			}
			
			for(Member m : members) {
				System.out.println(m.toString());
			}
			for(Orders o : orders) {
				System.out.println(o.toString());
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
