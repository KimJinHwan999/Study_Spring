package com.koreait.item.domain.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository // @Repository 덕분에 해당 클래스는, 스프링 컨테이너에서 빈으로 등록된 클래스가 된다.
public class ItemRepository {
	
	// static 사용
	private static final Map<Long, Item> store = new HashMap<Long, Item>();
	private static long sequence = 0L;
	
	// 저장
	public Item save(Item item) {
		item.setId(++sequence);
		// ---- item.setId(++sequence); 줄까지 끝내면 item에 데이터 다 담긴 상태인것.
		store.put(item.getId(), item);
		return item;
	}
	
	// id로 찾기
	public Item findById(Long id) {
		return store.get(id);
	}
	
	// 전체 찾기
	public List<Item> findAll(){
		return new ArrayList<Item>(store.values());
	}
	
	// 수정
	public void update(Long itemId, Item updateParam) {
		// item을 먼저 찾는다
		Item findItem = findById(itemId);
		findItem.setItemName(updateParam.getItemName());
		findItem.setPrice(updateParam.getPrice());
		findItem.setQuantity(updateParam.getQuantity());
		
		findItem.setOpen(updateParam.getOpen());			// 판매 여부 로직처리
		findItem.setRegions(updateParam.getRegions());		// 등록 지역
		findItem.setItemType(updateParam.getItemType());	// 상품 종류
		findItem.setDeliveryCode(updateParam.getDeliveryCode()); // 배송 방식
	}
	
}	// ItemRepository class
