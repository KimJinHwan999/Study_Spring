package com.koreait.mylogin.loginweb.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.mylogin.loginweb.member.Member;
import com.koreait.mylogin.loginweb.member.MemberRepository;
import com.koreait.mylogin.loginweb.session.SessionConst;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;
	/*
	 * login/loginForm
	 * loginForm.html -> thymeleaf 적용 
	 */
	
	@GetMapping("/login")
	public String loginForm(@ModelAttribute LoginForm loginForm) {
		return "login/loginForm";
	}
	
	// @PostMapping("/login")
	public String login(@ModelAttribute LoginForm form, Model model, 
								RedirectAttributes redirectAttributes, HttpServletResponse response) {
		
		Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
		if( loginMember == null ) {
			// 로그인 실패
			model.addAttribute("msg", "로그인 실패");
			return "login/loginForm";
		}
		
		/*
		 * - redirectAttributes.addAttribute 		: url이 뒤에 붙는다.
		 * - redirectAttributes.addFlashAttribute	: url이 뒤에 붙지 않는다. (1회성으로만 사용)
		 */
		// 로그인 성공
		Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
		response.addCookie(idCookie);
		redirectAttributes.addFlashAttribute("msg", "로그인 성공");
		return "redirect:/";
	}
	
	// @PostMapping("/login")
	public String loginv2(@ModelAttribute LoginForm form, Model model, 
								RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
		if( loginMember == null ) {
			// 로그인 실패
			model.addAttribute("msg", "로그인 실패");
			return "login/loginForm";
		}
		
		// 로그인 성공
		HttpSession session = request.getSession();
		
		// 세션에 로그인 회원 정보 보관
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
		redirectAttributes.addFlashAttribute("msg", "로그인 성공");
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String loginv3(@ModelAttribute LoginForm form, Model model, 
						RedirectAttributes redirectAttributes, HttpServletRequest request,
						@RequestParam(defaultValue = "/") String redirectURL) {
		
		Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
		if( loginMember == null ) {
			// 로그인 실패
			model.addAttribute("msg", "로그인 실패");
			return "login/loginForm";
		}
		
		// 로그인 성공
		HttpSession session = request.getSession();
		
		// 세션에 로그인 회원 정보 보관
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
		redirectAttributes.addFlashAttribute("msg", "로그인 성공");
		// 파라미터로 넘어온 값이 없으면 기본값인 /로 넘어감
		// 파라미터로 넘어온 값 (items) 이 있으면 /items로 넘어감
		return "redirect:" + redirectURL;
	}
		
	
	// @PostMapping("/logout")
	public String logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("memberId", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "redirect:/";
	}
	
	@PostMapping("/logout")
	public String logoutv2(HttpServletRequest request) {
		// 세션을 삭제
		/*
		 * request.getSession(true)
		 * 	- 세션이 있으면 기존 세션을 반환한다.
		 * 	- 세션이 없으면 새로운 세션을 생성해서 반환한다.
		 * 
		 * request.getSession(false)
		 *  - 세션이 있으면 기존 세션을 반환한다.
		 *  - 세션이 없으면 새로운 세션을 생성하지 않고 null을 반환
		 */
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate(); // 세션 다 날리기
		}
		
		return "redirect:/";
	}
	
}
