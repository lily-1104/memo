package com.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.memo.user.bo.UserBO;
import com.memo.user.entity.UserEntity;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	
	@Autowired
	private UserBO userBO;
	
	
	/**
	 * 로그인 아이디 중복 확인 API
	 * @param loginId
	 * @return
	 */
	// URL : http://localhost:80/user/sign-up-view
	@RequestMapping("/is-duplicated-id")	// RequestMapping 으로 해야 get, post 전부 가능
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId) {
		
		// db 조회
		UserEntity user = userBO.getUserEntityByLoginId(loginId);
		
		// 응답값 만들고 리턴 => JSON
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		
		if (user == null) {
			
			// 중복 아님
			result.put("isDuplicated", false);
			
		} else {
			
			// 중복
			result.put("isDuplicated", true);
		}
		
		return result;
	}
	
	
	
	// 회원가입
	@PostMapping("/sign-up")
	public Map<String, Object> signUp(
			@RequestParam(("loginId") String loginId,	// "loginId" : jsp의 name 값
			@RequestParam(("password") String password,
			@RequestParam(("name") String name,
			@RequestParam(("email") String email) {
		
		// password 해싱 (암호화 -> 복호화 불가능) - md5 알고리즘
		String hashedPassword = EncryptUtils.md5(password);
				
		// DB insert
		Integer id = userBO.addUser(loginId, hashedPassword, name, email);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		
		if (id == null) {
			result.put("code", 500);
			result.put("errorMessage", "회원가입 하는데 실패했습니다");
			
		} else {	// 성공
			
			result.put("code", 200);
			result.put("result", "성공");
		}
		
		return result;	// json
		
	}

}
