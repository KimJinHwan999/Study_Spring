package com.koreait.mylogin.loginweb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.koreait.mylogin.loginweb.member.Member;
import com.koreait.mylogin.loginweb.member.MemberRepository;
import com.koreait.mylogin.loginweb.session.SessionConst;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final MemberRepository memberRepository;
	

	// @GetMapping
	public String home() {
		return "home";
	}
	
	// @GetMapping
	public String homev2(@CookieValue(name = "memberId", required = false) Long memberId,
						 Model model) {	// required = false (로그인 안 한 사용자도 들어올 수 있도록 해줌)
		
		// 로그인 한 사용자가 아니라면 home으로 보낸다.
		if(memberId == null) {
			return "home";
		}
		
		// db조회
		Member loginMember = memberRepository.findById(memberId);
		// 사용자가 없으면 null 처리필요
		if(loginMember == null) {
			return "home";
		}
		
		// loginHome : 로그인에 성공한 사람만이 볼 수 있는 화면
		model.addAttribute("member", loginMember);
		return "loginHome";
	}
	
	// @GetMapping
	public String homev3(HttpServletRequest request, Model model) {	
		HttpSession session = request.getSession(false);
		
		// 로그인 한 사용자가 아니라면 home으로 보낸다.
		if(session == null) {
			return "home";
		}
		
		Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
		
		// 사용자가 없으면 null 처리필요
		if(loginMember == null) {
			return "home";
		}
		
		// loginHome : 로그인에 성공한 사람만이 볼 수 있는 화면
		model.addAttribute("member", loginMember);
		return "loginHome";
	}
	
	
	
	@GetMapping
	public String homev4(@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required=false) Member loginMember, 
						 Model model) {	// sessionAttribute를 뒤져서 member에 값을 넣어준다.
		
	
		// 사용자가 없으면 null 처리필요
		if(loginMember == null) {
			return "home";
		}
		
		// loginHome : 로그인에 성공한 사람만이 볼 수 있는 화면
		model.addAttribute("member", loginMember);
		return "loginHome";
	}
	
} // HomeController
