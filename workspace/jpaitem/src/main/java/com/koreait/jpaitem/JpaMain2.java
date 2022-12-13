package com.koreait.jpaitem;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.relation.Member;
import com.koreait.relation.Team;

public class JpaMain2 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		try {
			// 양방향 매핑 시 가장 많이 하는 실수
			// Owner는 이미 JPA안에 정해져있다. (주인 = Member(자식테이블) => Team의 members는 Member안에 있는 team과 mappedBy 되어있기 때문에..)
			// Owner가 insert 해야지만 insert가 됨
			/*
			Member member = new Member();
			member.setName("member1");
			em.persist(member);
			
			
			Team team = new Team();
			team.setName("TeamA");	
			team.getMember().add(member);
			em.persist(team);
			*/
			
			Team team = new Team();
			team.setName("TeamA");	
			em.persist(team);
			
			Member member = new Member();
			member.setName("member1");
//			member.setTeam(team);
//			member.changeTeam(team);
			em.persist(member);
			
			team.addMember(member);
			
			
//			em.flush();
//			em.clear();
			
			// 객체 지향적인 입장에서 양쪽에 모두 값을 넣어줘야 한다.
			// 양방향 매핑시에는 양쪽에 값을 모두 입력해 주어야 한다.
			// DB를 다시 다녀오지 않고 객체 상태로만 사용할 수 있다.
//			team.getMember().add(member);
			
			System.out.println("============================");
			
			Team findTeam = em.find(Team.class, team.getId());
			List<Member> members = findTeam.getMember();
			
			for(Member m : members) {
				System.out.println("m = " + m.getName());
			}
			
			System.out.println("============================");
			
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
}
