package com.memo.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.memo.post.bo.PostBO;

@RequestMapping("/post")
@RestController
public class PostRestController {
	
	
	@Autowired
	private PostBO postBO;
	
	
	// 글쓰기 API
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam(value = "file", required = false) MultipartFile file,	  // 비필수 파라미터라서 value 사용
			HttpSession session) {
		
		// session에 들어있는 user id를 꺼낸다
		int userId = (int) session.getAttribute("userId");	// 로그인이 안되어있으면 에러나도록 설정
			// session.getAttribute에 마우스 대보면 리턴값이 object로 설정되어있어서 int로 변경
			// int로 설정하면 에러남
		String userLoginId = (String) session.getAttribute("userLoginId");
		
		// DB insert
		postBO.addPost(userId, userLoginId, subject, content, file);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
		
	}
	
	
}
