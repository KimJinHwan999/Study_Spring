package com.koreait.relation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

// @Entity
@Getter @Setter
public class Team {

	@Id @GeneratedValue
	@Column(name = "TEAM_ID")
	private Long id;
	private String name;
	
	// Team 하나에 Member가 어레이리스트로 담기게 됨
	/*
	 *  team에 의해서 관리가 된다.
	 *  mappedBy가 적힌 곳은 읽기만 가능하다.
	 *  값을 넣어봐야 아무 일도 벌어지지 않는다.
	 *  대신 조회는 가능
	 */
	@OneToMany(mappedBy = "team") 
	private List<Member> member = new ArrayList<Member>();
	
	public void addMember(Member member) {
		member.setTeam(this);
		this.member.add(member);
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", member=" + member + "]";
	}
	
	
}
