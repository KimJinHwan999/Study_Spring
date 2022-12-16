package com.koreait.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koreait.jpashop.domain.Item;
import com.koreait.jpashop.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;

	// 저장
	@Transactional
	public void saveItem(Item item) {
		itemRepository.save(item);
	}
	
	public List<Item> findItem(){
		return itemRepository.findAll();
	}


	public Item findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}

	@Transactional
	public void updateItem(Long itemid, String name, int price, int stockQuantity) {
		// select문을 통해서 영속성 컨텍스트에서 가져옴 (find 한번 해서 영속상태의 더티체킹을 통해 변경감지 가능하도록)
		// findItem은 영속상태에 놓이게 된다.
		Item findItem = itemRepository.findOne(itemid); 
		findItem.setName(name);
		findItem.setPrice(price);
		findItem.setStockQuantity(stockQuantity);
		
	}
	
	
	
}
