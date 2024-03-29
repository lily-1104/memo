package com.memo.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.memo.post.mapper.PostMapper;

@Controller
public class TestController {
	
	@Autowired
	PostMapper postMapper;
	
	
	// URL : http://localhost:80/test1
	@ResponseBody
	@GetMapping("/test1")
	public String test1() {
		
		return "Hello World";
		
	}
	
	
	// URL : http://localhost:80/test2
	@ResponseBody
	@GetMapping("/test2")
	public Map<String, Object> test2() {
		
		Map<String, Object> map = new HashMap<>();
		map.put("a", 1);
		map.put("b", 200);
		
		return map;
		
	}
	
	
	// URL : http://localhost:80/test3
	@GetMapping("/test3")
	public String test3() {
		
		return "test/test";
		
	}
	
	
	// URL : http://localhost:80/test4
	@ResponseBody
	@GetMapping("/test4")
	public List<Map<String, Object>> test4() {
			
		return postMapper.selectPostList();
			
	}
	
	
}
