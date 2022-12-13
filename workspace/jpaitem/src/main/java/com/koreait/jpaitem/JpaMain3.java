package com.koreait.jpaitem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.jpaitem.domain.Item;
import com.koreait.jpaitem.domain.Member;
import com.koreait.jpaitem.domain.Order;
import com.koreait.jpaitem.domain.OrderItem;

public class JpaMain3 {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		try {
			Member member = new Member();
			member.setName("member1");
			member.setCity("Seoul");
			member.setStreet("도산대로");
			member.setZiqcode("122-233");
			em.persist(member);
			
			Order order = new Order();
			order.setOrderDate(null);
			order.setStatus("주문완");
			em.persist(order);
			
			Item item = new Item();
			item.setName("연필");
			item.setPrice(1000);
			item.setStockQuantity(20);
			em.persist(item);
			
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderPrice(20000);
			orderItem.setCount(3);
			em.persist(orderItem);
			
			
			
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
}
