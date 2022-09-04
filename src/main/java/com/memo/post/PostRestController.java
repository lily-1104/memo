package com.memo.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.memo.post.bo.PostBO;

@RestController
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	
	// 메모 입력 api
	@PostMapping("/post/create")
	public Map<String, String> memoCreate(
			@RequestParam("title") String title
			, @RequestParam("content") String content
			, @RequestParam(value="file", required=false) MultipartFile file	// MultipartFile file은 
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		int userId = (Integer) session.getAttribute("userId");
		
		int count = postBO.addPost(userId, title, content, file);
		
		Map<String, String> result = new HashMap<>();
		
		if (count == 1) {
			result.put("result", "success");
			
		} else {
			result.put("result", "fail");
		}
		
		return result;
		
	}
	
	
	
	// 게시글 수정
	@PostMapping("/post/update")
	public Map<String, String> updatePost(
			@RequestParam("postId") int postId
			, @RequestParam("title") String title
			, @RequestParam("content") String content) {
		
		int count = postBO.updatePost(postId, title, content);
		
		Map<String, String> map = new HashMap<>();
		
		if(count == 1) {
			map.put("result", "success");
		} else {
			map.put("result", "fail");
		}
		
		return map;
		
	}
	
	
	
	// 게시글 삭제
	@GetMapping("/post/delete")
	public Map<String, String> deletePost(@RequestParam("postId") int postId) {
		
		Map<String, String> map = new HashMap<>();
		
		int count = postBO.deletePost(postId);
		
		if(count == 1) {
			map.put("result", "success");
		} else {
			map.put("result", "fail");
		}
		
		return map;
		
	}
	
}
