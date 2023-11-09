package com.memo.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
	
	
	/**
	 * 회원가입 화면
	 * @param model
	 * @return
	 */
	// URL : http://localhost:80/user/sign-up-view
	@GetMapping("/sign-up-view")
	public String signUpView(Model model) {
		
		model.addAttribute("viewName", "user/signUp");	// layout은 고정이 되고, 중간 내용만 바뀌게 됨
		
		return "template/layout";
		
	}
	
	
	
	/**
	 * 로그인 화면
	 * @param model
	 * @return
	 */
	// URL : http://localhost:80/user/sign-in-view
	@GetMapping("/sign-in-view")
	public String signInView(Model model) {
		
		model.addAttribute("viewName", "user/signIn");	// layout은 고정이 되고, 중간 내용만 바뀌게 됨
		
		return "template/layout";
		
	}
	
	
	
	// 로그아웃
	@RequestMapping("/sign-out")
	public String signOut(HttpSession session) {
		
		// 세션에 있는 내용을 비운다
		session.removeAttribute("userId");	// userRestController의 로그인 API - 로그인 처리의 "userId" 부분이랑 키가 같아야 함
		session.removeAttribute("userName");
		session.removeAttribute("userLoginId");
		
		// 로그인 화면으로 이동
		return "redirect:/user/sign-in-view";
		
	}
	
	
}
