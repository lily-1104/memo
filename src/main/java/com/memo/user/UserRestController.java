package com.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	
	// 회원가입
	// URL : http://localhost:80/user/sign-up-view
	@RequestMapping("/is-duplicated-id")	// RequestMapping 으로 해야 get, post 전부 가능
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId) {
		
		// db 조회
		
		
		
		
		// 응답값 만들고 리턴 => JSON
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("isDuplicated", true);
		
		return result;
		
	}
	

}
