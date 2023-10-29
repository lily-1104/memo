package com.memo.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
	
	
	// 회원가입
	// URL : http://localhost:80/user/sign-up-view
	@GetMapping("/sign-up-view")
	public String signUpView(Model model) {
		
		model.addAttribute("viewName", "user/signUp");	// layout은 고정이 되고, 중간 내용만 바뀌게 됨
		
		return "template/layout";
		
	}
	
	
}
