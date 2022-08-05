package com.memo.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	// view 만 구성
	
	@GetMapping("/user/signup/view")
	public String signupView() {
		return "user/signup";
	}

	
	@GetMapping("/user/signin/view")
	public String signinView() {
		return "user/signin";
	}
	
	
	@GetMapping("/user/signout")
	public String signOut(HttpServletRequest request) {
		
		// 로그아웃
		// 로그인 시에 저장한 세션의 값들을 모두 제거한다 
		
		HttpSession session = request.getSession();
		
			// session은 어떤 페이지에서든 똑같은 값을 가지고있음
			// userId, userLoginId, userName
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		
		
			// redirect:리다이렉트 할 주소
		return "redirect:/user/signin/view";
		
		
		
	}
	
}
