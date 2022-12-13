package com.koreait.item.enumeration;

public class Main02 {

	public static void main(String[] args) {
		Season season = Season.SPRING;
		
		// name() : 열거 객체의 문자열 리턴
		String name = season.name();
		System.out.println(name);
		System.out.println("-----");
		
		// ordinal() : 열거 객체가 몇 번째인지를 리턴
		int ordinal = season.ordinal();
		System.out.println(ordinal);
		System.out.println("-----");
		
		// values() : 열거 타입의 모든 열거 객체들을 배열로 만들어 리턴
		Season[] season1 = Season.values();
		// 배열에 담긴 값을 하나씩 꺼내서 출력
		for( Season s : season1 ) {
			System.out.println(s);
		}
		
	}
}
