package com.koreait.jpaitem;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.relation.Member;
import com.koreait.relation.Team;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		try {
			/*
			Team team = new Team();
			
			team.setName("TeamA");	
			// 어짜피 id는 @GeneratedValue에 의해 1개씩 자동증가 됨
			// 영속상태가 되면, PK의 값이 세팅이 된 후 영속상태가 된다.
			em.persist(team);
			
			Member member = new Member();
			member.setName("member1");
			member.setTeamid(team.getId());
			em.persist(member);
			
			// select		
			// 어느 팀 소속인지 알고 싶을 때 jpa or db에게 계속 물어봐야 한다.
			Member findMember = em.find(Member.class, member.getId()); // Member.class = 멤버 객체
			Long findTeamid = findMember.getTeamid();
			Team findTeam = em.find(Team.class, findTeamid);
			System.out.println("findTeam : " + findTeam.getName());
			*/
			
			// 처음 테이블 생성하고 두 번째 데이터부터 persistence = none으로 바꿔주기
			Team team = new Team();
			team.setName("TeamA");	
			em.persist(team);
			
			Member member = new Member();
			member.setName("member1");
//			member.setTeam(team);
			em.persist(member);
			
			// 강제로 DB쿼리를 보고 싶을 때
			em.flush();	// DB로 보내는 것
			em.clear();	// 영속성 컨텍스트 비워내는 것
			
			// select		
			// find시에 1차 캐시에서 가지고와서 select문이 없다.
			Member findMember = em.find(Member.class, member.getId()); 
			Team findTeam = findMember.getTeam();	// Team 객체를 통으로 받아온다
			System.out.println("findTeam : " + findTeam.getName());
			// findTeam 가장 마지막의 데이터
			
			// 수정
//			Team newTeam = em.find(Team.class, 1L);
//			findMember.setTeam(newTeam);
//			System.out.println("findTeamName : " + newTeam.getName());
//			System.out.println("findTeam.getId() : " + newTeam.getId());
			
			// 양방향 매핑 (Member에서 Team 접근 / Team에서 Member 접근 => em.flush, clear 해줘야 함)
			// 먼저 Team 객체에 인서트를 함. -> Team 클래스 안에 데이터 들어 감 (이 때는 member안에 데이터 없음) 
			// -> Member안에 인서트 해줌 (앞에서 Team에 대한 정보를 가지고 있음)
			// -> Member는 Team에 대한 정보가 있지만 Team에는 Member에 대한 정보가 없음 
			// -> flush와 clear를 안해주면 이 상태가 유지됨(팀안의 멤버를 가지고 올 수가 없음)
			// -> 그렇기 때문에 flush, clear로 db로 보낸 다음 다시 찾아 오라고 요청 
			// -> 그땐 Team안에 멤버가 다시 담기게 됨
			Member findSideMember = em.find(Member.class, member.getId());
			List<Member> members = findSideMember.getTeam().getMember();
			
			for(Member m : members) {
				System.out.println("result = " + m.getName());
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
