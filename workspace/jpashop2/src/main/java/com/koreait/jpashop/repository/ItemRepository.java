package com.koreait.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.jpashop.domain.Item;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
	
	@Autowired
	private final EntityManager em;
	

	// 등록
	public void save(Item item) {
		// 처음에 item의 id 가 없으면 신규등록
		// 머지는 중간에 데이터가 바뀌는 것을 알아채지 못함
//		if(item.getId() == null) {
			// 신규
			em.persist(item);
//		}else {
//			// jpa를 통해서 db에 한 번 들어간 값
//			em.merge(item);
//		}
		
	}
	
	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class)
				 .getResultList();
	}
	
	// item 하나 조회
	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}
	


}
