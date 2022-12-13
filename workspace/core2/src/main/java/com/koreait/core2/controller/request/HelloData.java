package com.koreait.core2.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



// @RequiredArgsConstructor = 파라미터가 있는 생성자 만들어 줌
// @ToString = toStriong 만들어 줌
// @Data = 모든 걸 다 만들어 줌 (서버에 올릴거면 다 열어주기 때문에 위험함 / 테스트 단계에선 데이터만 달아 줌)

@Getter @Setter @ToString
public class HelloData {

	private String username;
	private int age;
}
